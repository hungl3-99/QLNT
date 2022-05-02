package ptit.QLKS.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import ptit.QLKS.dto.BillDTO;
import ptit.QLKS.entity.Bill;
import ptit.QLKS.repository.BillRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class BillMapper {

    public BillDTO toDTO(Bill bill){
        try{
            BillDTO billDTO = new BillDTO();
            billDTO.setId(bill.getId());
            billDTO.setMonth(bill.getMonth());
            billDTO.setYear(bill.getYear());
            billDTO.setElectricNumber(bill.getElectricNumber());
            billDTO.setWaterNumber(bill.getWaterNumber());
            billDTO.setNetworkNumber(bill.getNetworkNumber());
            billDTO.setUsername(bill.getOrder().getAccount().getUsername());
            billDTO.setRoomName(bill.getOrder().getRoom().getRoomName());
            billDTO.setStoreName(bill.getOrder().getRoom().getStore().getUsername());
            billDTO.setTotalElectricPrice(bill.getTotalElectricPrice());
            billDTO.setTotalWaterPrice(bill.getTotalNetworkPrice());
            billDTO.setTotalNetworkPrice(bill.getTotalNetworkPrice());
            billDTO.setTotalBill(bill.getTotalBill());
            billDTO.setStatus(bill.getStatus());
            return billDTO;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Bill toEntity(BillDTO billDTO){
        try{
            Bill bill = new Bill();
            bill.setId(billDTO.getId());
            bill.setMonth(billDTO.getMonth());
            bill.setYear(billDTO.getYear());
            bill.setElectricNumber(billDTO.getElectricNumber());
            bill.setWaterNumber(billDTO.getWaterNumber());
            bill.setNetworkNumber(billDTO.getNetworkNumber());
            bill.setTotalElectricPrice(billDTO.getTotalElectricPrice());
            bill.setTotalWaterPrice(billDTO.getTotalNetworkPrice());
            bill.setTotalNetworkPrice(billDTO.getTotalNetworkPrice());
            bill.setTotalBill(billDTO.getTotalBill());
            bill.setStatus(billDTO.getStatus());
            return bill;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    public List<BillDTO> toListDto(List<Bill> list){
        List<BillDTO> result = new ArrayList<>();
        for(Bill a:list){
            result.add(toDTO(a));
        }
        return result;
    }
}
