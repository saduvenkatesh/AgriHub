
import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginSignupFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signupButton;

    public LoginSignupFrame() {
        setTitle("Login / Signup");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setBackground(Color.black);
        ImageIcon hulk = new ImageIcon("APP/src/hulk.jpg");
        setIconImage(hulk.getImage());

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(20, 20, 100, 25);
        
        add(usernameLabel);
       
        usernameField = new JTextField();
        usernameField.setBounds(130, 20, 150, 25);
      
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 60, 100, 25);
       
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 60, 150, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(30, 100, 100, 25);
        loginButton.setFocusable(false);
        loginButton.setBackground(Color.GRAY);
        loginButton.setForeground(Color.red);
        add(loginButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(150, 100, 100, 25);
        loginButton.setFocusable(false);
        signupButton.setBackground(Color.GRAY);
        signupButton.setForeground(Color.red);
        add(signupButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup();
            }
        });
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                new DashboardFrame().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void signup() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be empty.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String checkQuery = "SELECT * FROM Users WHERE username = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Username already exists.");
            } else {
                String insertQuery = "INSERT INTO Users (username, password) VALUES (?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                insertStmt.setString(1, username);
                insertStmt.setString(2, password);
                insertStmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Signup successful. You can now login.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginSignupFrame().setVisible(true));
    }
}
