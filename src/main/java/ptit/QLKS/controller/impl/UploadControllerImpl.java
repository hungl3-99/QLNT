package ptit.QLKS.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ptit.QLKS.controller.UploadController;
import ptit.QLKS.service.UploadService;
import ptit.QLKS.vo.BaseResponse;

import javax.annotation.Resource;

@RestController
public class UploadControllerImpl implements UploadController {

    @Resource
    UploadService uploadService;

    @Override
    public ResponseEntity<?> FileUpload(MultipartFile[] files) {
        BaseResponse baseResponseDTO = uploadService.UploadFile(files);
        return ResponseEntity.ok(baseResponseDTO);
    }
}
