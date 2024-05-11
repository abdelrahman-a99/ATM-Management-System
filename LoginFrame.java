import java.util.HashMap;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends JFrame implements ActionListener {

    JLabel accNumLabel;
    JLabel pinCodeLabel;

    JTextField accNumField;
    JPasswordField pinCodeField;

    JButton loginButton;
    JButton registerButton;

    HashMap<String, String> loginInfo = new HashMap<String, String>();

    LoginFrame(HashMap<String, String> loginInfoOriginal) {
        loginInfo = loginInfoOriginal;

        setTitle("Login System");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        accNumLabel = new JLabel("ACC NUM:");
        pinCodeLabel = new JLabel("PIN CODE:");

        accNumLabel.setBounds(50, 20, 100, 25);
        pinCodeLabel.setBounds(50, 70, 100, 25);

        accNumField = new JTextField(10);
        pinCodeField = new JPasswordField(10);

        accNumField.setBounds(125, 20, 200, 25);
        pinCodeField.setBounds(125, 70, 200, 25);

        loginButton = new JButton("Login");
        loginButton.setBounds(90, 115, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        registerButton = new JButton("Register");
        registerButton.setBounds(210, 115, 100, 25);
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);

        add(accNumLabel);
        add(pinCodeLabel);

        add(accNumField);
        add(pinCodeField);

        add(loginButton);
        add(registerButton);

        setVisible(true);
    }

    String extractPassword(String userData) {
        String[] parts = userData.split(":");
        return parts[4];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String accNum = accNumField.getText().trim();
            String pinCode = String.valueOf(pinCodeField.getPassword());

            if (accNum.isEmpty() && pinCode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Account Number and PIN.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            } else if (accNum.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Account Number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (pinCode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter PIN CODE.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (loginInfo.containsKey(accNum)) {
                if (extractPassword(loginInfo.get(accNum)).equals(pinCode)) {
                    JOptionPane.showMessageDialog(this, "        Login Successful", "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                    dispose();
                    TransactionFrame transactionFrame = new TransactionFrame(accNum);
                    transactionFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Wrong Password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Account Number not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == registerButton) {
            dispose();
            RegisterFrame registerFrame = new RegisterFrame(loginInfo);
            registerFrame.setVisible(true);
        }
    }
}
