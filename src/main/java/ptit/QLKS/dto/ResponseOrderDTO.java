package ptit.QLKS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOrderDTO implements Serializable {
    @JsonProperty("room_id")
    private String roomId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("note")
    private String note;

    @JsonProperty("order_account")
    private AccountDTO accountDTO;
}
