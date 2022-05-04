package ptit.QLKS.mapper.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import ptit.QLKS.dto.RoomDTO;
import ptit.QLKS.entity.Room;
import ptit.QLKS.vo.CreateRoomRequest;

import java.util.ArrayList;
import java.util.List;

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
        room.setImages(createRoomRequest.getImages());
        return room;
    }


    public RoomDTO toDTO(Room room){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setId(room.getId());
        roomDTO.setRoomName(room.getRoomName());
        roomDTO.setPrice(room.getPrice());
        roomDTO.setElectricPrice(room.getElectricPrice());
        roomDTO.setWaterPrice(room.getWaterPrice());
        roomDTO.setNetworkPrice(room.getNetworkPrice());
        roomDTO.setMaxNumberPeople(room.getMaxNumberPeople());
        roomDTO.setType(room.getType());
        roomDTO.setDescription(room.getDescription());
        roomDTO.setLocation(room.getLocation());
        roomDTO.setBooking(room.isBooking());
        roomDTO.setValid(room.isValid());
        roomDTO.setImages(room.getImages());
        return roomDTO;
    }

    public List<RoomDTO> toListDto(List<Room> list){
        List<RoomDTO> result = new ArrayList<>();
        for(Room a:list){
            result.add(toDTO(a));
        }
        return result;
    }
}
