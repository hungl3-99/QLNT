package ptit.QLKS.service.impl;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ptit.QLKS.service.UploadService;
import ptit.QLKS.vo.BaseResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
@Log4j2
public class FileUploadImpl implements UploadService {

    public static final String PATH_TO_FILE_STORE ="src/main/resources/static/images";

    @Override
    public BaseResponse UploadFile(MultipartFile[] files, String id) {
        String dictionary = PATH_TO_FILE_STORE +"/" + id + "/";
        log.info(dictionary);
        System.out.println(dictionary);
        File theDir = new File(dictionary);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }

        for (MultipartFile file : files) {
            File myFile = new File(dictionary + converseFileName(file.getOriginalFilename()));

            try {
                if (file.getSize() / Math.pow(1024, 2) > 5) {
                    return BaseResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "File cannot be larger than 5MB" , null);
                }

                if (Stream.of("pdf", "txt", "csv", "mp4", "jpg", "jpeg", "docx", "xlsx", "png", "pptx").anyMatch(FilenameUtils.getExtension(file.getOriginalFilename())::equalsIgnoreCase)) {
                    myFile.createNewFile();
                    FileOutputStream fos = new FileOutputStream(myFile);
                    fos.write(file.getBytes());
                    fos.close();
                } else {
                    return BaseResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Invalid Extension !!!" , null);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return BaseResponse.success(HttpStatus.OK, "Success !!!" , null);
    }

    public String converseFileName(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }
}
