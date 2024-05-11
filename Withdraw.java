import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;

import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;

public class Withdraw extends JFrame implements ActionListener {

    JLabel withdrawLabel;

    JLabel amountLabel;
    JTextField amountField;

    JButton withdrawButton;
    JButton backButton;

    String accountNumber;

    UserDataManager FullUserData = new UserDataManager();

    HashMap<String, String> userDataMap = new HashMap<String, String>();

    Withdraw(String accNumber, HashMap<String, String> userDataMap) {

        this.userDataMap = userDataMap;
        accountNumber = accNumber;

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
        amountLabel.setBounds(80, 60, 100, 25);

        amountField = new JTextField();
        amountField.setBounds(150, 60, 150, 25);

        withdrawButton = new JButton("WITHDRAW");
        withdrawButton.setBounds(80, 110, 100, 25);
        withdrawButton.setFocusable(false);
        withdrawButton.addActionListener(this);

        backButton = new JButton("BACK");
        backButton.setBounds(200, 110, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        add(withdrawLabel);

        add(amountLabel);
        add(amountField);

        add(withdrawButton);
        add(backButton);

        setVisible(true);
    }

    String extractBalance(String userData) {
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

        if (e.getSource() == withdrawButton) {
            try {
                int balance = Integer.parseInt(extractBalance(userDataMap.get(accountNumber)));
                int WithdrawnAmount = Integer.parseInt(amountField.getText());

                if (WithdrawnAmount > 0) {
                    if (WithdrawnAmount > balance) {
                        JOptionPane.showMessageDialog(this, "You have insufficient funds to complete the transaction.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    balance = balance - WithdrawnAmount;
                    // Updating the user data
                    String userData = userDataMap.get(accountNumber);
                    String[] parts = userData.split(":");
                    parts[9] = String.valueOf(balance); // Update the balance
                    String updatedUserData = String.join(":", parts);

                    // Replace the existing data in the HashMap and rewrite the data file
                    FullUserData.updateUserData(accountNumber, updatedUserData);

                    JOptionPane.showMessageDialog(this, "Withdrawal successful!", "Success!",
                            JOptionPane.INFORMATION_MESSAGE);

                    MiniStatement.AddMessage("Withdrawn: " + WithdrawnAmount);

                    dispose();
                    TransactionFrame transactionFrame = new TransactionFrame(accountNumber); // Pass account number
                    transactionFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount to withdraw.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid value to be withdrawn.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
