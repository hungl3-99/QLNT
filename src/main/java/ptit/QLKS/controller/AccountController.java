package ptit.QLKS.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ptit.QLKS.dto.AccountDTO;
import ptit.QLKS.dto.UpdateStoreDTO;
import ptit.QLKS.vo.BaseResponse;
import ptit.QLKS.vo.ListResponse;
import ptit.QLKS.vo.RegisterRequest;

import java.util.List;

@RequestMapping("/api/account")
public interface AccountController {

    @Operation(
            summary = "Update Account api",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PutMapping
    ResponseEntity<?> updateAccount(@RequestBody RegisterRequest registerRequest);

    @Operation(
            summary = "Inactive User api",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PutMapping("/in-active")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> inactiveUser(@RequestParam String username);

    @Operation(
            summary = "Get Account by Conditions api",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<?> getAccountByCondition(@RequestParam(required = false ) String username ,
                                                                         @RequestParam(required = false ) String address ,
                                                                         @RequestParam(required = false ) String tel ,
                                                                         @RequestParam(required = false ) String idCard,
                                                                         @RequestParam(required = false) boolean isRequest,
                                                                         @RequestParam(required = false , defaultValue = "1") int page,
                                                                         @RequestParam(required = false , defaultValue = "10") int size);

    @Operation(
            summary = "Request Update normal account to store account ",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/request-update")
    ResponseEntity<?> updateAccountToStore();

    @Operation(
            summary = "Update normal account to store account by Admin",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update-to-store")
    ResponseEntity<?> updateRequestToStoreByAdmin(@RequestBody UpdateStoreDTO dto);
}
