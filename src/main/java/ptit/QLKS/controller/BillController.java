package ptit.QLKS.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptit.QLKS.dto.BillDTO;
import ptit.QLKS.dto.CreateBillDTO;
import ptit.QLKS.dto.UpdateStatusBillDTO;


@RequestMapping("/api/bill")
public interface BillController {

    @Operation(
            summary = "Api create bill",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PostMapping
    ResponseEntity<?> createBill(@RequestBody CreateBillDTO createBillDTO);


    @Operation(
            summary = "get bill by conditions",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @GetMapping
    ResponseEntity<?> getBillByConditions(@RequestParam(defaultValue = "UNPAID") String status,
                                          @RequestParam(name = "findValue" , required = false) String findValue,
                                          @RequestParam(defaultValue = "1" , required = false) int page,
                                          @RequestParam(defaultValue = "10" , required = false) int size);


    @Operation(
            summary = "get bill by id",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<?> getBillById(@PathVariable int id);


    @Operation(
            summary = "get Statistical by month , year and store",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @GetMapping("/statistical")
    public ResponseEntity<?> getStatisticalByStore(@RequestParam int month ,
                                                   @RequestParam int year ,
                                                   @RequestParam String store_id);

    @Operation(
            summary = "update status bill by store",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PutMapping("/update-status")
    ResponseEntity<?> updateBillStatus(@RequestBody UpdateStatusBillDTO updateStatusBillDTO);

    @Operation(
            summary = "update bill by store",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PutMapping
    ResponseEntity<?> updateBill(@RequestBody BillDTO billDTO);


    @Operation(
            summary = "API check-out",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PutMapping("/check-out")
    ResponseEntity<?> checkout(@RequestBody CreateBillDTO createBillDTO);
}


