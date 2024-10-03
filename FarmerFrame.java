import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class FarmerFrame extends JFrame {
    JTextField productNameField, quantityField, priceField;
    JButton addButton,goBackButton;

    public FarmerFrame() {
        setTitle("Farmer Portal");
        setSize(400, 400);
        setLayout(null);
        setBackground(Color.GRAY);

        JLabel productLabel = new JLabel("Product Name:");
        productLabel.setBounds(50, 50, 100, 30);
        productNameField = new JTextField();
        productNameField.setBounds(150, 50, 150, 30);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(50, 100, 100, 30);
        quantityField = new JTextField();
        quantityField.setBounds(150, 100, 150, 30);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(50, 150, 100, 30);
        priceField = new JTextField();
        priceField.setBounds(150, 150, 150, 30);

        addButton = new JButton("Add Product");
        addButton.setBounds(150, 200, 150, 30);

        goBackButton = new JButton("Go Back");
        goBackButton.setBackground(Color.GRAY);
        goBackButton.setForeground(Color.red);
        goBackButton.setBounds(50, 250, 50, 30);
        add(goBackButton);

        add(productLabel);
        add(productNameField);
        add(quantityLabel);
        add(quantityField);
        add(priceLabel);
        add(priceField);
        add(addButton);

        setVisible(true);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashboardFrame().setVisible(true);
                dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productName = productNameField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());

                addProductToDatabase(productName, quantity, price);
            }
        });
    }

    private void addProductToDatabase(String productName, int quantity, double price) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organicfarmingdb", "root", "Venky@2005");
            String query = "INSERT INTO products (product_name, quantity, price) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, productName);
            pst.setInt(2, quantity);
            pst.setDouble(3, price);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Product added successfully!");
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
