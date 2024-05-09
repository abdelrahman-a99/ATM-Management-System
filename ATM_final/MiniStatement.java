import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Font;

public class MiniStatement extends JFrame implements ActionListener {

    JLabel miniStatementLabel;

    JLabel amountLabel;
    JTextField amountField;

    JButton miniStatementButton;
    JButton backButton;

    String accountNumber; // Account number variable

    MiniStatement(String accNumber) { // Constructor to receive account number

        accountNumber = accNumber; // Initialize account number

        setTitle("MINISTATEMENT Transaction");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        miniStatementLabel = new JLabel("MINISTATEMENT");
        miniStatementLabel.setBounds(110, 10, 200, 25);
        miniStatementLabel.setFont(new Font(null, Font.BOLD, 20));

        amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(80, 50, 100, 25);

        amountField = new JTextField();
        amountField.setBounds(150, 50, 150, 25);

        miniStatementButton = new JButton("MiniStatement");
        miniStatementButton.setBounds(80, 100, 100, 25);
        miniStatementButton.setFocusable(false);
        miniStatementButton.addActionListener(this);

        backButton = new JButton("BACK");
        backButton.setBounds(200, 100, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        add(miniStatementLabel);

        add(amountLabel);
        add(amountField);

        add(miniStatementButton);
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
