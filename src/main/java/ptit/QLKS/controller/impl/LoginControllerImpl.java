package ptit.QLKS.controller.impl;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ptit.QLKS.controller.LoginController;
import ptit.QLKS.dto.ForgotPasswordDTO;
import ptit.QLKS.service.LoginService;
import ptit.QLKS.vo.BaseResponse;
import ptit.QLKS.vo.LoginRequest;
import ptit.QLKS.vo.LoginResponse;
import ptit.QLKS.vo.RegisterRequest;


@RestController
@Log4j2
public class LoginControllerImpl implements LoginController {

    @Autowired
    LoginService loginService;

    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) {
        try{
            return loginService.Login(loginRequest);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> register(RegisterRequest registerRequest) {
            return loginService.register(registerRequest);
    }

    @Override
    public ResponseEntity<?> forgotPassword(ForgotPasswordDTO forgotPasswordDTO) {
        return loginService.forgotPassword(forgotPasswordDTO);
    }
}
