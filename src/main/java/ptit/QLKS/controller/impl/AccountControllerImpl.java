package ptit.QLKS.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ptit.QLKS.constrant.Constrant;
import ptit.QLKS.controller.AccountController;
import ptit.QLKS.dto.AccountDTO;
import ptit.QLKS.dto.UpdateAccountDTO;
import ptit.QLKS.dto.UpdateStoreDTO;
import ptit.QLKS.entity.Account;
import ptit.QLKS.mapper.impl.AccountMapper;
import ptit.QLKS.service.impl.AccountService;
import ptit.QLKS.vo.BaseResponse;
import ptit.QLKS.vo.ListResponse;
import ptit.QLKS.vo.RegisterRequest;
import ptit.QLKS.vo.RegisterRequestByAdmin;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class AccountControllerImpl implements AccountController {

    @Resource
    AccountService accountService;

    @Resource
    AccountMapper accountMapper;

    @Override
    public ResponseEntity<?> updateAccount(UpdateAccountDTO registerRequest) {
        try {
            Account account = accountService.updateAccountInfo(registerRequest);
            return ResponseEntity.ok(accountMapper.toDto(account));
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> inactiveUser(String username) {
        try {
            accountService.inActiveUser(username);
            return new ResponseEntity(Constrant.SUCCESS , HttpStatus.OK);
        }
        catch (RuntimeException runtimeException){
            return new ResponseEntity(runtimeException.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public ResponseEntity<?> getAccountByCondition(String username, String address, String tel, String idCard , boolean isRequest ,String role , int page, int size) {
        try {
            ListResponse<List<AccountDTO>> results = accountService.getAccountByCondition(username , address , tel ,idCard , isRequest,role , page , size);
            return ResponseEntity.ok(results);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public ResponseEntity<?> updateAccountToStore() {
        try {
            return new ResponseEntity<>(accountService.createRequestRoleStore() , HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public ResponseEntity<?> updateRequestToStoreByAdmin(UpdateStoreDTO dto) {
        try {
            return new ResponseEntity<>(accountService.updateStatusRequest(dto) , HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public ResponseEntity<?> getCurrentUserInfo() {
        try {
            AccountDTO accountDTO = accountService.getLoginUserInFoDTO();
            return ResponseEntity.ok(accountDTO);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> register(RegisterRequestByAdmin registerRequest) {
        return accountService.register(registerRequest);
    }
}
