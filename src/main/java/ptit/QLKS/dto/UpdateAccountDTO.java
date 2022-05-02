package ptit.QLKS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateAccountDTO implements Serializable {
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
}
