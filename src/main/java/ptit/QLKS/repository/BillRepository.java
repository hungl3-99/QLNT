package ptit.QLKS.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ptit.QLKS.entity.Bill;
import ptit.QLKS.entity.Order;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill , Integer> {
    @Query("select u from Bill u where ((u.order.room.roomName like %:value% or u.order.room.roomName is null ) or (u.order.account.username like %:value% or u.order.account.username is null )) and u.status = :status ")
    Page<Bill> findByCondition(@Param("status") String status , @Param("value") String value , Pageable pageable);

    Bill findByMonthAndYearAndOrder(@Param("month")int month ,@Param("year") int year ,@Param("order") Order order);

    List<Bill> findByOrder(@Param("order") Order order);

    @Query("select COALESCE(sum(i.totalBill),0) from Bill i where i.status = :status and i.order.storeId= :store")
    Long getSumByConditions(@Param("status") String status , @Param("store") String store);

    @Query("select i from Bill i where i.order.storeId= :store")
    List<Bill> getBillByStore(@Param("store") String store);
}
