package ptit.QLKS.service.impl;

import lombok.extern.log4j.Log4j2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ptit.QLKS.constrant.Constrant;
import ptit.QLKS.dto.RoomDTO;
import ptit.QLKS.entity.Account;
import ptit.QLKS.entity.Order;
import ptit.QLKS.entity.Room;
import ptit.QLKS.mapper.impl.RoomMapper;
import ptit.QLKS.repository.AccountRepository;
import ptit.QLKS.repository.OrderRepository;
import ptit.QLKS.repository.RoomCustomRepository;
import ptit.QLKS.repository.RoomRepository;
import ptit.QLKS.service.RoomService;
import ptit.QLKS.vo.BaseResponse;
import ptit.QLKS.vo.CreateRoomRequest;
import ptit.QLKS.vo.ListResponse;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class RoomServiceImpl implements RoomService {

    @Resource
    RoomMapper roomMapper;

    @Resource
    RoomRepository roomRepository;

    @Resource
    AccountRepository accountRepository;

    @Resource
    RoomCustomRepository roomCustomRepository;

    @Resource
    OrderRepository orderRepository;


    @Override
    public Room createRoom(CreateRoomRequest createRoomRequest) {
        String username = getLoginUser();
        Account account = accountRepository.findByUsername(username);

        Room room = roomMapper.toEntity(createRoomRequest);
        room.init();
        room.setCreatedAt(new Date());
        room.setCreatedBy(getLoginUser());
        room.setStore(account);

        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(CreateRoomRequest updateRoomRequest) {
        String username = getLoginUser();

        Account account = accountRepository.findByUsername(username);
        Optional<Room> oldInfo = roomRepository.findById(updateRoomRequest.getId());

        if(ObjectUtils.isEmpty(oldInfo)){
            throw new RuntimeException("Room is not Exist !!!");
        }

        Room room = oldInfo.get();

        if(!room.getStore().equals(account)){
            throw new IllegalArgumentException("UNAUTHORIZED  !!!");
        }

        room = roomMapper.toEntity(updateRoomRequest);
        return roomRepository.save(room);
    }

    @Override
    public ListResponse<?> getRoomByConditions(String location, String type, long number ,String store, int page , int size) {
        long totalElement = roomCustomRepository.getTotalElement(location , type , number , store);
        List<Room> result = roomCustomRepository.getRoomByCondition(location ,type , number,store ,page , size , totalElement);
        return ListResponse.success(HttpStatus.OK , "Success" , roomMapper.toListDto(result) , totalElement);
    }


    @Override
    public Room getRoomById(String id) {
        return roomRepository.getById(id);
    }

    @Override
    public Page<Room> getRoomOfCurrentStore(int page , int size) {
        PageRequest pageRequest = PageRequest.of(page , size);
        return roomRepository.getRoomOfCurrentStore(getLoginUser() , pageRequest);
    }


    private String getLoginUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(principal);
        String username = ((UserDetails)principal).getUsername();
        return username;
    }
}
