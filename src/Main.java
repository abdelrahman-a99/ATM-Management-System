import java.util.HashMap;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {

        userDataManager accNum_pinCode = new userDataManager();

        LinkedList<String> stringLinkedList = new LinkedList<String>();

        @SuppressWarnings("unchecked")

        HashMap<String, String> loginInfo = accNum_pinCode.getLoginInfo();

        if (loginInfo != null) {
            LoginFrame login = new LoginFrame(loginInfo);
        }
    }
}