
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.Font;
import java.util.HashMap;

public class FastCash extends JFrame implements ActionListener{

    JLabel balanceLabel;
    JLabel balance;

    JButton $100;
    JButton $500;
    JButton $1000;
    JButton $2000;
    JButton $5000;
    JButton $10000;

    JButton backButton;

    String accountNumber; // Account number variable
    HashMap<String, String> userDataMap = new HashMap<String,String>();
    userDataManager accNum_pinCode = new userDataManager();

    FastCash(String accNumber, HashMap<String, String> userDataMap) { // Constructor to receive account number

        this.userDataMap = userDataMap;
        accountNumber = accNumber; // Initialize account number

        setTitle("Fast Cash Transaction");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        balanceLabel = new JLabel("Balance: ");
        balanceLabel.setBounds(150, 10, 100, 25);
        balanceLabel.setFont(new Font(null, Font.BOLD, 15));

        balance = new JLabel();
        balance.setBounds(200, 10, 100, 25);
        balance.setFont(new Font(null, Font.BOLD, 15));

        $100 = new JButton("$100");
        $100.setBounds(40, 50, 100, 40);
        $100.setFocusable(false);
        $100.addActionListener(this);

        $500 = new JButton("$500");
        $500.setBounds(40, 150, 100, 40);
        $500.setFocusable(false);
        $500.addActionListener(this);

        $1000 = new JButton("$1000");
        $1000.setBounds(40, 250, 100, 40);
        $1000.setFocusable(false);
        $1000.addActionListener(this);

        $2000 = new JButton("$2000");
        $2000.setBounds(250, 50, 100, 40);
        $2000.setFocusable(false);
        $2000.addActionListener(this);

        $5000 = new JButton("$5000");
        $5000.setBounds(250, 150, 100, 40);
        $5000.setFocusable(false);
        $5000.addActionListener(this);

        $10000 = new JButton("$10000");
        $10000.setBounds(250, 250, 100, 40);
        $10000.setFocusable(false);
        $10000.addActionListener(this);

        backButton = new JButton("BACK");
        backButton.setBounds(145, 310, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        add(balanceLabel);
        add(balance);

        add($100);
        add($500);
        add($1000);
        add($2000);
        add($5000);
        add($10000);

        add(backButton);

        setVisible(true);
    }
    String extractBalance(String userData){
        String[] parts = userData.split(":");
        return parts[9];
    }

    void DepositAndUpdateAmount(int amount){
        int balance = Integer.parseInt(extractBalance(userDataMap.get(accountNumber)));
        balance += amount;

        String userData = userDataMap.get(accountNumber);
        String[] parts = userData.split(":");
        parts[9] = String.valueOf(balance);  // Update the balance
        String updatedUserData = String.join(":", parts);

        // Replace the existing data in the HashMap and rewrite the data file
        accNum_pinCode.updateUserData(accountNumber, updatedUserData);

        JOptionPane.showMessageDialog(this, "Fast Cash Deposit successful!", "Success!", JOptionPane.INFORMATION_MESSAGE);
        MiniStatement.AddMessage("Deposited via Fast Cash: " + amount);

        dispose();
        TransactionFrame transactionFrame = new TransactionFrame(accountNumber); // Pass account number
        transactionFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {

                    dispose();
                    TransactionFrame transactionFrame = new TransactionFrame(accountNumber); // Pass account number
                    transactionFrame.setVisible(true);

        }

        if(e.getSource() == $100){
            DepositAndUpdateAmount(100);
        }

        if(e.getSource() == $500){
            DepositAndUpdateAmount(500);
        }

        if(e.getSource() == $1000){
            DepositAndUpdateAmount(1000);
        }

        if(e.getSource() == $2000){
            DepositAndUpdateAmount(2000);
        }

        if(e.getSource() == $5000){
            DepositAndUpdateAmount(5000);
        }

        if(e.getSource() == $10000){
            DepositAndUpdateAmount(10000);
        }


    }

}
