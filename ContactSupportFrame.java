import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

public class ContactSupportFrame extends JFrame{
    
    private JButton goBackButton;
     ContactSupportFrame() {
        // Create frame
       
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));
        ImageIcon hulk = new ImageIcon("APP/src/hulk.png");
        setIconImage(hulk.getImage());
        

        // Company Name Label
        JLabel companyNameLabel = new JLabel("Company: Organic Farming", SwingConstants.CENTER);
        add(companyNameLabel);

        // Phone Number Label (with clickable link)
        JLabel phoneLabel = new JLabel("Phone: +91 8555015489", SwingConstants.CENTER);
        phoneLabel.setForeground(Color.BLUE.darker());
        phoneLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(phoneLabel);

        // Email Label (with clickable link)
        JLabel emailLabel = new JLabel("Email: saduvenkatesh141205@gmail.com", SwingConstants.CENTER);
        emailLabel.setForeground(Color.BLUE.darker());
        emailLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(emailLabel);
        
        goBackButton = new JButton("Go Back");
        
       // goBackButton.setBounds(20, 330, 100, 25);
       goBackButton.setForeground(Color.BLUE.darker());
       goBackButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       goBackButton.setBackground(Color.GRAY);
       goBackButton.setForeground(Color.red);
       add(goBackButton);

        // Copyrights Label
        JLabel copyrightLabel = new JLabel("© Copyrights ", SwingConstants.CENTER);
        add(copyrightLabel);

        // Action Listener for Phone Number (opens phone dialer app)
        phoneLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    URI phoneURI = new URI("tel:+918555015489");
                    Desktop.getDesktop().browse(phoneURI);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashboardFrame().setVisible(true);
                dispose();
            }
        });

        // Action Listener for Email (opens default email app)
        emailLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    URI emailURI = new URI("mailto:saduvenkatesh141205@gmail.com");
                    Desktop.getDesktop().mail(emailURI);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Set frame visible
        setVisible(true);
    }
}
