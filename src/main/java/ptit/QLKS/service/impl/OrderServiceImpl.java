package ptit.QLKS.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ptit.QLKS.constrant.Constrant;
import ptit.QLKS.dto.OrderDTO;
import ptit.QLKS.dto.UpdateStatusOrderDTO;
import ptit.QLKS.entity.Account;
import ptit.QLKS.entity.Order;
import ptit.QLKS.entity.Room;
import ptit.QLKS.mapper.impl.OrderMapper;
import ptit.QLKS.repository.OrderRepository;
import ptit.QLKS.repository.RoomRepository;
import ptit.QLKS.service.OrderService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderRepository orderRepository;

    @Resource
    AccountService accountService;

    @Resource
    RoomRepository roomRepository;

    @Resource
    OrderMapper orderMapper;

    @Override
    public Order createOrderInRoomPageByUser(OrderDTO dto) {
        Account currentAccount = accountService.getLoginUserInFo();
        Order order = orderMapper.toEntity(dto);
        Room orderRoom = validateRoom(dto.getRoomId());
        if(!ObjectUtils.isEmpty(orderRepository.findByAccountAndRoomAndStatus(currentAccount , orderRoom , Constrant.SystemStatus.PENDING.getValue()))){
            throw new IllegalArgumentException(Constrant.YOUR_ORDER_IS_ALREADY_EXIST);
        }
        if(!ObjectUtils.isEmpty(orderRoom)){
            order.setRoom(orderRoom);
            order.setAccount(currentAccount);
            order.setStatus(Constrant.SystemStatus.PENDING.getValue());
            setCreatedOrder(order , currentAccount.getUsername());
            return orderRepository.save(order);
        }
        return null;
    }


    @Override
    public Order updateStatusOrder(UpdateStatusOrderDTO dto) {
        Account account = accountService.getLoginUserInFo();
        Order order = orderRepository.getById(dto.getId());
        if(!account.equals(order.getRoom().getStore())){
            throw new RuntimeException(Constrant.YOU_DONT_HAVE_PERMISSION_TO_DO_THIS_ACTION);
        }
        Room room = order.getRoom();
        room.setBooking(true);room.setValid(false);
        order.setStatus(dto.getStatus());
        setUpdatedOrder(order , account.getUsername());
        orderRepository.save(order);
        roomRepository.save(room);
        // firebase message
        if(dto.getStatus().equalsIgnoreCase(Constrant.SystemStatus.APPROVED.getValue())){
            List<Order> orders = orderRepository.findByRoomAndStatus(order.getRoom() , Constrant.SystemStatus.PENDING.getValue());
            if(!ObjectUtils.isEmpty(orders)){
                for (Order ord : orders){
                    ord.setStatus(Constrant.SystemStatus.REJECT.getValue());
                    setUpdatedOrder(ord , account.getUsername());
                    orderRepository.save(ord);
                }
            }
        }
        return order;
    }

    private Room validateRoom(String roomID){
        Room room = roomRepository.getById(roomID);
        if(!ObjectUtils.isEmpty(room) || !room.isValid()){
            return room;
        }
        return null;
    }

    private void setCreatedOrder(Order order , String username){
        order.setCreatedAt(new Date());
        order.setCreatedBy(username);
    }

    private void setUpdatedOrder(Order order , String username){
        order.setUpdatedAt(new Date());
        order.setUpdatedBy(username);
    }
}
