package ptit.QLKS.service;


import org.springframework.web.multipart.MultipartFile;
import ptit.QLKS.vo.BaseResponse;

public interface UploadService {
    BaseResponse UploadFile(MultipartFile[] files, String id);
}
