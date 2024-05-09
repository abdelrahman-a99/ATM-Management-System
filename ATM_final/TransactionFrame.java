import javax.swing.Timer;

import java.util.HashMap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

public class TransactionFrame extends JFrame implements ActionListener {

    JLabel headMessage;
    JLabel message;

    JLabel accNumLabel;
    JLabel accNum;

    JButton deposit;
    JButton withdraw;
    JButton fastCash;
    JButton miniStatement;
    JButton changePIN;
    JButton balance;

    JButton logOut;

    AccNum_PinCode accNum_pinCode = new AccNum_PinCode();
    @SuppressWarnings("unchecked")
    HashMap<String, String> loginInfo = accNum_pinCode.getLoginInfo();

    String accountNumber;

    TransactionFrame(String accountNumber) {

        this.accountNumber = accountNumber;

        setTitle("ATM Management System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        headMessage = new JLabel("ATM Management System");
        headMessage.setBounds(100, 0, 200, 25);
        headMessage.setFont(new Font(null, Font.BOLD, 15));

        message = new JLabel("Select Your Transaction Please");
        message.setBounds(50, 30, 300, 25);
        message.setFont(new Font(null, Font.BOLD, 20));

        accNumLabel = new JLabel("Account Number:");
        accNumLabel.setBounds(100, 60, 150, 25);
        accNumLabel.setFont(new Font(null, Font.BOLD, 15));

        accNum = new JLabel(" " + accountNumber);
        accNum.setBounds(220, 60, 100, 25);
        accNum.setFont(new Font(null, Font.BOLD, 15));

        deposit = new JButton("DEPOSIT");
        deposit.setBounds(40, 100, 130, 40);
        deposit.setFocusable(false);
        deposit.addActionListener(this);

        withdraw = new JButton("WITHDRAW");
        withdraw.setBounds(40, 170, 130, 40);
        withdraw.setFocusable(false);
        withdraw.addActionListener(this);

        fastCash = new JButton("FASTCASH");
        fastCash.setBounds(40, 240, 130, 40);
        fastCash.setFocusable(false);
        fastCash.addActionListener(this);

        miniStatement = new JButton("MINI STATEMENT");
        miniStatement.setBounds(210, 100, 130, 40);
        miniStatement.setFocusable(false);
        miniStatement.addActionListener(this);

        changePIN = new JButton("CHANGE PIN");
        changePIN.setBounds(210, 170, 130, 40);
        changePIN.setFocusable(false);
        changePIN.addActionListener(this);

        balance = new JButton("BALANCE");
        balance.setBounds(210, 240, 130, 40);
        balance.setFocusable(false);
        balance.addActionListener(this);

        logOut = new JButton("LOG OUT");
        logOut.setBounds(125, 300, 130, 25);
        logOut.setFocusable(false);
        logOut.addActionListener(this);

        add(headMessage);
        add(message);

        add(accNumLabel);
        add(accNum);

        add(deposit);
        add(withdraw);
        add(fastCash);

        add(miniStatement);
        add(changePIN);
        add(balance);

        add(logOut);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deposit) {

            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();

                    Deposit deposit = new Deposit(accountNumber);
                    deposit.setVisible(true);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }

        if (e.getSource() == withdraw) {

            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();

                    Withdraw withdraw = new Withdraw(accountNumber);
                    withdraw.setVisible(true);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }

        if (e.getSource() == fastCash) {

            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();

                    FastCash fastCash = new FastCash(accountNumber);
                    fastCash.setVisible(true);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }

        if (e.getSource() == miniStatement) {

            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();

                    MiniStatement miniStatement = new MiniStatement(accountNumber);
                    miniStatement.setVisible(true);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }

        if (e.getSource() == changePIN) {

            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();

                    ChangePIN changePIN = new ChangePIN(accountNumber);
                    changePIN.setVisible(true);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }

        if (e.getSource() == balance) {

            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();

                    Balance balance = new Balance(accountNumber);
                    balance.setVisible(true);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }

        if (e.getSource() == logOut) {

            JOptionPane.showMessageDialog(this,
                    "      Logged out successfully\nThank You for using our system", "Logout",
                    JOptionPane.INFORMATION_MESSAGE);

            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();

                    LoginFrame loginFrame = new LoginFrame(loginInfo);
                    loginFrame.setVisible(true);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }
}
