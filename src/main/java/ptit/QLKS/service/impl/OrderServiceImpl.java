package ptit.QLKS.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ptit.QLKS.constrant.Constrant;
import ptit.QLKS.dto.*;
import ptit.QLKS.entity.Account;
import ptit.QLKS.entity.Bill;
import ptit.QLKS.entity.Order;
import ptit.QLKS.entity.Room;
import ptit.QLKS.mapper.impl.BillMapper;
import ptit.QLKS.mapper.impl.OrderMapper;
import ptit.QLKS.mapper.impl.RoomMapper;
import ptit.QLKS.repository.AccountRepository;
import ptit.QLKS.repository.BillRepository;
import ptit.QLKS.repository.OrderRepository;
import ptit.QLKS.repository.RoomRepository;
import ptit.QLKS.service.OrderService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderRepository orderRepository;

    @Resource
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Resource
    RoomRepository roomRepository;

    @Resource
    OrderMapper orderMapper;

    @Resource
    RoomMapper roomMapper;

    @Resource
    BillRepository billRepository;

    @Resource
    BillMapper billMapper;

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
            order.setStoreId(orderRoom.getStore().getId());
            order.setAccount(currentAccount);
            order.setStatus(Constrant.SystemStatus.APPROVED.getValue());
            orderRoom.setBooking(true);orderRoom.setValid(false);
            setCreatedOrder(order , currentAccount.getUsername());
            roomRepository.save(orderRoom);
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

    @Override
    public List<OrderHistoryDTO> getOrderHistory() {
        Account account = accountService.getLoginUserInFo();
        List<Order> orders = orderRepository.findByAccount(account);
        List<OrderHistoryDTO> result = new ArrayList<>();
        if(!ObjectUtils.isEmpty(orders) && !orders.isEmpty()){
            for (Order order : orders){
                OrderHistoryDTO orderHistoryDTO = orderMapper.toHistoryDto(order);
                orderHistoryDTO.setRoomDTO(roomMapper.toDTO(order.getRoom()));
                List<Bill> bills = billRepository.findByOrder(order);
                if(!ObjectUtils.isEmpty(billMapper) && !bills.isEmpty()){
                    List<BillDTO> billDTOS = billMapper.toListDto(bills);
                    orderHistoryDTO.setBillDTO(billDTOS);
                }
                result.add(orderHistoryDTO);
            }
        }

        return result;
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

    @Override
    public List<RoomDTO> getRentedRooms() {
        Account account = accountRepository.findByUsername(getLoginUser());
        String id = account.getId();
        List<Order> activeOrder = orderRepository.findByStoreIdAndStatus(id , Constrant.SystemStatus.APPROVED.getValue());
        List<Room> rooms = null;
        if(!ObjectUtils.isEmpty(activeOrder)){
            rooms = activeOrder.stream().map(Order::getRoom).collect(Collectors.toList());
        }
        return roomMapper.toListDto(rooms);
    }

    private String getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        String username = ((UserDetails)principal).getUsername();
        return username;
    }
}
