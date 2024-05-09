import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        AccNum_PinCode accNum_pinCode = new AccNum_PinCode();

        @SuppressWarnings("unchecked")
        HashMap<String, String> loginInfo = accNum_pinCode.getLoginInfo();

        if (loginInfo != null) {
            LoginFrame login = new LoginFrame(loginInfo);
        }
    }
}
