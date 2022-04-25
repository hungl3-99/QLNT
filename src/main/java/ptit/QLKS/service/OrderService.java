package ptit.QLKS.service;

import ptit.QLKS.dto.OrderDTO;
import ptit.QLKS.dto.UpdateStatusOrderDTO;
import ptit.QLKS.entity.Order;


public interface OrderService {
    Order createOrderInRoomPageByUser(OrderDTO dto);
    Order updateStatusOrder(UpdateStatusOrderDTO dto);
}
