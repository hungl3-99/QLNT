package ptit.QLKS.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ListResponse<T> implements Serializable {
    private boolean success;
    private int code;
    private T data;
    private String message;
    private long totalElement;

    public static <T> ListResponse<T> success(HttpStatus status, String message, T data , long totalElement) {
        return ListResponse.<T>builder()
                .success(true)
                .code(status.value())
                .message(message)
                .data(data)
                .totalElement(totalElement)
                .build();
    }
}

