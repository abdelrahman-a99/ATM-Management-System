import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.Font;

public class Withdraw extends JFrame implements ActionListener {

    JLabel withdrawLabel;

    JLabel amountLabel;
    JTextField amountField;

    JLabel balanceAmountLabel;
    JLabel balanceAmount;

    JButton withdrawButton;
    JButton backButton;

    String accountNumber; // Account number variable

    Withdraw(String accNumber) { // Constructor to receive account number

        accountNumber = accNumber; // Initialize account number

        setTitle("Withdraw Transaction");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        withdrawLabel = new JLabel("WITHDRAW");
        withdrawLabel.setBounds(140, 10, 150, 25);
        withdrawLabel.setFont(new Font(null, Font.BOLD, 20));

        balanceAmountLabel = new JLabel("Your Balance:");
        balanceAmountLabel.setBounds(80, 50, 100, 25);

        balanceAmount = new JLabel();
        balanceAmount.setBounds(150, 50, 150, 25);

        amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(80, 80, 100, 25);

        amountField = new JTextField();
        amountField.setBounds(150, 80, 150, 25);

        withdrawButton = new JButton("WITHDRAW");
        withdrawButton.setBounds(80, 120, 100, 25);
        withdrawButton.setFocusable(false);
        withdrawButton.addActionListener(this);

        backButton = new JButton("BACK");
        backButton.setBounds(200, 120, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        add(withdrawLabel);

        add(balanceAmountLabel);
        add(balanceAmount);

        add(amountLabel);
        add(amountField);

        add(withdrawButton);
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
