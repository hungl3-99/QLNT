package ptit.QLKS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import ptit.QLKS.entity.Account;
import ptit.QLKS.entity.Order;
import ptit.QLKS.entity.Room;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order , String> {
    List<Order> findByAccountAndRoomAndStatus(Account account , Room room , String status);
    List<Order> findByRoomAndStatus(Room room , String status);

    List<Order> findByStoreIdAndStatus(@Param("store_id") String store_id ,@Param("status") String status);

    List<Order> findByAccount(Account account);

    @Query("select o from Order o where o.room.id = :room and o.status = 'APPROVED' and o.startDate <= :date")
    Order findLastestOrderOfRoom(@Param("room") String room , @Param("date")Date date);
}