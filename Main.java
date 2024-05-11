import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        UserDataManager accNum_pinCode = new UserDataManager();

        @SuppressWarnings("unchecked")
        HashMap<String, String> loginInfo = accNum_pinCode.getLoginInfo();

        if (loginInfo != null) {
            LoginFrame login = new LoginFrame(loginInfo);
        }
    }
}
