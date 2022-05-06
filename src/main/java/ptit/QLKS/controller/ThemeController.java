package ptit.QLKS.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/theme")
public interface ThemeController {

    @Operation(
            summary = "add new theme api",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @PostMapping
    ResponseEntity<?> createTheme(@RequestBody String link);


    @Operation(
            summary = "Delete theme api",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @DeleteMapping
    ResponseEntity<?> deleteTheme(@RequestParam int id);

    @Operation(
            summary = "Get All theme api",
            description = "Author: ",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"),
                    @ApiResponse(responseCode = "500", description = "Server Error")
            }
    )
    @GetMapping
    ResponseEntity<?> getAllTheme();
}
