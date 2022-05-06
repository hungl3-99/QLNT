package ptit.QLKS.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestByAdmin implements Serializable {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty ("address")
    private String address;

    @JsonProperty("tel")
    private String tel;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonProperty("birth_day")
    private Date birthDay;

    @JsonProperty("id_card")
    private String idCard;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("is_request")
    private boolean isRequest;

    @JsonProperty("role")
    private String role;
}
