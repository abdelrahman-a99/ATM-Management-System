

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.Font;
import java.util.HashMap;

public class ChangePIN extends JFrame implements ActionListener{

    JLabel changePINLabel;
    JLabel OldPassword;
    JLabel NewPassword;
    JLabel NewPasswordConfirm;
    JTextField OldPasswordText;
    JTextField NewPasswordText;
    JTextField NewPasswordTextConfirm;
    JButton changePINButton;
    JButton backButton;
    String accountNumber; // Account number variable
    userDataManager accNum_pinCode = new userDataManager();


    @SuppressWarnings("unchecked")
    HashMap<String, String> loginInfo = accNum_pinCode.getLoginInfo();

    ChangePIN(String accNumber, HashMap<String, String> loginInfoOriginal){ // Constructor to receive account number
        this.accountNumber = accNumber;

        this.loginInfo = loginInfoOriginal;

        accountNumber = accNumber; // Initialize account number

        setTitle("CHANGE PIN Transaction");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        changePINLabel = new JLabel("CHANGE PIN");
        changePINLabel.setBounds(130, 10, 150, 25);
        changePINLabel.setFont(new Font(null, Font.BOLD, 20));

        OldPassword = new JLabel("Old Password:");
        OldPassword.setBounds(80, 50, 100, 25);

        NewPassword = new JLabel("New Password:");
        NewPassword.setBounds(80, 100, 100, 25);

        NewPasswordConfirm = new JLabel("Confirm Password:");
        NewPasswordConfirm.setBounds(80, 150, 120, 25);

        OldPasswordText = new JTextField();
        OldPasswordText.setBounds(200, 50, 150, 25);

        NewPasswordText = new JTextField();
        NewPasswordText.setBounds(200, 100, 150, 25);

        NewPasswordTextConfirm = new JTextField();
        NewPasswordTextConfirm.setBounds(200, 150, 150, 25);

        changePINButton = new JButton("Change PIN");
        changePINButton.setBounds(80, 200, 100, 25);
        changePINButton.setFocusable(false);
        changePINButton.addActionListener(this);

        backButton = new JButton("BACK");
        backButton.setBounds(200, 200, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        add(changePINLabel); // Header

        add(OldPassword); // labels
        add(NewPassword);
        add(NewPasswordConfirm);

        add(OldPasswordText); // text
        add(NewPasswordText);
        add(NewPasswordTextConfirm);

        add(changePINButton); // buttons
        add(backButton);

        setVisible(true);
    }

    String extractPassword(String userData){
        String[] parts = userData.split(":");
        return parts[4];
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {

                    dispose(); // closes current window
                    TransactionFrame transactionFrame = new TransactionFrame(accountNumber); // Pass account number
                    transactionFrame.setVisible(true);

        }

        if(e.getSource() == changePINButton){
            String OldPasswordString = String.valueOf(OldPasswordText.getText());
            String NewPasswordString = String.valueOf(NewPasswordText.getText());
            String ConfirmPasswordString = String.valueOf(NewPasswordTextConfirm.getText());

            if (OldPasswordString.isEmpty() || NewPasswordString.isEmpty() || ConfirmPasswordString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the data", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

           else if (NewPasswordString.length() != 4 || ConfirmPasswordString.length() != 4){
               JOptionPane.showMessageDialog(this, "Pin Code Must Consist Of 4 Digits", "Error",
                       JOptionPane.ERROR_MESSAGE);
               return;
           }

           else if(!extractPassword(loginInfo.get(accountNumber)).equals(OldPasswordString)){
               JOptionPane.showMessageDialog(this, "Pin Code Is Wrong For This Account Number", "Error",
                       JOptionPane.ERROR_MESSAGE);
               return;
           }

            else if(!ConfirmPasswordString.equals(NewPasswordString)){
                JOptionPane.showMessageDialog(this, "New Password Is Not Confirmed Properly", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Updating the user data
            String userData = loginInfo.get(accountNumber);
            String[] parts = userData.split(":");
            parts[4] = NewPasswordString;  // Update the pin code
            String updatedUserData = String.join(":", parts);

            // Replace the existing data in the HashMap and rewrite the data file
            accNum_pinCode.updateUserData(accountNumber, updatedUserData);

            JOptionPane.showMessageDialog(this, "Password Changed Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            MiniStatement.AddMessage("Changed Pin");


            dispose();
            TransactionFrame transactionFrame = new TransactionFrame(accountNumber);
            transactionFrame.setVisible(true);

        }
    }


}
