import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Font;

public class ChangePIN extends JFrame implements ActionListener{
    
    JLabel changePINLabel;

    JLabel amountLabel;
    JTextField amountField;

    JButton changePINButton;
    JButton backButton;

    String accountNumber; // Account number variable

    ChangePIN(String accNumber){ // Constructor to receive account number

        accountNumber = accNumber; // Initialize account number

        setTitle("CHANGE PIN Transaction");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        changePINLabel = new JLabel("CHANGE PIN");
        changePINLabel.setBounds(130, 10, 150, 25);
        changePINLabel.setFont(new Font(null, Font.BOLD, 20));

        amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(80, 50, 100, 25);

        amountField = new JTextField();
        amountField.setBounds(150, 50, 150, 25);

        changePINButton = new JButton("changePIN");
        changePINButton.setBounds(80, 100, 100, 25);
        changePINButton.setFocusable(false);
        changePINButton.addActionListener(this);

        backButton = new JButton("BACK");
        backButton.setBounds(200, 100, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        add(changePINLabel);

        add(amountLabel);
        add(amountField);

        add(changePINButton);
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
