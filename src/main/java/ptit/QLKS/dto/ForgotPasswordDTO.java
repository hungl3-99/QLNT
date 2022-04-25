package ptit.QLKS.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ForgotPasswordDTO implements Serializable {
    private String username;
    private String oldPassword;
    private String newPassword;
}
