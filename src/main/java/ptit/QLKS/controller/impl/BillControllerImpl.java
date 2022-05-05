package ptit.QLKS.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ptit.QLKS.constrant.Constrant;
import ptit.QLKS.controller.BillController;
import ptit.QLKS.dto.BillDTO;
import ptit.QLKS.dto.CreateBillDTO;
import ptit.QLKS.dto.UpdateStatusBillDTO;
import ptit.QLKS.service.BillService;

@RestController
public class BillControllerImpl implements BillController {

    @Autowired
    BillService billService;

    @Override
    public ResponseEntity<?> createBill(CreateBillDTO createBillDTO) {
        try {
            return ResponseEntity.ok(billService.createBill(createBillDTO));
        }
        catch (IllegalArgumentException illegalArgumentException){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage() , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getBillByConditions(String status,String findValue, int page, int size) {
        try {
            return ResponseEntity.ok(billService.getBillByConditions(status , findValue , page , size));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getBillById(int id) {
        try {
            return ResponseEntity.ok(billService.getBillById(id));
        }
        catch (Exception e){
            return new ResponseEntity<>(Constrant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getStatisticalByStore(String store_id) {
        try {
            return ResponseEntity.ok(billService.getStatisticalByStore(store_id));
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Constrant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateBillStatus(UpdateStatusBillDTO updateStatusBillDTO) {
        try {
            return ResponseEntity.ok(billService.updateStatus(updateStatusBillDTO));
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage() , HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Constrant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateBill(BillDTO billDTO) {
        try {
            return ResponseEntity.ok(billService.updateBill(billDTO));
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Constrant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> checkout(CreateBillDTO createBillDTO) {
        try {
            return ResponseEntity.ok(billService.checkOut(createBillDTO));
        }
        catch (IllegalArgumentException illegalArgumentException){
            return new ResponseEntity<>(illegalArgumentException.getMessage() , HttpStatus.UNAUTHORIZED);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(Constrant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
