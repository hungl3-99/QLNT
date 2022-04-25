package ptit.QLKS.constrant;

import lombok.Getter;

public class Constrant {
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_STORE = "ROLE_STORE";
    public static final String CANNOT_UPDATE_ROLE_FOR_THIS_ACCOUNT ="can't update role for this account !!!";
    public static final String SUCCESS = "success";
    public static final String CREATE_ORDER_FAILURE = "CREATE_ORDER_FAILURE ";
    public static final String YOUR_ORDER_IS_ALREADY_EXIST = "YOUR_ORDER_IS_ALREADY_EXIST";
    public static final String USERNAME_ARE_ALREADY_EXIST = "USERNAME_ARE_ALREADY_EXIST";
    public static final String INVALID_USERNAME = "INVALID_USERNAME ";
    public static final String INVALID_USERNAME_OR_PASSWORD = "Tài khoản hoặc mật khẩu không đúng";
    public static final String YOU_DONT_HAVE_PERMISSION_TO_DO_THIS_ACTION = "YOU_DONT_HAVE_PERMISSION_TO_DO_THIS_ACTION !!!";
    public static final String SOMETHING_WENT_WRONG = "Lỗi hệ thống !!!";
    public static final String WRONG_PASSWORD = "Sai mật khẩu !!!";

    @Getter
    public enum SystemStatus{
        PENDING(1,"PENDING"),
        APPROVED(2,"APPROVED"),
        REJECT(3,"REJECT"),
        PUBLISH(4 , "PUBLISH"),
        CLOSED(5 , "CLOSED"),
        WAIT(6 , "WAIT"),
        HIRING(7 , "HIRING");
        private int key;
        private String value;
        SystemStatus(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
