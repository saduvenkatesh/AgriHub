import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame {
    private JButton tutorialsButton, marketplaceButton, expertsButton, signOutButton,ContactSupport,feedback,farmerButton,consumerButton;

    public DashboardFrame() {
        setTitle("Dashboard");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setBackground(Color.red);
        ImageIcon hulk = new ImageIcon("APP/src/hulk.png");  
        setIconImage(hulk.getImage());
        
        marketplaceButton = new JButton("Marketplace");
        marketplaceButton.setBounds(50, 50, 300, 30);
        marketplaceButton.setFocusable(false);
        marketplaceButton.setBackground(Color.yellow);  
        marketplaceButton.setForeground(Color.blue);
        add(marketplaceButton);
        
        farmerButton = new JButton("Farmer");
        farmerButton.setBounds(50, 100, 300, 30);
        farmerButton.setFocusable(false);
        farmerButton.setBackground(Color.yellow);  
        farmerButton.setForeground(Color.blue);
        add(farmerButton);

        consumerButton = new JButton("Consumer");
        consumerButton.setBounds(50, 150, 300, 30);
        consumerButton.setFocusable(false);
        consumerButton.setBackground(Color.yellow);  
        consumerButton.setForeground(Color.blue);
        add(consumerButton);
        
        expertsButton = new JButton("Experts");
        expertsButton.setBounds(50, 200, 300, 30);
        expertsButton.setFocusable(false);
        expertsButton.setBackground(Color.yellow);  
        expertsButton.setForeground(Color.blue);
        add(expertsButton);

        tutorialsButton = new JButton("View Tutorials");
        tutorialsButton.setBounds(50, 250, 300, 30);
        tutorialsButton.setFocusable(false);
        tutorialsButton.setBackground(Color.yellow);  
        tutorialsButton.setForeground(Color.blue);
        add(tutorialsButton);

        ContactSupport = new JButton("Contact & Support");
        ContactSupport.setBounds(50, 300, 300, 30);
        ContactSupport.setFocusable(false);
        ContactSupport.setBackground(Color.yellow);  
        ContactSupport.setForeground(Color.blue);
        add(ContactSupport);

        feedback = new JButton("Feedback");
        feedback.setBounds(50, 350, 300, 30);
        feedback.setFocusable(false);
        feedback.setBackground(Color.yellow);  
        feedback.setForeground(Color.blue);
        add(feedback);

        signOutButton = new JButton("Sign Out");
        signOutButton.setBounds(50, 400, 300, 30);
        signOutButton.setFocusable(false);
        signOutButton.setBackground(Color.yellow);  
        signOutButton.setForeground(Color.blue);
        add(signOutButton);
     
        farmerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FarmerFrame().setVisible(true);
                dispose();
            }
        });

        consumerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ConsumerFrame().setVisible(true);
                dispose();
            }
        });

        tutorialsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TutorialsFrame().setVisible(true);
                dispose();
            }
        });

        marketplaceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MarketplaceFrame().setVisible(true);
                dispose();
            }
        });

        expertsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExpertsFrame().setVisible(true);
                dispose();
            }
        });
        
        ContactSupport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ContactSupportFrame().setVisible(true);
                dispose();
            }
        });
        
        feedback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RatingFrame().setVisible(true);
                dispose();
            }
        });

        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginSignupFrame().setVisible(true);
                dispose();
            }
        });

        
    }
}
