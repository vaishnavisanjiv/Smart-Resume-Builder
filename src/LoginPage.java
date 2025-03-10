import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends JFrame {
    private JTextField userText;
    private JPasswordField passwordText;

    public LoginPage() {
        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        add(panel);

        JLabel userLabel = new JLabel("User:");
        userText = new JTextField(20);
        JLabel passwordLabel = new JLabel("Password:");
        passwordText = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign Up");

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(loginButton);
        panel.add(signupButton);

        loginButton.addActionListener(new LoginAction());
        signupButton.addActionListener(new SignUpAction());

        setVisible(true);
    }

    private class LoginAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());
            if (validateLogin(username, password)) {
                new DashboardPage();
                dispose();
            } else {
                JOptionPane.showMessageDialog(LoginPage.this, "Invalid login credentials");
            }
        }
    }

    private boolean validateLogin(String username, String password) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                String query = "SELECT * FROM users WHERE username = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();
                return resultSet.next();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private class SignUpAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new SignUpPage();
            dispose();
        }
    }
}