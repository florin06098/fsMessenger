package client;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginFrame {
    private JTextField textField1;
    private JButton loginButton;
    private JPanel jPanel;
    private JPasswordField passwordField1;
    private JButton signUpButton;

    public LoginFrame() {
        loginButton.addActionListener(ev -> login());
        signUpButton.addActionListener(ev -> {
            MainFrame.getInstance().switchToRegisterFrame();
        });

    }

    private void login() {
        String username = textField1.getText();
        String password = new String(passwordField1.getPassword());

        boolean canLogin = ConnectionController.getInstance().login(username, password);
        if (canLogin) {
            MainFrame.getInstance().switchToChatFrame(username);
        } else {
            JOptionPane.showMessageDialog(null, "The username or password is incorred");
        }
    }

    public JPanel getjPanel() {
        return jPanel;
    }

}
