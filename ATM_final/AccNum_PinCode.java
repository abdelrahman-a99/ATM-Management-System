import java.io.FileReader;
import java.io.FileWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.IOException;

import java.util.HashMap;

public class AccNum_PinCode {

    // AccNum_PinCode() {
    // loginInfo.put("abdelrahman", "01200");
    // loginInfo.put("fzlka", "0000");
    // loginInfo.put("Bro123", "1234");
    // }

    HashMap<String, String> loginInfo = new HashMap<String, String>();

    AccNum_PinCode() {
        // try (FileReader fr = new FileReader("LoginInfo.txt")) {
        // String line;
        // while ((line = fr.readLine()) != null) { // ---> this line is gave me an
        // error
        // String[] parts = line.split(":");
        // loginInfo.put(parts[0], parts[1]);
        // }
        // }
        // try (BufferedReader br = new BufferedReader(new FileReader("LoginInfo.txt")))
        // {
        // String line;
        // while ((line = br.readLine()) != null) {
        // String[] parts = line.split(":");
        // loginInfo.put(parts[0], parts[1]);
        // }
        // }
        try (BufferedReader br = new BufferedReader(new FileReader("LoginInfo.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    // Contains only accNum and pinCode
                    loginInfo.put(parts[0], parts[1]);
                } else if (parts.length == 9) {
                    // Contains all user data
                    loginInfo.put(parts[0], parts[4]); // Assuming accNum is at index 0 and pinCode at index 4
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String accNum, String pinCode) {
        loginInfo.put(accNum, pinCode);
        writeToFile();
    }

    public void addUser(String accNum, String pinCode, String userData) {
        loginInfo.put(accNum, pinCode);
        writeToFile();
        writeToFile(userData);
    }

    private void writeToFile() {
        // try (FileWriter fw = new FileWriter("LoginInfo.txt")) {
        // for (String Key : loginInfo.keySet()) {
        // fw.write(Key + ":" + loginInfo.get(Key));
        // fw.write("\n");
        // }
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("LoginInfo.txt"))) {
            for (String key : loginInfo.keySet()) {
                bw.write(key + ":" + loginInfo.get(key));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(String userData) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("RegisterInfo.txt", true))) {
            bw.write(userData);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    protected HashMap getLoginInfo() {
        return loginInfo;
    }
}
