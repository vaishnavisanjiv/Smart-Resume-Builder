import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class ResumeForm extends JFrame {
    private JTextField nameText;
    private JTextField emailText;
    private JTextField phoneText;
    private JTextArea educationText;
    private JTextArea experienceText;

    public ResumeForm() {
        setTitle("Create Resume");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        add(panel);

        JLabel nameLabel = new JLabel("Name:");
        nameText = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        emailText = new JTextField(20);
        JLabel phoneLabel = new JLabel("Phone:");
        phoneText = new JTextField(20);
        JLabel educationLabel = new JLabel("Education:");
        educationText = new JTextArea(5, 20);
        JScrollPane educationScroll = new JScrollPane(educationText);
        JLabel experienceLabel = new JLabel("Experience:");
        experienceText = new JTextArea(5, 20);
        JScrollPane experienceScroll = new JScrollPane(experienceText);
        JButton saveButton = new JButton("Save");

        panel.add(nameLabel);
        panel.add(nameText);
        panel.add(emailLabel);
        panel.add(emailText);
        panel.add(phoneLabel);
        panel.add(phoneText);
        panel.add(educationLabel);
        panel.add(educationScroll);
        panel.add(experienceLabel);
        panel.add(experienceScroll);
        panel.add(new JLabel());
        panel.add(saveButton);

        saveButton.addActionListener(new SaveAction());

        setVisible(true);
    }

    private class SaveAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameText.getText();
            String email = emailText.getText();
            String phone = phoneText.getText();
            String education = educationText.getText();
            String experience = experienceText.getText();
            generateResume(name, email, phone, education, experience);
            JOptionPane.showMessageDialog(ResumeForm.this, "Resume saved successfully");

            // Assuming DashboardPage exists
            new DashboardPage();
            dispose();
        }
    }

    private void generateResume(String name, String email, String phone, String education, String experience) {
        try {
            String dest = "resume.pdf";
            PdfWriter writer = new PdfWriter(new FileOutputStream(dest));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Name: " + name));
            document.add(new Paragraph("Email: " + email));
            document.add(new Paragraph("Phone: " + phone));
            document.add(new Paragraph("Education: " + education));
            document.add(new Paragraph("Experience: " + experience));

            document.close();
            System.out.println("Resume saved as PDF: " + dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ResumeForm();
    }
}
