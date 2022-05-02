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
public class BillDTO implements Serializable {

    private int id;

    private int month;

    private int year;

    @JsonProperty("electric_number")
    private long electricNumber;

    @JsonProperty("water_number")
    private long waterNumber;

    @JsonProperty("network_number")
    private long networkNumber;

    private String username;

    @JsonProperty("room_name")
    private String roomName;

    @JsonProperty("store_name")
    private String storeName;

    @JsonProperty("total_electric_price")
    private long totalElectricPrice;

    @JsonProperty("total_water_price")
    private long totalWaterPrice;

    @JsonProperty("total_network_price")
    private long totalNetworkPrice;

    @JsonProperty("room_price")
    private long roomPrice;

    @JsonProperty("total_bill")
    private long totalBill;

    private String status;
}
