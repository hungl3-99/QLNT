package ptit.QLKS.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ptit.QLKS.dto.OrderDTO;
import ptit.QLKS.dto.UpdateStatusOrderDTO;

@RequestMapping("/api/order")
public interface OrderController {
    @Operation(
            summary = "Create order by user API",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping
    ResponseEntity<?> createOrderByUser(@RequestBody OrderDTO dto);

    @Operation(
            summary = "Update status order by user API",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PreAuthorize("hasRole('ROLE_STORE')")
    @PutMapping("/update-status")
    ResponseEntity<?> updateStatusOrder(@RequestBody UpdateStatusOrderDTO dto);

    @Operation(
            summary = "Get current room and bill  of user API",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @GetMapping("/get-current-room")
    ResponseEntity<?> getHiringRoomOfCurrentUser();
}
