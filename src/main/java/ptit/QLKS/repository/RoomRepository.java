package ptit.QLKS.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ptit.QLKS.entity.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room , String> {
    @Query(value = "select room from Room room where (room.location like %:location% or :location is null ) and " +
            "(room.type like %:type% or:type is null) or (room.maxNumberPeople <= :number)")
    Page<Room> getRoomByCondition(@Param("location") String location , @Param("type") String type , @Param("number") int number , Pageable pageable);


    @Query(value = "select room from Room room where room.store.id = :store and room.isValid = false and room.isBooking = true ")
    Page<Room> getRoomOfCurrentStore(@Param("store") String store , Pageable pageable);
}
