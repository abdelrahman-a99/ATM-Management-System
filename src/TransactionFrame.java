

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

    userDataManager accNum_pinCode = new userDataManager();
    @SuppressWarnings("unchecked")
    HashMap<String, String> userDataMap = accNum_pinCode.getLoginInfo();

    String accountNumber;

    TransactionFrame(String accountNumber) {

        this.accountNumber = accountNumber;

        setTitle("ATM Management System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

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
        accNum.setBounds(225, 60, 100, 25);
        accNum.setFont(new Font(null, Font.BOLD, 15));

        deposit = new JButton("DEPOSIT");
        deposit.setBounds(40, 100, 130, 40);
        deposit.setFocusable(false);
        deposit.addActionListener(this);

        withdraw = new JButton("WITHDRAW");
        withdraw.setBounds(40, 170, 130, 40);
        withdraw.setFocusable(false);
        withdraw.addActionListener(this);

        fastCash = new JButton("FAST-CASH");
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


                    dispose();
                    Deposit deposit = new Deposit(accountNumber, userDataMap);
                    deposit.setVisible(true);

        }

        if (e.getSource() == withdraw) {
                    dispose();
                    Withdraw withdraw = new Withdraw(accountNumber, userDataMap);
                    withdraw.setVisible(true);

        }

        if (e.getSource() == fastCash) {


                    dispose();
                    FastCash fastCash = new FastCash(accountNumber, userDataMap);
                    fastCash.setVisible(true);


        }

        if (e.getSource() == miniStatement) {


                    dispose();

                    MiniStatement miniStatement = new MiniStatement(accountNumber);
                    miniStatement.setVisible(true);

        }

        if (e.getSource() == changePIN) {
                    dispose();
                    ChangePIN changePIN = new ChangePIN(accountNumber, userDataMap);
                    changePIN.setVisible(true);
        }

        if (e.getSource() == balance) {

                    dispose();
                    Balance balance = new Balance(accountNumber, userDataMap);
                    balance.setVisible(true);

        }

        if (e.getSource() == logOut) {

            JOptionPane.showMessageDialog(this,
                    "      Logged out successfully\nThank You for using our system", "Logout",
                    JOptionPane.INFORMATION_MESSAGE);

                    dispose();
                    LoginFrame loginFrame = new LoginFrame(userDataMap);
                    loginFrame.setVisible(true);

        }
    }
}
