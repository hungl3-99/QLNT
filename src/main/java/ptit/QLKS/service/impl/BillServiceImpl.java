package ptit.QLKS.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import ptit.QLKS.constrant.Constrant;
import ptit.QLKS.dto.*;
import ptit.QLKS.entity.Account;
import ptit.QLKS.entity.Bill;
import ptit.QLKS.entity.Order;
import ptit.QLKS.entity.Room;
import ptit.QLKS.mapper.impl.BillMapper;
import ptit.QLKS.repository.BillRepository;
import ptit.QLKS.repository.OrderRepository;
import ptit.QLKS.repository.RoomRepository;
import ptit.QLKS.service.BillService;
import ptit.QLKS.vo.BaseResponse;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class BillServiceImpl implements BillService {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    AccountService accountService;

    @Autowired
    BillRepository billRepository;

    @Autowired
    BillMapper billMapper;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public BillDTO createBill(CreateBillDTO dto) {
        try {
            Bill bill = new Bill();
            Room room  = roomRepository.getById(dto.getRoomId());
            Account store = accountService.getLoginUserInFo();
            if(!store.equals(room.getStore())){
                throw new IllegalArgumentException(Constrant.YOU_DONT_HAVE_PERMISSION_TO_DO_THIS_ACTION);
            }

            Order order = orderRepository.findLastestOrderOfRoom(dto.getRoomId() , getLastDayOfMonth(dto.getMonth() , dto.getYear()));
            if(!ObjectUtils.isEmpty(order)){
                if(order.getStatus().equalsIgnoreCase(Constrant.SystemStatus.APPROVED.getValue())){
                    bill.setMonth(dto.getMonth());
                    bill.setYear(dto.getYear());
                    bill.setElectricNumber(dto.getElectricNumber());
                    bill.setWaterNumber(dto.getWaterNumber());
                    bill.setNetworkNumber(dto.getNetworkNumber());
                    bill.setOrder(order);
                    bill.setTotalElectricPrice(room.getElectricPrice() * dto.getElectricNumber());
                    bill.setTotalWaterPrice(room.getWaterPrice() * dto.getWaterNumber());
                    bill.setTotalNetworkPrice(room.getNetworkPrice() * dto.getNetworkNumber());
                    System.out.println(room.getWaterPrice() * dto.getWaterNumber() + " " + room.getNetworkPrice() * dto.getNetworkNumber());
                    bill.setTotalBill(room.getElectricPrice() * dto.getElectricNumber() + room.getWaterPrice() * dto.getWaterNumber()
                            + room.getNetworkPrice() * dto.getNetworkNumber());
                    bill.setStatus(Constrant.SystemStatus.UNPAID.getValue());
                    billRepository.save(bill);
                    TimeUnit.SECONDS.sleep(1);
                    return billMapper.toDTO(bill);
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Bill> getBillByConditions(String status , String findValue , int page , int size) {
        PageRequest pageRequest = PageRequest.of(page , size);
        Page<Bill> bills = billRepository.findByCondition(status , findValue , pageRequest);
        return bills;
    }

    @Override
    public BillDTO getBillById(int id) {
        return billMapper.toDTO(billRepository.getById(id));
    }

    @Override
    public StatisticalDTO getStatisticalByStore(String store) {
        List<Bill> list = billRepository.getBillByStore(store);
        long paid = billRepository.getSumByConditions(Constrant.SystemStatus.PAID.getValue(), store);
        long unpaid = billRepository.getSumByConditions(Constrant.SystemStatus.UNPAID.getValue(), store);
        long total = paid + unpaid;
        StatisticalDTO statisticalDTO = new StatisticalDTO();
        statisticalDTO.setTotalPaid(paid);
        statisticalDTO.setTotalUnpaid(unpaid);
        statisticalDTO.setTotal(total);
        if(!ObjectUtils.isEmpty(list)){
            statisticalDTO.setListBill(billMapper.toListDto(list));
        }
        return statisticalDTO;
    }

    @Override
    public BaseResponse<BillDTO> updateStatus(UpdateStatusBillDTO updateStatusBillDTO) {
        Bill bill = billRepository.getById(updateStatusBillDTO.getId());
        if(!ObjectUtils.isEmpty(bill)){
            String store = bill.getOrder().getRoom().getStore().getUsername();
            if(!store.equalsIgnoreCase(getLoginUser())){
                throw new IllegalArgumentException(Constrant.YOU_DONT_HAVE_PERMISSION_TO_DO_THIS_ACTION);
            }
            bill.setStatus(updateStatusBillDTO.getStatus());
        }
        return BaseResponse.success(HttpStatus.OK , Constrant.SUCCESS , billMapper.toDTO(bill));
    }

    @Override
    public BillDTO updateBill(BillDTO billDTO) {
        Bill bill = billMapper.toEntity(billDTO);
        return billMapper.toDTO(bill);
    }

    @Override
    @Transactional
    public BaseResponse<?> checkOut(CreateBillDTO dto) {
        BillDTO billDTO = createBill(dto);
        if(!ObjectUtils.isEmpty(billDTO)){
            Room room = roomRepository.getById(dto.getRoomId());
            if(!room.getStore().getUsername().equalsIgnoreCase(getLoginUser())){
                throw new IllegalArgumentException(Constrant.YOU_DONT_HAVE_PERMISSION_TO_DO_THIS_ACTION);
            }
            Order order = orderRepository.findLastestOrderOfRoom(dto.getRoomId() , new Date());
            if(ObjectUtils.isEmpty(order)){
                order.setEndDate(new Date());
                order.setStatus("CLOSED");
                order.setUpdatedBy(getLoginUser());
                order.setUpdatedAt(new Date());
                orderRepository.save(order);
            }

            room.setValid(true);
            room.setBooking(false);
            room.setUpdatedAt(new Date());
            room.setUpdatedBy(getLoginUser());

            return BaseResponse.success(HttpStatus.OK , Constrant.SUCCESS , null);
        }
        return null;
    }



    private String getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        return username;
    }

    private Date getLastDayOfMonth(int month , int year){
        String date = month + "/01/"+ year;
        LocalDate lastDayOfMonth = LocalDate.parse(date, DateTimeFormatter.ofPattern("M/dd/yyyy"))
                .with(TemporalAdjusters.lastDayOfMonth());
        return Date.from(lastDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
