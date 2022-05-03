package ptit.QLKS.service;

import org.springframework.data.domain.Page;
import ptit.QLKS.dto.RoomDTO;
import ptit.QLKS.entity.Room;
import ptit.QLKS.vo.CreateRoomRequest;
import ptit.QLKS.vo.ListResponse;

import java.util.List;


public interface RoomService {
    Room createRoom(CreateRoomRequest createRoomRequest);
    Room updateRoom(CreateRoomRequest updateRoomRequest);
    ListResponse<?> getRoomByConditions(String location , String type , long number, String store , int page , int size);
    List<Room> getRentedRooms();
    Room getRoomById(String id);
    Page<Room> getRoomOfCurrentStore(int page , int size);
}
