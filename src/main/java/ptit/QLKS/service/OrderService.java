package ptit.QLKS.service;

import ptit.QLKS.dto.OrderDTO;
import ptit.QLKS.dto.OrderHistoryDTO;
import ptit.QLKS.dto.UpdateStatusOrderDTO;
import ptit.QLKS.entity.Order;
import ptit.QLKS.entity.Room;

import java.util.List;


public interface OrderService {
    Order createOrderInRoomPageByUser(OrderDTO dto);
    Order updateStatusOrder(UpdateStatusOrderDTO dto);
    List<OrderHistoryDTO>  getOrderHistory();
    public List<Room> getRentedRooms();
}
