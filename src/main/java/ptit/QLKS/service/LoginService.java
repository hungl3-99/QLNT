package ptit.QLKS.service;

import org.springframework.http.ResponseEntity;
import ptit.QLKS.dto.AccountDTO;
import ptit.QLKS.dto.ForgotPasswordDTO;
import ptit.QLKS.vo.BaseResponse;
import ptit.QLKS.vo.LoginRequest;
import ptit.QLKS.vo.LoginResponse;
import ptit.QLKS.vo.RegisterRequest;


public interface LoginService {
    ResponseEntity<LoginResponse> Login(LoginRequest loginRequest);
    ResponseEntity<AccountDTO> register(RegisterRequest registerRequest);
    ResponseEntity<?> forgotPassword(ForgotPasswordDTO forgotPasswordDTO);
}
