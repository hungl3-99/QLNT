package ptit.QLKS.service;

import org.springframework.data.domain.Page;
import ptit.QLKS.dto.*;
import ptit.QLKS.entity.Bill;
import ptit.QLKS.vo.BaseResponse;



public interface BillService {
    BillDTO createBill(CreateBillDTO dto);
    Page<Bill> getBillByConditions(String status , String findValue , int page , int size);
    BillDTO getBillById(int id);
    StatisticalDTO getStatisticalByStore(String store , int year , int month);
    BaseResponse<BillDTO> updateStatus(UpdateStatusBillDTO updateStatusBillDTO);
    BillDTO updateBill(BillDTO billDTO);
    BaseResponse<?> checkOut(CreateBillDTO dto);
}
