package ptit.QLKS.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusOrderDTO implements Serializable {
    private String id;
    private String status;
}
