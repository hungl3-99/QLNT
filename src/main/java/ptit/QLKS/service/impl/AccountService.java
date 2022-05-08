package ptit.QLKS.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ptit.QLKS.constrant.Constrant;
import ptit.QLKS.dto.AccountDTO;
import ptit.QLKS.dto.UpdateAccountDTO;
import ptit.QLKS.dto.UpdateStoreDTO;
import ptit.QLKS.entity.Account;
import ptit.QLKS.mapper.impl.AccountMapper;
import ptit.QLKS.repository.AccountCustomRepository;
import ptit.QLKS.repository.AccountRepository;
import ptit.QLKS.service.EncryptDecryptService;
import ptit.QLKS.vo.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
public class AccountService implements UserDetailsService {

    @Resource
    private AccountRepository accountRepository;

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private AccountCustomRepository accountCustomRepository;

    @Autowired
    EncryptDecryptService encryptDecryptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(account);
    }

    public Account updateAccountInfo(UpdateAccountDTO registerRequest){
        Account account = accountRepository.findByUsername(getLoginUser());
        updateAccount(registerRequest, account);
        return accountRepository.save(account);
    }

    public void inActiveUser(String username){
        Account account = accountRepository.findByUsername(username);
        Account admin = accountRepository.findByUsername(getLoginUser());
        if(!admin.getRole().equalsIgnoreCase(Constrant.ROLE_ADMIN)){
            throw new RuntimeException("You dont have permission to do this action !!!" );
        }
        account.setActive(false);
        accountRepository.save(account);
    }

    public ListResponse<List<AccountDTO>> getAccountByCondition(String username , String address , String tel , String idCard ,boolean isRequest, String role, int page , int size){
        Long totalElement = accountCustomRepository.getTotalElementByConditions(username , address , tel , idCard , isRequest , role);
        List<Account> results = accountCustomRepository.getAccountByConditions(username , address , tel , idCard , isRequest,role, page , size , totalElement);
        List<AccountDTO> result= accountMapper.toListDto(results);
        return ListResponse.success(HttpStatus.OK , "Success" , result , totalElement);
    }


    public BaseResponse<?> createRequestRoleStore(){
        String currentUser = getLoginUser();
        Account account = accountRepository.findByUsername(currentUser);
        if(!account.getRole().equals(Constrant.ROLE_USER)){
            return BaseResponse.error(HttpStatus.BAD_REQUEST , Constrant.CANNOT_UPDATE_ROLE_FOR_THIS_ACCOUNT , null);
        }
        account.setRequest(true);
        account.setUpdatedBy(currentUser);
        account.setUpdatedAt(new Date());
        accountRepository.save(account);
        return BaseResponse.success(HttpStatus.OK , Constrant.SUCCESS , null);
    }

    public BaseResponse<?> updateStatusRequest(UpdateStoreDTO dto){
        Account account = accountRepository.findById(dto.getId()).get();
        if(dto.getStatus().equalsIgnoreCase(Constrant.SystemStatus.APPROVED.getValue())){
            account.setRole(Constrant.ROLE_STORE);
            account.setRequest(false);
            account.setUpdatedBy(getLoginUser());
            account.setUpdatedAt(new Date());
            accountRepository.save(account);
        }
        return BaseResponse.success(HttpStatus.OK , Constrant.SUCCESS ,accountMapper.toDto(account));
    }

    private void updateAccount(UpdateAccountDTO registerRequest, Account account) {
        account.setTel(registerRequest.getTel());
        account.setAddress(registerRequest.getAddress());
        account.setBirthDay(registerRequest.getBirthDay());
        account.setAvatar(registerRequest.getAvatar());
        account.setIdCard(registerRequest.getIdCard());
        account.setUpdatedBy(getLoginUser());
        account.setRole(account.getRole());
        account.setUpdatedAt(new Date());
    }

    private String getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        return username;
    }

    public AccountDTO getLoginUserInFoDTO(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        Account account = accountRepository.findByUsername(username);
        return accountMapper.toDto(account);
    }

    public Account getLoginUserInFo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        String username = ((UserDetails)principal).getUsername();
        Account account = accountRepository.findByUsername(username);
        return account;
    }

    public ResponseEntity register(RegisterRequestByAdmin requestByAdmin){
        String username = requestByAdmin.getUsername();
        String password = requestByAdmin.getPassword();

        Account registerAccount = accountRepository.findByUsername(username);
        if(!ObjectUtils.isEmpty(registerAccount)){
            return new ResponseEntity(Constrant.USERNAME_ARE_ALREADY_EXIST , HttpStatus.UNAUTHORIZED);
        }

        if(!validateUsername(username)){
            return new ResponseEntity(Constrant.INVALID_USERNAME , HttpStatus.BAD_REQUEST);
        }
        try {
            password = encryptDecryptService.encrypt(password);
            log.info(password);
            Account account = createAccount(requestByAdmin , password);
            accountRepository.save(account);
            return new ResponseEntity<>(accountMapper.toDto(account) , HttpStatus.CREATED);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }

    private Account createAccount(RegisterRequestByAdmin registerRequest , String password) {
        Account account = Account.builder()
                .username(registerRequest.getUsername())
                .password(password)
                .isActive(true)
                .role(Constrant.ROLE_USER)
                .address(registerRequest.getAddress())
                .birthDay(registerRequest.getBirthDay())
                .tel(registerRequest.getTel())
                .isRequest(false)
                .idCard(registerRequest.getIdCard())
                .fullName(registerRequest.getFullName())
                .avatar(registerRequest.getPassword())
                .role(registerRequest.getRole())
                .build();
        account.setCreatedAt(new Date());
        account.setCreatedBy(account.getUsername());
        return account;
    }

    public boolean validateUsername(String username){
        Pattern usernamePattern = Pattern.compile("^(?=.{8,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
        Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
        if(usernamePattern.matcher(username).matches()){
            return true;
        }

        if(emailPattern.matcher(username).matches()){
            return true;
        }
        return false;
    }
}
