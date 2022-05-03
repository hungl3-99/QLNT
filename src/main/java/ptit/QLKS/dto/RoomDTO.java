package ptit.QLKS.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RoomDTO {

    private String id;

    @JsonProperty("room_name")
    private String roomName;

    private long price;

    @JsonProperty("electric_price")
    private long electricPrice;

    @JsonProperty("water_price")
    private long waterPrice;

    @JsonProperty("network_price")
    private long networkPrice;

    @JsonProperty("max_number_people")
    private long maxNumberPeople;

    private String type;

    private String description;

    private String location;

    private String store;

    @JsonProperty("booking")
    private boolean isBooking;

    @JsonProperty("valid")
    private boolean isValid;

    private String images;

    @JsonProperty("number_of_room")
    private int numberOfRoom;
}
