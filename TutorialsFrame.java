

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TutorialsFrame extends JFrame {
    private JTextArea tutorialsArea;
    private JButton goBackButton;

    public TutorialsFrame() {
        setTitle("Organic Farming Tutorials");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        tutorialsArea = new JTextArea();
        tutorialsArea.setBounds(20, 20, 450, 300);
        tutorialsArea.setEditable(false);
        add(tutorialsArea);

        goBackButton = new JButton("Go Back");
        goBackButton.setBounds(20, 330, 100, 25);
        goBackButton.setBackground(Color.GRAY);
        goBackButton.setForeground(Color.red);
        add(goBackButton);

        loadTutorials();

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashboardFrame().setVisible(true);
                dispose();
            }
        });
    }

    private void loadTutorials() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Tutorials";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            StringBuilder sb = new StringBuilder();
            while (rs.next()) {
                sb.append(rs.getString("title")).append("\n");
                sb.append(rs.getString("content")).append("\n\n");
            }
            tutorialsArea.setText(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
