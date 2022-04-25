package ptit.QLKS.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    private boolean success;
    private int code;
    private T data;
    private String message;

    public static <T> BaseResponse<T> success(HttpStatus status, String message, T data) {
        return BaseResponse.<T>builder()
                .success(true)
                .code(status.value())
                .message(message)
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> error(HttpStatus status, String message, T data) {
        return BaseResponse.<T>builder()
                .success(false)
                .code(status.value())
                .message(message)
                .data(data)
                .build();
    }
}
