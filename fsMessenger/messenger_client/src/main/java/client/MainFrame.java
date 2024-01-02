package client;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JPanel panel1;
    private JButton loginButton;
    private JButton signUpButton;
    private final JFrame jframe;
    private MainFrame() {
        this.jframe = new JFrame();

        loginButton.addActionListener(ev -> {
            switchToLoginFrame();
        });

        signUpButton.addActionListener(ev -> {
            switchToRegisterFrame();
        });
    }

    private static final class SingletonHolder{
        private static final MainFrame INSTANCE = new MainFrame();
    }

    public static MainFrame getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public void initializeMainFrame(){
        jframe.setContentPane(panel1);
        jframe.pack();
        jframe.setTitle("FS Messenger");
        jframe.setMinimumSize(new Dimension(400, 300));
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void switchToLoginFrame(){
        LoginFrame loginFrame = new LoginFrame();
        jframe.setContentPane(loginFrame.getjPanel());
        jframe.pack();
        jframe.setTitle("Login");
        jframe.setMinimumSize(new Dimension(400, 300));
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    public void switchToRegisterFrame(){
        RegisterFrame registerFrame = new RegisterFrame();
        jframe.setContentPane(registerFrame.getjPanel());
        jframe.pack();
        jframe.setTitle("Sign Up");
        jframe.setMinimumSize(new Dimension(400, 300));
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

    public void switchToChatFrame(String username){
        ChatFrame mainFrame = new ChatFrame(username);
        mainFrame.startDisplayThread();
        jframe.setContentPane(mainFrame.getPanel());
        jframe.pack();
        jframe.setTitle(username);
        jframe.setMinimumSize(new Dimension(500, 500));
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }
}
