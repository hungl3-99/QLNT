package ptit.QLKS.service;

import org.springframework.data.domain.Page;
import ptit.QLKS.dto.*;
import ptit.QLKS.entity.Bill;
import ptit.QLKS.vo.BaseResponse;



public interface BillService {
    BillDTO createBill(CreateBillDTO dto);
    Page<BillDTO> getBillByConditions(String status , String findValue , int page , int size);
    BillDTO getBillById(int id);
    StatisticalDTO getStatisticalByStore(String store);
    BaseResponse<BillDTO> updateStatus(UpdateStatusBillDTO updateStatusBillDTO);
    BillDTO updateBill(BillDTO billDTO);
    BaseResponse<?> checkOut(CreateBillDTO dto);
    void deleteBill(int id);
}
