package ptit.QLKS.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ptit.QLKS.config.JwtUtils;
import ptit.QLKS.constrant.Constrant;
import ptit.QLKS.dto.AccountDTO;
import ptit.QLKS.dto.ForgotPasswordDTO;
import ptit.QLKS.entity.Account;
import ptit.QLKS.mapper.impl.AccountMapper;
import ptit.QLKS.repository.AccountRepository;
import ptit.QLKS.service.EncryptDecryptService;
import ptit.QLKS.service.LoginService;
import ptit.QLKS.vo.BaseResponse;
import ptit.QLKS.vo.LoginRequest;
import ptit.QLKS.vo.LoginResponse;
import ptit.QLKS.vo.RegisterRequest;

import javax.annotation.Resource;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Pattern;

@Service
@Log4j2
public class LoginServiceImpl implements LoginService {

    @Resource
    private JwtUtils jwtUtil;

    @Autowired
    EncryptDecryptService encryptDecryptService;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountMapper accountMapper;

    @Override
    public ResponseEntity<LoginResponse> Login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        try {
            password = encryptDecryptService.encrypt(password);
            log.info(password);
        }
        catch (Exception e){
            throw new RuntimeException("Encrypt Password Failed");
        }
        Account loginAccount = accountRepository.findByUsernameAndPassword(username , password);
        if(ObjectUtils.isEmpty(loginAccount)){
            return new ResponseEntity(Constrant.INVALID_USERNAME_OR_PASSWORD , HttpStatus.UNAUTHORIZED);
        }
        String token = jwtUtil.generateToken(loginAccount.getUsername(), loginAccount.getRole());
        token = Base64.getEncoder().encodeToString(token.getBytes());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setRole(loginAccount.getRole());
        loginResponse.setActive(loginAccount.isActive());
        loginResponse.setUsername(loginAccount.getUsername());
        loginResponse.setAvatar(loginAccount.getAvatar());
        loginResponse.setId(loginAccount.getId());
        return new ResponseEntity<>(loginResponse , HttpStatus.OK);
    }

    @Override
    public ResponseEntity register(RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();

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
            Account account = createAccount(registerRequest , password);
            accountRepository.save(account);
            return new ResponseEntity<>(accountMapper.toDto(account) , HttpStatus.CREATED);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public ResponseEntity<?> forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {
        try{

            String oldPassword = encryptDecryptService.encrypt(forgotPasswordDTO.getOldPassword());

            Account account = accountRepository.findByUsernameAndPassword(forgotPasswordDTO.getUsername(), oldPassword);
            if(ObjectUtils.isEmpty(account)){
                return new ResponseEntity(Constrant.WRONG_PASSWORD , HttpStatus.BAD_REQUEST);
            }

            String newPassword = encryptDecryptService.encrypt(forgotPasswordDTO.getNewPassword());
            account.setPassword(newPassword);
            accountRepository.save(account);
            return new ResponseEntity(Constrant.SUCCESS , HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public AccountDTO getAccountInformationOfCurrentUser() {
        String username = getLoginUser();
        if(ObjectUtils.isEmpty(username)){
            throw new IllegalArgumentException("User is not exist !!!");
        }
        Account account = accountRepository.findByUsername(username);
        return accountMapper.toDto(account);
    }


    private Account createAccount(RegisterRequest registerRequest , String password) {
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
    private String getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        log.info("{}" , username);
        return username;
    }

}
