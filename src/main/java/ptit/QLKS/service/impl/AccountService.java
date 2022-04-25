package ptit.QLKS.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ptit.QLKS.constrant.Constrant;
import ptit.QLKS.dto.AccountDTO;
import ptit.QLKS.dto.UpdateStoreDTO;
import ptit.QLKS.entity.Account;
import ptit.QLKS.mapper.impl.AccountMapper;
import ptit.QLKS.repository.AccountCustomRepository;
import ptit.QLKS.repository.AccountRepository;
import ptit.QLKS.vo.BaseResponse;
import ptit.QLKS.vo.CustomUserDetail;
import ptit.QLKS.vo.ListResponse;
import ptit.QLKS.vo.RegisterRequest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    @Resource
    private AccountRepository accountRepository;

    @Resource
    private AccountMapper accountMapper;

    @Resource
    private AccountCustomRepository accountCustomRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(account);
    }

    public Account updateAccountInfo(RegisterRequest registerRequest){
        Account account = accountRepository.findByUsername(registerRequest.getUsername());
        if(!getLoginUser().equals(registerRequest.getUsername())){
            throw new IllegalArgumentException("UNAUTHORIZED");
        }
        updateAccount(registerRequest, account);
        return accountRepository.save(account);
    }

    public void inActiveUser(String username){
        Account account = accountRepository.findByUsername(username);
        if(ObjectUtils.isEmpty(account) || !account.getRole().equals(Constrant.ROLE_ADMIN)){
            throw new RuntimeException("You dont have permission to do this action !!!" );
        }
        account.setActive(false);
        accountRepository.save(account);
    }

    public ListResponse<List<AccountDTO>> getAccountByCondition(String username , String address , String tel , String idCard ,boolean isRequest, int page , int size){
        Long totalElement = accountCustomRepository.getTotalElementByConditions(username , address , tel , idCard , isRequest);
        List<Account> results = accountCustomRepository.getAccountByConditions(username , address , tel , idCard , isRequest, page , size , totalElement);
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
        return BaseResponse.success(HttpStatus.OK , Constrant.SUCCESS , account);
    }

    private void updateAccount(RegisterRequest registerRequest, Account account) {
        account.setFullName(registerRequest.getFullName());
        account.setTel(registerRequest.getTel());
        account.setAddress(registerRequest.getAddress());
        account.setBirthDay(registerRequest.getBirthDay());
        account.setAvatar(registerRequest.getAvatar());
        account.setIdCard(registerRequest.getIdCard());
        account.setUpdatedBy(registerRequest.getUsername());
        account.setUpdatedAt(new Date());
    }

    private String getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        return username;
    }

    public Account getLoginUserInFo(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        Account account = accountRepository.findByUsername(username);
        return account;
    }

}
