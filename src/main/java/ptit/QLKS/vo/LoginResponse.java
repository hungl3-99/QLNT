package ptit.QLKS.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse  implements Serializable {
    private static final long serialVersionUID = -2007191464721800412L;
    private String token;
    private String username;
    private String role;
    private boolean isActive;
    private String avatar;
}
