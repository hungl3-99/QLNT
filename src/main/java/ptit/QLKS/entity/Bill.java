package ptit.QLKS.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="bill")
public class Bill extends BaseEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;

    @Column(name = "electric_number")
    @JsonProperty("electric_number")
    private long electricNumber;

    @Column(name = "water_number")
    @JsonProperty("water_number")
    private long waterNumber;

    @Column(name = "network_number")
    @JsonProperty("network_number")
    private long networkNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "total_electric_price")
    @JsonProperty("total_electric_price")
    private long totalElectricPrice;

    @Column(name = "total_water_price")
    @JsonProperty("total_water_price")
    private long totalWaterPrice;

    @Column(name = "total_network_price")
    @JsonProperty("total_network_price")
    private long totalNetworkPrice;

    @Column(name = "room_price")
    @JsonProperty("room_price")
    private long roomPrice;

    @Column(name = "total_bill")
    @JsonProperty("total_bill")
    private long totalBill;

    @Column(name = "status")
    private String status;
}
