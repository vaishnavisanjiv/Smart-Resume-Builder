import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpPage extends JFrame {
    private JTextField userText;
    private JPasswordField passwordText;

    public SignUpPage() {
        setTitle("Sign Up");
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
        JButton signupButton = new JButton("Sign Up");

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(new JLabel());
        panel.add(signupButton);

        signupButton.addActionListener(new SignUpAction());

        setVisible(true);
    }

    private class SignUpAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());
            if (createUser(username, password)) {
                new LoginPage();
                dispose();
            } else {
                JOptionPane.showMessageDialog(SignUpPage.this, "Sign up failed");
            }
        }
    }

    private boolean createUser(String username, String password) {
        Connection connection = DatabaseConnection.getConnection();
        if (connection != null) {
            try {
                String query = "INSERT INTO users (username, password) VALUES (?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                int result = preparedStatement.executeUpdate();
                return result > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}