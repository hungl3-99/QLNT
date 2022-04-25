package ptit.QLKS.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.QLKS.entity.Account;
import ptit.QLKS.entity.Order;
import ptit.QLKS.entity.Room;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order , String> {
    List<Order> findByAccountAndRoomAndStatus(Account account , Room room , String status);
    List<Order> findByRoomAndStatus(Room room , String status);
    List<Order> findByAccountAndStatus(Account account , String status);
}
