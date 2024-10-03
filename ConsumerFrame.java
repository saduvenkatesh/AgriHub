import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class ConsumerFrame extends JFrame {
    JTextField productNameField, buyQuantityField;
    JButton viewButton, buyButton,goBackButton;
    JTextArea productListArea;

    public ConsumerFrame() {
        setTitle("Consumer Portal");
        setSize(500, 500);
        setLayout(null);
        setBackground(Color.GRAY);


        JLabel productLabel = new JLabel("Product Name:");
        productLabel.setBounds(50, 50, 100, 30);
        productNameField = new JTextField();
        productNameField.setBounds(150, 50, 150, 30);

        viewButton = new JButton("View Products");
        viewButton.setBounds(50, 100, 150, 30);

        buyButton = new JButton("Buy Product");
        buyButton.setBounds(220, 100, 150, 30);

        productListArea = new JTextArea();
        productListArea.setBounds(50, 150, 400, 200);

        JLabel buyQuantityLabel = new JLabel("Buy Quantity:");
        buyQuantityLabel.setBounds(50, 370, 100, 30);
        buyQuantityField = new JTextField();
        buyQuantityField.setBounds(150, 370, 150, 30);

        goBackButton = new JButton("Go Back");
        goBackButton.setBackground(Color.GRAY);
        goBackButton.setForeground(Color.red);
        goBackButton.setBounds(50, 420, 300, 30);
        add(goBackButton);
        add(productLabel);
        add(productNameField);
        add(viewButton);
        add(buyButton);
        add(productListArea);
        add(buyQuantityLabel);
        add(buyQuantityField);

        

        setVisible(true);

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewProducts();
            }
        });

        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productName = productNameField.getText();
                int buyQuantity = Integer.parseInt(buyQuantityField.getText());
                buyProduct(productName, buyQuantity);
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashboardFrame().setVisible(true);
                dispose();
            }
        });


    }

    private void viewProducts() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organicfarmingdb", "root", "Venky@2005");
            String query = "SELECT * FROM products";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            productListArea.setText("ID\tName\tQuantity\tPrice\n");
            while (rs.next()) {
                productListArea.append(rs.getInt("product_id") + "\t" + rs.getString("product_name") + "\t" + rs.getInt("quantity") + "\t" + rs.getDouble("price") + "\n");
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void buyProduct(String productName, int buyQuantity) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organicfarmingdb", "root", "Venky@2005");
            String query = "SELECT quantity FROM products WHERE product_name=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, productName);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int availableQuantity = rs.getInt("quantity");
                if (availableQuantity >= buyQuantity) {
                    query = "UPDATE products SET quantity=? WHERE product_name=?";
                    pst = con.prepareStatement(query);
                    pst.setInt(1, availableQuantity - buyQuantity);
                    pst.setString(2, productName);
                    pst.executeUpdate();

                    JOptionPane.showMessageDialog(this, "Purchase successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Not enough quantity available!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Product not found!");
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
