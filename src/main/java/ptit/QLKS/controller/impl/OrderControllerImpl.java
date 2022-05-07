package ptit.QLKS.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;
import ptit.QLKS.constrant.Constrant;
import ptit.QLKS.controller.OrderController;
import ptit.QLKS.dto.OrderDTO;
import ptit.QLKS.dto.OrderHistoryDTO;
import ptit.QLKS.dto.UpdateStatusOrderDTO;
import ptit.QLKS.entity.Order;
import ptit.QLKS.mapper.impl.OrderMapper;
import ptit.QLKS.service.OrderService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class OrderControllerImpl implements OrderController {

    @Resource
    OrderService orderService;

    @Resource
    OrderMapper orderMapper;

    @Override
    public ResponseEntity<?> createOrderByUser(OrderDTO dto) {
        try {
            Order order = orderService.createOrderInRoomPageByUser(dto);
            if(ObjectUtils.isEmpty(order)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(orderMapper.toDto(order) , HttpStatus.CREATED);
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public ResponseEntity<?> updateStatusOrder(UpdateStatusOrderDTO dto) {
        try{
            Order order = orderService.updateStatusOrder(dto);
            if(ObjectUtils.isEmpty(order)){
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
            return ResponseEntity.ok(orderMapper.toDto(order));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }

    }

    @Override
    public ResponseEntity<?> getHiringRoomOfCurrentUser() {
        try {
            List<OrderHistoryDTO> list = orderService.getOrderHistory();
            return ResponseEntity.ok(list);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> findHiringRoom() {
        try {
            return ResponseEntity.ok(orderService.getRentedRooms());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Constrant.SOMETHING_WENT_WRONG);
        }
    }
}
