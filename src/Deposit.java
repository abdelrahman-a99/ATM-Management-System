import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.Font;
import java.util.HashMap;

public class Deposit extends JFrame implements ActionListener{

    JLabel depositLabel;
    JLabel amountLabel;
    JTextField amountField;
    JButton depositButton;
    JButton backButton;
    String accountNumber;

    HashMap<String, String> userDataMap = new HashMap<String,String>();
    userDataManager FullUserData = new userDataManager();
    Deposit(String accNumber, HashMap<String, String> userDataMap){ // Constructor to receive account number

        accountNumber = accNumber; // Initialize account number
        this.userDataMap = userDataMap; // initialize data map

        setTitle("Deposit Transaction");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        depositLabel = new JLabel("DEPOSIT");
        depositLabel.setBounds(140, 10, 150, 25);
        depositLabel.setFont(new Font(null, Font.BOLD, 20));

        amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(80, 50, 100, 25);

        amountField = new JTextField();
        amountField.setBounds(150, 50, 150, 25);

        depositButton = new JButton("DEPOSIT");
        depositButton.setBounds(80, 100, 100, 25);
        depositButton.setFocusable(false);
        depositButton.addActionListener(this);

        backButton = new JButton("BACK");
        backButton.setBounds(200, 100, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        add(depositLabel);

        add(amountLabel);
        add(amountField);

        add(depositButton);
        add(backButton);

        setVisible(true);
    }

    String extractBalance(String userData){
        String[] parts = userData.split(":");
        return parts[9];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {

                    dispose();
                    TransactionFrame transactionFrame = new TransactionFrame(accountNumber); // Pass account number
                    transactionFrame.setVisible(true);
                    return;
        }

        if(e.getSource() == depositButton){
            try {
                int DepositedAmount = Integer.parseInt(amountField.getText());

                if (DepositedAmount > 0) {
                    int balance = Integer.valueOf(extractBalance(userDataMap.get(accountNumber)));
                    balance = balance + DepositedAmount;

                    // Updating the user data
                    String userData = userDataMap.get(accountNumber);
                    String[] parts = userData.split(":");
                    parts[9] = String.valueOf(balance);  // Update the balance
                    String updatedUserData = String.join(":", parts);

                    // Replace the existing data in the HashMap and rewrite the data file
                    FullUserData.updateUserData(accountNumber, updatedUserData);
                    JOptionPane.showMessageDialog(this, "Deposit successful!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    MiniStatement.AddMessage("Deposited Amount: " + DepositedAmount);
                    dispose();
                    TransactionFrame transactionFrame = new TransactionFrame(accountNumber); // Pass account number
                    transactionFrame.setVisible(true);
                    return;
                }
                else {
                    JOptionPane.showMessageDialog(this, "Please enter a value higher than 0 to deposit", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid value to deposit", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
