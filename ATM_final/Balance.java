import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;

public class Balance extends JFrame implements ActionListener {

    JLabel balanceLabel;

    JLabel accNumLabel;
    JLabel balanceAmountLabel;

    JLabel accNum;
    JLabel balanceAmount;

    JButton backButton;

    String accountNumber; // Account number variable

    Balance(String accNumber) { // Constructor to receive account number

        accountNumber = accNumber; // Initialize account number

        setTitle("BALANCE Transaction");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        balanceLabel = new JLabel("BALANCE");
        balanceLabel.setBounds(140, 10, 150, 25);
        balanceLabel.setFont(new Font(null, Font.BOLD, 20));

        accNumLabel = new JLabel("Account Number:");
        accNumLabel.setBounds(80, 50, 100, 25);

        balanceAmountLabel = new JLabel("Your Balance:");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {

            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();

                    TransactionFrame transactionFrame = new TransactionFrame(accountNumber); // Pass account number
                    transactionFrame.setVisible(true);
                }
            });
            timer.setRepeats(false);
            timer.start();

        }
    }
}
