package ptit.QLKS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRoomByOwnerDTO implements Serializable {

    @JsonProperty("room_name")
    private String room_name;

    @JsonProperty("username")
    private String username;

    @JsonProperty("status")
    private String status;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("note")
    private String note;
}
