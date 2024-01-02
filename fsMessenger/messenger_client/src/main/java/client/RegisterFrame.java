package client;

import javax.swing.*;

public class RegisterFrame {
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton signupButton;
    private JPasswordField passwordField1;
    private JPanel jPanel;
    private JButton loginButton;

    public RegisterFrame() {
        signupButton.addActionListener(ev -> signup());
        loginButton.addActionListener(ev -> {
            MainFrame.getInstance().switchToLoginFrame();
        });
    }

    private void signup() {
        String username = textField1.getText();
        String password = new String(passwordField1.getPassword());
        String last_name = textField3.getText();
        String first_name = textField4.getText();
        String age = textField5.getText();
        String email = textField6.getText();

        boolean isNewUser = ConnectionController.getInstance().register(username, password, last_name, first_name, age, email);
        if (isNewUser) {
            JOptionPane.showMessageDialog(null, "Userul " + username + " has been created");
            MainFrame.getInstance().switchToChatFrame(username);
        } else {
            JOptionPane.showMessageDialog(null, "The user is already registered, please try a new username");
        }
    }

    public JPanel getjPanel() {
        return jPanel;
    }
}
