package ptit.QLKS.controller;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ptit.QLKS.dto.ForgotPasswordDTO;
import ptit.QLKS.vo.BaseResponse;
import ptit.QLKS.vo.LoginRequest;
import ptit.QLKS.vo.RegisterRequest;

@RequestMapping("/api")
public interface LoginController {

    @Operation(
            summary = "Login api",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody  LoginRequest loginRequest);

    @Operation(
            summary = "sign-in api",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest);

    @Operation(
            summary = "forgot password api",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PutMapping("/forgot-password")
    ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO);
}
