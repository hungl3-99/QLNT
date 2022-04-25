package ptit.QLKS.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception{
    public CustomException(String message){
        super(message);
    }
}
