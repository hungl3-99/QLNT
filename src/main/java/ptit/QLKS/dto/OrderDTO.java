package ptit.QLKS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @JsonProperty("room_id")
    private String roomId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("note")
    private String note;
}
