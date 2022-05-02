package ptit.QLKS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class StatisticalDTO implements Serializable {

    private int month;

    private int year;

    @JsonProperty("total_paid")
    private long totalPaid;

    @JsonProperty("total_unpaid")
    private long totalUnpaid;

    private long total;
}
