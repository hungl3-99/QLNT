package ptit.QLKS.mapper.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import ptit.QLKS.entity.Room;
import ptit.QLKS.vo.CreateRoomRequest;

@Component
public class RoomMapper {
    public Room toEntity(CreateRoomRequest createRoomRequest){
        Room room = new Room();
        if(!ObjectUtils.isEmpty(createRoomRequest.getId())){
            room.setId(createRoomRequest.getId());
        }
        room.setRoomName(createRoomRequest.getRoomName());
        room.setPrice(createRoomRequest.getPrice());
        room.setDescription(createRoomRequest.getDescription());
        room.setElectricPrice(createRoomRequest.getElectricPrice());
        room.setWaterPrice(createRoomRequest.getWaterPrice());
        room.setLocation(createRoomRequest.getLocation());
        room.setMaxNumberPeople(createRoomRequest.getMaxNumberPeople());
        room.setNetworkPrice(createRoomRequest.getNetworkPrice());
        room.setType(createRoomRequest.getType());
        room.setBooking(false);
        room.setValid(true);
        room.setNumberOfRoom(createRoomRequest.getNumberOfRoom());
        return room;
    }
}
