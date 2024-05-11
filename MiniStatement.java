import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// import java.util.LinkedList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.Font;

public class MiniStatement extends JFrame implements ActionListener {

    // public static LinkedList<String> currentTransactions = new LinkedList<String>();
    public static Stack<String> currentTransactions = new Stack<String>();

    JLabel miniStatementLabel;
    JTextArea transactionsText;
    JButton backButton;

    String accountNumber;

    MiniStatement(String accNumber) {

        accountNumber = accNumber;

        setTitle("MINI-STATEMENT");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        miniStatementLabel = new JLabel("MINI-STATEMENT");
        miniStatementLabel.setBounds(110, 10, 200, 25);
        miniStatementLabel.setFont(new Font(null, Font.BOLD, 20));

        transactionsText = new JTextArea("");
        transactionsText.setBounds(40, 50, 300, 150);
        transactionsText.setFont(new Font(null, Font.BOLD, 10));
        transactionsText.setEditable(false);
        PrintList();

        backButton = new JButton("BACK");
        backButton.setBounds(140, 220, 100, 25);
        backButton.setFocusable(false);
        backButton.addActionListener(this);

        add(miniStatementLabel);

        add(backButton);
        add(transactionsText);

        setVisible(true);
    }
    // This method print like linked list
    // public static void AddMessage(String message) {
    //     currentTransactions.addLast(message);
    // }

    // This method print like stack
    public static void AddMessage(String message) {
        currentTransactions.push(message);
    }

    void PrintList() {
        String list = "-----------------------------------------------------------------------------------------------------\n";

        if (currentTransactions.isEmpty()) {
            transactionsText.setText("No Transactions Available"); // Update GUI label
        } else {
            // Print all the transactions but like linked list
            // for (String transaction : currentTransactions) {
            //     list += transaction + "\n";
            //     list += "-----------------------------------------------------------------------------------------------------\n";
            // }

            // Print all the transactions but like stack
            for (String transaction : currentTransactions) {
                list = transaction + "\n" + list;
                list = "-----------------------------------------------------------------------------------------------------\n" + list;
            }
            transactionsText.setText(list);
        }
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
