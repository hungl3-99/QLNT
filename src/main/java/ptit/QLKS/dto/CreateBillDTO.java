package ptit.QLKS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateBillDTO implements Serializable {

    private int month;

    private int year;

    @JsonProperty("electric_number")
    private long electricNumber;

    @JsonProperty("water_number")
    private long waterNumber;

    @JsonProperty("network_number")
    private long networkNumber;

    @JsonProperty("room_id")
    private String roomId;
}
