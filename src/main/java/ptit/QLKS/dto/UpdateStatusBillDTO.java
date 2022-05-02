package ptit.QLKS.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateStatusBillDTO implements Serializable {
    private int id;
    private String status;
}
