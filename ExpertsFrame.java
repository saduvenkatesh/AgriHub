

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpertsFrame extends JFrame {
    private JPanel expertsPanel;
    private JButton goBackButton;

    public ExpertsFrame() {
        setTitle("Experts");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        ImageIcon hulk = new ImageIcon("APP/src/hulk.png");
        setIconImage(hulk.getImage());

        expertsPanel = new JPanel();
        expertsPanel.setLayout(new GridLayout(0, 1));
        add(new JScrollPane(expertsPanel), BorderLayout.CENTER);

        goBackButton = new JButton("Go Back");
        goBackButton.setBackground(Color.GRAY);
        goBackButton.setForeground(Color.red);
        add(goBackButton, BorderLayout.SOUTH);

        loadExperts();

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashboardFrame().setVisible(true);
                dispose();
            }
        });
    }

    private void loadExperts() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Experts";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String bio = rs.getString("bio");
                

                JButton expertButton = new JButton(name);
                expertButton.setBackground(Color.yellow);  
                expertButton.setForeground(Color.blue);
                expertButton.setFocusable(false);
                expertButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(ExpertsFrame.this, bio, name, JOptionPane.INFORMATION_MESSAGE);
                    }
                });
                expertsPanel.add(expertButton);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
