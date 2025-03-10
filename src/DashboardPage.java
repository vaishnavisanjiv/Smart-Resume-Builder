import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardPage extends JFrame {
    public DashboardPage() {
        setTitle("Dashboard");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        add(panel);

        JButton createButton = new JButton("Create Resume");
        createButton.addActionListener(new CreateAction());

        panel.add(createButton);

        setVisible(true);
    }

    private class CreateAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            new ResumeForm();
            dispose();
        }
    }
}