package ptit.QLKS.dto;

import lombok.Data;

@Data
public class RoomDTO {

    private String id;

    private String roomName;

    private long price;

    private long electricPrice;

    private long waterPrice;

    private long networkPrice;

    private long maxNumberPeople;

    private String type;

    private String description;

    private String location;

    private String store;

    private boolean isBooking;

    private boolean isValid;

    private int numberOfRoom;

}
