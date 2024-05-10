import java.io.FileReader;
import java.io.FileWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.IOException;

import java.util.HashMap;

public class userDataManager {

    HashMap<String, String> userDataMap = new HashMap<String, String>();

    userDataManager() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/UserInfo.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if(parts.length==1){
                }
                   else { // Contains all user data
                    userDataMap.put(parts[0], line); // accNum and it's associated data
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUserData() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/UserInfo.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                userDataMap.put(parts[0], line);  // Store the account number and the full user data line
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String accNum, String fullUserData) {
        userDataMap.put(accNum, fullUserData);
        writeToFile(fullUserData, true);
    }

    private void writeToFile(String data, boolean append) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/UserInfo.txt", append))) {
            bw.write(data);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateUserData(String accNum, String newUserData) {
        // Replace the old data with the new data
        userDataMap.put(accNum, newUserData);
        // Rewrite to file to reflect these changes
        rewriteFullFile();
    }

    private void rewriteFullFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/UserInfo.txt", false))) {
            for (String data : userDataMap.values()) {
                bw.newLine();
                bw.write(data);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("rawtypes")
    protected HashMap getLoginInfo() {
        return userDataMap;
    }
}
