package ptit.QLKS.controller.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ptit.QLKS.constrant.Constrant;
import ptit.QLKS.controller.RoomController;
import ptit.QLKS.entity.Room;
import ptit.QLKS.mapper.impl.RoomMapper;
import ptit.QLKS.service.RoomService;
import ptit.QLKS.vo.CreateRoomRequest;


@RestController
@Log4j2
public class RoomControllerImpl implements RoomController {

    @Autowired
    RoomService roomService;

    @Autowired
    RoomMapper roomMapper;

    @Override
    public ResponseEntity<?> createRoom(CreateRoomRequest createRoomRequest) {
        try{
            Room room = roomService.createRoom(createRoomRequest);
            return new ResponseEntity<>(room , HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public ResponseEntity<?> updateRoom(CreateRoomRequest createRoomRequest) {
        try{
            Room room = roomService.updateRoom(createRoomRequest);
            return ResponseEntity.ok(roomMapper.toDTO(room));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public ResponseEntity<?> findRoomByConditions(String location, String type, long number ,String store, int page , int size) {
        try{
            return ResponseEntity.ok(roomService.getRoomByConditions(location , type , number ,store, page , size));
        }
        catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }


    @Override
    public ResponseEntity<?> getRoomById(String id) {
        try {
            return ResponseEntity.ok(roomMapper.toDTO(roomService.getRoomById(id)));
        }
        catch (Exception e){
            return null;
        }
    }
}
