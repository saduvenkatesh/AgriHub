

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MarketplaceFrame extends JFrame {
    private JTextArea marketplaceArea;
    private JButton goBackButton;

    public MarketplaceFrame() {
        setTitle("Market");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        ImageIcon hulk = new ImageIcon("APP/src/hulk.png");
        setIconImage(hulk.getImage());
        

        marketplaceArea = new JTextArea();
        marketplaceArea.setBounds(20, 20, 450, 300);
        marketplaceArea.setEditable(false);
        add(marketplaceArea);

        goBackButton = new JButton("Go Back");
        goBackButton.setBounds(20, 330, 100, 25);
        goBackButton.setBackground(Color.GRAY);
        goBackButton.setForeground(Color.red);
        add(goBackButton);

        loadMarketplace();

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashboardFrame().setVisible(true);
                dispose();
            }
        });
    }

    private void loadMarketplace() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Market";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            StringBuilder sb = new StringBuilder();
            
            while (rs.next()) {
                sb.append(rs.getString("category")).append(" - ");
                sb.append(rs.getString("item_name")).append(": ");
                sb.append(rs.getDouble("price")).append("\n");
            }
            marketplaceArea.setText(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
