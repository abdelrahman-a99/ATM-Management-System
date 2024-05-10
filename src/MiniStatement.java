import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.Font;
import java.util.LinkedList;


public class MiniStatement extends JFrame implements ActionListener {

    public static LinkedList<String> currentTransactions = new LinkedList<String>();
    JLabel miniStatementLabel;

    JTextArea transactionsText;
    JButton miniStatementButton;
    JButton backButton;
    String accountNumber; // Account number variable

    MiniStatement(String accNumber) { // Constructor to receive account number

        accountNumber = accNumber; // Initialize account number

        setTitle("MINI-STATEMENT");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        miniStatementLabel = new JLabel("MINI-STATEMENT");
        miniStatementLabel.setBounds(110, 10, 400, 25);
        miniStatementLabel.setFont(new Font(null, Font.BOLD, 20));

        transactionsText = new JTextArea("");
        transactionsText.setBounds(110,100,300,100);
        transactionsText.setFont(new Font(null, Font.BOLD, 10));
        PrintList();

        miniStatementButton = new JButton("PRINT");
        miniStatementButton.setBounds(80, 400, 100, 25);
        miniStatementButton.setFocusable(false);
        miniStatementButton.addActionListener(this);

        backButton = new JButton("BACK");
        backButton.setBounds(200, 400, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        add(miniStatementLabel);

        add(miniStatementButton);
        add(backButton);
        add(transactionsText);

        setVisible(true);
    }

    public static void AddMessage(String message) {
        currentTransactions.addLast(message);
    }

    void PrintList() {
        // Initialize the list string with the title "LIST"
        String list = "";

        // Check if the transaction list is empty
        if (currentTransactions.isEmpty()) {
            transactionsText.setText("No Transactions Available"); // Update GUI label
        } else {
            // Build the complete list string from all transactions
            for (String transaction : currentTransactions) {
                list += "\n" + transaction; // Append each transaction with a new line
            }
            // Set the GUI label to display all transactions
            transactionsText.setText(list);
        }
    }

        @Override
        public void actionPerformed (ActionEvent e){
            if (e.getSource() == backButton) {
                dispose();
                TransactionFrame transactionFrame = new TransactionFrame(accountNumber); // Pass account number
                transactionFrame.setVisible(true);
            }
        }
}
