import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


import java.awt.Font;
import java.util.HashMap;

public class Withdraw extends JFrame implements ActionListener {

    JLabel withdrawLabel;

    JLabel amountLabel;
    JTextField amountField;

    JButton withdrawButton;
    JButton backButton;

    String accountNumber; // Account number variable
    userDataManager FullUserData = new userDataManager();
    HashMap<String, String> userDataMap = new HashMap<String, String>();
    Withdraw(String accNumber,HashMap<String, String> userDataMap ) { // Constructor to receive account number

        this.userDataMap = userDataMap; // Initialize user data
        accountNumber = accNumber; // Initialize account number

        setTitle("Withdraw Transaction");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        withdrawLabel = new JLabel("WITHDRAW");
        withdrawLabel.setBounds(140, 10, 150, 25);
        withdrawLabel.setFont(new Font(null, Font.BOLD, 20));

        amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(80, 50, 100, 25);

        amountField = new JTextField();
        amountField.setBounds(150, 50, 150, 25);

        withdrawButton = new JButton("WITHDRAW");
        withdrawButton.setBounds(80, 100, 100, 25);
        withdrawButton.setFocusable(false);
        withdrawButton.addActionListener(this);

        backButton = new JButton("BACK");
        backButton.setBounds(200, 100, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        add(withdrawLabel);

        add(amountLabel);
        add(amountField);

        add(withdrawButton);
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
        }

        if(e.getSource() == withdrawButton){
            try {
                int balance = Integer.parseInt(extractBalance(userDataMap.get(accountNumber)));
                int WithdrawnAmount = Integer.parseInt(amountField.getText());


                if (WithdrawnAmount > 0) {

                    if(WithdrawnAmount > balance){
                        JOptionPane.showMessageDialog(this, "Insufficient funds ", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    balance = balance - WithdrawnAmount;
                    // Updating the user data
                    String userData = userDataMap.get(accountNumber);
                    String[] parts = userData.split(":");
                    parts[9] = String.valueOf(balance);  // Update the balance
                    String updatedUserData = String.join(":", parts);

                    // Replace the existing data in the HashMap and rewrite the data file
                    FullUserData.updateUserData(accountNumber, updatedUserData);
                    JOptionPane.showMessageDialog(this, "Withdraw successful!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    MiniStatement.AddMessage("Withdrawn: " + WithdrawnAmount);

                    dispose();
                    TransactionFrame transactionFrame = new TransactionFrame(accountNumber); // Pass account number
                    transactionFrame.setVisible(true);

                }
                else {
                    JOptionPane.showMessageDialog(this, "Please enter a proper value to withdraw", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid value to deposit", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}
