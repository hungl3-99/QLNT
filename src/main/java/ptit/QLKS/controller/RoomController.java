package ptit.QLKS.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptit.QLKS.vo.CreateRoomRequest;


@RequestMapping("/api/room")
public interface RoomController {

    @Operation(
            summary = "Create room api",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PostMapping
    ResponseEntity<?> createRoom(@RequestBody CreateRoomRequest createRoomRequest);

    @Operation(
            summary = "Update room api",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PutMapping
    ResponseEntity<?> updateRoom(@RequestBody CreateRoomRequest createRoomRequest);


    @Operation(
            summary = "Get-All room api",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @GetMapping
    ResponseEntity<?> findRoomByConditions(@RequestParam(name = "location" , required = false) String location,
                                         @RequestParam(name = "type" , required = false) String type,
                                         @RequestParam(name = "number" ,required = false , defaultValue = "10") long number,
                                         @RequestParam(name = "store" , required = false) String store,
                                         @RequestParam(required = false , defaultValue = "1") int page,
                                         @RequestParam(required = false , defaultValue = "10") int size);

    @Operation(
            summary = "Get-All room current user-hiring",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @GetMapping("/find-hiring-room")
    ResponseEntity<?> findHiringRoom();


    @Operation(
            summary = "Get-All room by id",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<?> getRoomById(@PathVariable String id);
}
