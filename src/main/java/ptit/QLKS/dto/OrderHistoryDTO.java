package ptit.QLKS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderHistoryDTO {

    @JsonProperty("status")
    private String status;

    @JsonProperty("start_date")
    private String startDate;

    @JsonProperty("end_date")
    private String endDate;

    @JsonProperty("room")
    private RoomDTO roomDTO;

    @JsonProperty("bill")
    private List<BillDTO> billDTO;

    @JsonProperty("note")
    private String note;
}
