package ptit.QLKS.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoomRequest {

    private String id;

    @JsonProperty("room_name")
    private String roomName;

    @JsonProperty("price")
    private long price;

    @JsonProperty("electric_price")
    private long electricPrice;

    @JsonProperty("water_price")
    private long waterPrice;

    @JsonProperty("network_price")
    private long networkPrice;

    @JsonProperty( "max_number_people")
    private long maxNumberPeople;

    @JsonProperty("type")
    private String type;

    @JsonProperty("description")
    private String description;

    @JsonProperty("location")
    private String location;
}
