package ptit.QLKS.repository;

import ptit.QLKS.entity.Room;

import java.util.List;

public interface RoomCustomRepository {
    List<Room> getRoomByCondition(String location , String type , long number, String store, int page , int size , long totalElement);
    Long getTotalElement(String location , String type , long number , String store);
}
