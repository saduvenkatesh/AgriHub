import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RatingFrame extends JFrame {
    JTextField usernameField, productNameField;
    JTextArea commentArea;
    JButton submitButton,goBackButton;
    JRadioButton rating1, rating2, rating3, rating4, rating5;
    ButtonGroup ratingGroup;

    public RatingFrame() {
        // Set up the main frame
        setTitle("Product Feedback & Rating");
        setSize(700, 700);
        setLayout(null);

        // Set the background color to grey
        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);
        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 200, 30);

        JLabel productLabel = new JLabel("Product Name:");
        productLabel.setBounds(50, 100, 100, 30);
        productNameField = new JTextField();
        productNameField.setBounds(150, 100, 200, 30);

        JLabel ratingLabel = new JLabel("Rating:");
        ratingLabel.setBounds(50, 150, 100, 30);

        rating1 = new JRadioButton("1 Star");
        rating2 = new JRadioButton("2 Stars");
        rating3 = new JRadioButton("3 Stars");
        rating4 = new JRadioButton("4 Stars");
        rating5 = new JRadioButton("5 Stars");

        // Position the rating radio buttons
        rating1.setBounds(150, 150, 80, 30);
        rating2.setBounds(230, 150, 80, 30);
        rating3.setBounds(310, 150, 80, 30);
        rating4.setBounds(150, 180, 80, 30);
        rating5.setBounds(230, 180, 80, 30);

        // Set grey background for radio buttons
        rating1.setBackground(Color.LIGHT_GRAY);
        rating2.setBackground(Color.LIGHT_GRAY);
        rating3.setBackground(Color.LIGHT_GRAY);
        rating4.setBackground(Color.LIGHT_GRAY);
        rating5.setBackground(Color.LIGHT_GRAY);

        // Group the radio buttons
        ratingGroup = new ButtonGroup();
        ratingGroup.add(rating1);
        ratingGroup.add(rating2);
        ratingGroup.add(rating3);
        ratingGroup.add(rating4);
        ratingGroup.add(rating5);

        JLabel commentLabel = new JLabel("Comments:");
        commentLabel.setBounds(50, 230, 100, 30);
        commentArea = new JTextArea();
        commentArea.setBounds(150, 230, 300, 100);

        submitButton = new JButton("Submit Feedback");
        submitButton.setBackground(Color.yellow);
        submitButton.setForeground(Color.blue);
        submitButton.setBounds(200, 350, 150, 40);

        goBackButton = new JButton("Go Back");
        goBackButton.setBackground(Color.GRAY);
        goBackButton.setForeground(Color.red);
        goBackButton.setBounds(100, 400, 300, 30);

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashboardFrame().setVisible(true);
                dispose();
            }
        });

        // Add components to the frame
        add(usernameLabel);
        add(usernameField);
        add(productLabel);
        add(productNameField);
        add(ratingLabel);
        add(rating1);
        add(rating2);
        add(rating3);
        add(rating4);
        add(rating5);
        add(commentLabel);
        add(commentArea);
        add(submitButton);
        add(goBackButton);

        // Set the frame visibility and close operation
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ActionListener for the submit button
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String productName = productNameField.getText();
                int rating = getSelectedRating();
                String comments = commentArea.getText();

                if (username.isEmpty() || productName.isEmpty() || rating == 0) {
                    JOptionPane.showMessageDialog(null, "Please fill in all the details and select a rating!");
                } else {
                    storeFeedbackInDatabase(username, productName, rating, comments);
                }
            }
        });
    }

    // Retrieve the selected rating from radio buttons
    private int getSelectedRating() {
        if (rating1.isSelected()) {
            return 1;
        } else if (rating2.isSelected()) {
            return 2;
        } else if (rating3.isSelected()) {
            return 3;
        } else if (rating4.isSelected()) {
            return 4;
        } else if (rating5.isSelected()) {
            return 5;
        } else {
            return 0;
        }
    }

    // Store the feedback into the feedback table in the database
    private void storeFeedbackInDatabase(String username, String productName, int rating, String comments) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/organicfarmingdb", "root", "Venky@2005");
            String query = "INSERT INTO feedback (username, product_name, rating, comments) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, productName);
            pst.setInt(3, rating);
            pst.setString(4, comments);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Thank you for your feedback!");
            }

            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Unable to store feedback!");
        }
    }

    public static void main(String[] args) {
        new RatingFrame();
    }
}
