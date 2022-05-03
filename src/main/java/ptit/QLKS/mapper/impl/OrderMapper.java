package ptit.QLKS.mapper.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import ptit.QLKS.dto.OrderDTO;
import ptit.QLKS.dto.OrderHistoryDTO;
import ptit.QLKS.dto.ResponseOrderDTO;
import ptit.QLKS.entity.Order;
import ptit.QLKS.mapper.impl.AccountMapper;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

@Component
public class OrderMapper {

    @Resource
    private AccountMapper accountMapper;

    public Order toEntity(OrderDTO dto){
        Order order = new Order();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            order.init();
            order.setEndDate(null);
            order.setStartDate(simpleDateFormat.parse(dto.getStartDate()));
            order.setNote(dto.getNote());
        }
        catch (Exception e){
            return null;
        }
        return order;
    }

    public ResponseOrderDTO toDto(Order order){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ResponseOrderDTO dto = new ResponseOrderDTO();
        dto.setRoomId(order.getRoom().getId());
        dto.setNote(order.getNote());
        dto.setAccountDTO(accountMapper.toDto(order.getAccount()));
        dto.setStatus(order.getStatus());
        if(!ObjectUtils.isEmpty(order.getStartDate())){
            dto.setStartDate(simpleDateFormat.format(order.getStartDate()));
        }
        else {
            dto.setStartDate(null);
        }

        if(!ObjectUtils.isEmpty(order.getEndDate())){
            dto.setEndDate(simpleDateFormat.format(order.getEndDate()));
        }
        else {
            dto.setEndDate(null);
        }

        return dto;
    }

    public OrderHistoryDTO toHistoryDto(Order order){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        OrderHistoryDTO dto = new OrderHistoryDTO();
        dto.setNote(order.getNote());
        dto.setStatus(order.getStatus());
        if(!ObjectUtils.isEmpty(order.getStartDate())){
            dto.setStartDate(simpleDateFormat.format(order.getStartDate()));
        }
        else {
            dto.setStartDate(null);
        }

        if(!ObjectUtils.isEmpty(order.getEndDate())){
            dto.setEndDate(simpleDateFormat.format(order.getEndDate()));
        }
        else {
            dto.setEndDate(null);
        }

        return dto;
    }
}
