import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;

public class ChangePIN extends JFrame implements ActionListener {

    JLabel changePINLabel;

    JLabel OldPassword;
    JLabel NewPassword;
    JLabel NewPasswordConfirm;

    JTextField OldPasswordText;
    JTextField NewPasswordText;
    JTextField NewPasswordTextConfirm;

    JButton changePINButton;
    JButton backButton;

    String accountNumber;

    UserDataManager accNum_pinCode = new UserDataManager();

    @SuppressWarnings("unchecked")
    HashMap<String, String> loginInfo = accNum_pinCode.getLoginInfo();

    ChangePIN(String accNumber, HashMap<String, String> loginInfoOriginal) {

        this.accountNumber = accNumber;

        this.loginInfo = loginInfoOriginal;

        accountNumber = accNumber;

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
        OldPassword.setBounds(50, 50, 100, 25);

        NewPassword = new JLabel("New Password:");
        NewPassword.setBounds(50, 100, 100, 25);

        NewPasswordConfirm = new JLabel("Confirm Password:");
        NewPasswordConfirm.setBounds(50, 150, 120, 25);

        OldPasswordText = new JTextField();
        OldPasswordText.setBounds(170, 50, 150, 25);

        NewPasswordText = new JTextField();
        NewPasswordText.setBounds(170, 100, 150, 25);

        NewPasswordTextConfirm = new JTextField();
        NewPasswordTextConfirm.setBounds(170, 150, 150, 25);

        changePINButton = new JButton("Change PIN");
        changePINButton.setBounds(80, 200, 100, 25);
        changePINButton.setFocusable(false);
        changePINButton.addActionListener(this);

        backButton = new JButton("BACK");
        backButton.setBounds(200, 200, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        add(changePINLabel);

        add(OldPassword);
        add(NewPassword);
        add(NewPasswordConfirm);

        add(OldPasswordText);
        add(NewPasswordText);
        add(NewPasswordTextConfirm);

        add(changePINButton);
        add(backButton);

        setVisible(true);
    }

    String extractPassword(String userData) {
        String[] parts = userData.split(":");
        return parts[4];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose();
            TransactionFrame transactionFrame = new TransactionFrame(accountNumber);
            transactionFrame.setVisible(true);
        }

        if (e.getSource() == changePINButton) {
            String OldPasswordString = String.valueOf(OldPasswordText.getText());
            String NewPasswordString = String.valueOf(NewPasswordText.getText());
            String ConfirmPasswordString = String.valueOf(NewPasswordTextConfirm.getText());

            if (OldPasswordString.isEmpty() || NewPasswordString.isEmpty() || ConfirmPasswordString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the data", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            else if (NewPasswordString.length() != 4 || ConfirmPasswordString.length() != 4) {
                JOptionPane.showMessageDialog(this, "PIN Code must consist of 4 digits", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            else if (!extractPassword(loginInfo.get(accountNumber)).equals(OldPasswordString)) {
                JOptionPane.showMessageDialog(this, "Old PIN Code is incorrect for this account number", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            else if (!ConfirmPasswordString.equals(NewPasswordString)) {
                JOptionPane.showMessageDialog(this, "New Password is not confirmed properly", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Updating the user data
            String userData = loginInfo.get(accountNumber);
            String[] parts = userData.split(":");
            parts[4] = NewPasswordString; // Update the pin code
            String updatedUserData = String.join(":", parts);

            // Replace the existing data in the HashMap and rewrite the data file
            accNum_pinCode.updateUserData(accountNumber, updatedUserData);

            JOptionPane.showMessageDialog(this, "Password changed successfully", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            MiniStatement.AddMessage("Changed Pin");

            dispose();
            TransactionFrame transactionFrame = new TransactionFrame(accountNumber);
            transactionFrame.setVisible(true);
        }
    }
}
