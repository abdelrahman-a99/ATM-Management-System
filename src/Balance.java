import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.util.HashMap;
import java.util.LinkedList;

public class Balance extends JFrame implements ActionListener {

    JLabel balanceLabel;

    JLabel accNumLabel;
    JLabel balanceAmountLabel;

    JLabel accNum;
    JLabel balanceAmount;

    JButton backButton;

    String accountNumber; // Account number variable



    HashMap<String, String> loginInfo = new HashMap<String, String>();

    Balance(String accNumber, HashMap<String, String> loginInfo) { // Constructor to receive account number

        this.loginInfo = loginInfo;

        accountNumber = accNumber; // Initialize account number

        setTitle("BALANCE");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        balanceLabel = new JLabel("BALANCE");
        balanceLabel.setBounds(140, 10, 150, 25);
        balanceLabel.setFont(new Font(null, Font.BOLD, 20));

        accNumLabel = new JLabel("Account Number:");
        accNumLabel.setBounds(50, 50, 100, 25);

        balanceAmountLabel = new JLabel("Your Balance:" + extractBalance(loginInfo.get(accNumber)));
        balanceAmountLabel.setBounds(80, 100, 100, 25);

        accNum = new JLabel(" " + accountNumber);
        accNum.setBounds(150, 50, 150, 25);

        balanceAmount = new JLabel();
        balanceAmount.setBounds(150, 100, 150, 25);

        backButton = new JButton("BACK");
        backButton.setBounds(200, 100, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        add(balanceLabel);

        add(accNumLabel);
        add(balanceAmountLabel);

        add(accNum);
        add(balanceAmount);

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
    }
}
