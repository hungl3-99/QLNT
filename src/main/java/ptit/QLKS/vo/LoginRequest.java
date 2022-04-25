package ptit.QLKS.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;
}
