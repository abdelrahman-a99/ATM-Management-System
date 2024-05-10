

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JOptionPane;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import java.awt.Font;

public class RegisterFrame extends JFrame implements ActionListener {

    JLabel registerLabel;

    JLabel accNumLabel;
    JLabel fNameLabel;
    JLabel lNameLabel;
    JLabel addressLabel;

    JLabel pinLabel;
    JLabel eduLabel;
    JLabel occLabel;
    JLabel phoneLabel;
    JLabel DOBLabel;

    JTextField accNumField;
    JTextField fNameField;
    JTextField lNameField;
    JTextArea addressField;

    JTextField pinField;
    @SuppressWarnings("rawtypes")
    JComboBox eduField;
    JTextField occField;
    JTextField phoneField;
    JTextField DOBField;

    JButton submiButton;

    String[] educationChoices = { "High School", "Bachelor's Degree", "Master's Degree", "PhD", "Other" };

    HashMap<String, String> loginInfo = new HashMap<String, String>();

    RegisterFrame(HashMap<String, String> loginInfoOriginal) {

        this.loginInfo = loginInfoOriginal;

        setTitle("Login System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);

        registerLabel = new JLabel("Registration Form");

        registerLabel.setBounds(130, 10, 200, 25);
        registerLabel.setFont(new Font(null, Font.BOLD, 15));

        accNumLabel = new JLabel("Acc Num:");
        fNameLabel = new JLabel("First Name:");
        lNameLabel = new JLabel("Last Name:");
        addressLabel = new JLabel("Address:");

        accNumLabel.setBounds(10, 50, 100, 25);
        fNameLabel.setBounds(10, 100, 100, 25);
        lNameLabel.setBounds(10, 150, 100, 25);
        addressLabel.setBounds(10, 200, 100, 25);

        accNumField = new JTextField();
        fNameField = new JTextField();
        lNameField = new JTextField();
        addressField = new JTextArea();

        accNumField.setBounds(80, 50, 100, 25);
        fNameField.setBounds(80, 100, 100, 25);
        lNameField.setBounds(80, 150, 100, 25);
        addressField.setBounds(80, 200, 100, 75);

        pinLabel = new JLabel("PIN CODE:");
        eduLabel = new JLabel("Education:");
        occLabel = new JLabel("Occupation:");
        phoneLabel = new JLabel("Phone:");
        DOBLabel = new JLabel("Date Of Birth:");

        pinLabel.setBounds(190, 50, 100, 25);
        eduLabel.setBounds(190, 100, 100, 25);
        occLabel.setBounds(190, 150, 100, 25);
        phoneLabel.setBounds(190, 200, 100, 25);
        DOBLabel.setBounds(190, 250, 100, 25);

        pinField = new JTextField();
        eduField = new JComboBox<>(educationChoices);
        occField = new JTextField();
        phoneField = new JTextField();
        DOBField = new JTextField();

        pinField.setBounds(270, 50, 100, 25);
        eduField.setBounds(270, 100, 100, 25);
        occField.setBounds(270, 150, 100, 25);
        phoneField.setBounds(270, 200, 100, 25);
        DOBField.setBounds(270, 250, 100, 25);

        submiButton = new JButton("Submit");
        submiButton.setBounds(120, 300, 150, 25);
        submiButton.setFocusable(false);
        submiButton.addActionListener(this);

        add(registerLabel);

        add(accNumLabel);
        add(fNameLabel);
        add(lNameLabel);
        add(addressLabel);

        add(accNumField);
        add(fNameField);
        add(lNameField);
        add(addressField);

        add(pinLabel);
        add(eduLabel);
        add(occLabel);
        add(phoneLabel);
        add(DOBLabel);

        add(pinField);
        add(eduField);
        add(occField);
        add(phoneField);
        add(DOBField);

        add(submiButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submiButton) {
            String accNum = accNumField.getText().trim();
            String fName = fNameField.getText().trim();
            String lName = lNameField.getText().trim();
            String address = addressField.getText().trim();
            String pin = pinField.getText().trim();
            String edu = (String) eduField.getSelectedItem();
            String occ = occField.getText().trim();
            String phone = phoneField.getText().trim();
            String dob = DOBField.getText().trim();

            if (accNum.isEmpty() || fName.isEmpty() || lName.isEmpty() || address.isEmpty() ||
                    pin.isEmpty() || edu.isEmpty() || occ.isEmpty() || phone.isEmpty() || dob.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!accNum.matches("\\d{4}-\\d{4}")) {
                JOptionPane.showMessageDialog(this, "Please enter a 8-digit account number in the format 0000-0000.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!fName.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid first name.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!lName.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid last name.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (!pin.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(this, "Please enter a 4-digit PIN code.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (eduField.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Please select your education.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!occ.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid occupation.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!phone.matches("\\d{11}")) {
                JOptionPane.showMessageDialog(this, "Please enter a 11-digit phone number.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(this, "Please enter date of birth in the format YYYY-MM-DD.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            // check if the accNum is not already exist
            if (loginInfo.containsKey(accNum)) {
                JOptionPane.showMessageDialog(this, "Account Number already exists. Please choose another one.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String userData = accNum + ":" + fName + ":" + lName + ":" + address + ":" + pin + ":" + edu + ":" + occ + ":" + phone + ":" + dob + ":" + 0; // 0 for init balance

            userDataManager accNum_pinCode = new userDataManager();
            accNum_pinCode.addUser(accNum, userData);

            JOptionPane.showMessageDialog(this, "Submission Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    TransactionFrame transactionFrame = new TransactionFrame(accNum);
                    transactionFrame.setVisible(true);

        }
    }

}
