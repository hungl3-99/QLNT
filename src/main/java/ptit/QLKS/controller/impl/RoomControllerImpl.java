package ptit.QLKS.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ptit.QLKS.constrant.Constrant;
import ptit.QLKS.controller.RoomController;
import ptit.QLKS.entity.Room;
import ptit.QLKS.service.RoomService;
import ptit.QLKS.vo.BaseResponse;
import ptit.QLKS.vo.CreateRoomRequest;


@RestController
public class RoomControllerImpl implements RoomController {

    @Autowired
    RoomService roomService;

    @Override
    public ResponseEntity<?> createRoom(CreateRoomRequest createRoomRequest) {
        try{
            Room room = roomService.createRoom(createRoomRequest);
            return new ResponseEntity<>(room , HttpStatus.CREATED);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public ResponseEntity<?> updateRoom(CreateRoomRequest createRoomRequest) {
        try{
            Room room = roomService.updateRoom(createRoomRequest);
            return ResponseEntity.ok(room);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public ResponseEntity<?> findRoomByConditions(String location, String type, long number , int page , int size) {
        try{
            return ResponseEntity.ok(roomService.getRoomByConditions(location , type , number , page , size));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public ResponseEntity<?> findHiringRoom() {
        try {
            return ResponseEntity.ok(roomService.getRentedRooms());
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }
}
