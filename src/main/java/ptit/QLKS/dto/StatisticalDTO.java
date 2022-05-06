package ptit.QLKS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StatisticalDTO implements Serializable {


    @JsonProperty("list_bill")
    List<BillDTO> listBill;


    @JsonProperty("total_paid")
    private long totalPaid;

    @JsonProperty("total_unpaid")
    private long totalUnpaid;

    private long total;
}
