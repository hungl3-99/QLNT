package ptit.QLKS.mapper;

import org.springframework.stereotype.Component;
import ptit.QLKS.dto.RoomDTO;
import ptit.QLKS.entity.Room;

@Component
public interface RoomCustomMapper extends MapStructMapper<Room , RoomDTO>{

    @Override
    RoomDTO toDTO(Room entity);
}
