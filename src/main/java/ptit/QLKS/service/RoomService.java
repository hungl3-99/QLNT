package ptit.QLKS.service;

import ptit.QLKS.entity.Room;
import ptit.QLKS.vo.CreateRoomRequest;
import ptit.QLKS.vo.ListResponse;

import java.util.List;


public interface RoomService {
    Room createRoom(CreateRoomRequest createRoomRequest);
    Room updateRoom(CreateRoomRequest updateRoomRequest);
    ListResponse<?> getRoomByConditions(String location , String type , long number , int page , int size);
    List<Room> getRentedRooms();
}
