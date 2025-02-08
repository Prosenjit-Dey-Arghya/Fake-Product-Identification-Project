import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JFrame implements ActionListener 
{
    private JButton registerButton, verifyButton;
    private JPanel panel;

    public WelcomeScreen() 
	{
        super("Fake Product Identification");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

 
        panel = new JPanel();
        panel.setLayout(null); 

      
        panel.setOpaque(false);
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setBounds(0, 0, getWidth(), getHeight()); 
        panel.add(backgroundPanel);

     
        registerButton = new JButton("Register Your Phone");
        registerButton.setBounds(30, 320, 200, 50); 
        registerButton.setForeground(Color.WHITE);
		registerButton.setBackground(Color.BLACK);
        registerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        registerButton.addActionListener(this);

        
        verifyButton = new JButton("Verify Your Phone");
        verifyButton.setBounds(270, 320, 200, 50); 
        verifyButton.setBackground(Color.BLACK);
        verifyButton.setForeground(Color.WHITE);
        verifyButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        verifyButton.addActionListener(this);

       
        panel.add(registerButton);
        panel.add(verifyButton);

        
        this.add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
	{
        if (e.getSource() == registerButton) 
		{
            new RegisterPhoneScreen("resources/qrcodes/", "resources/phones.txt");
            dispose();
        } else if (e.getSource() == verifyButton) 
		{
            new VerifyPhoneScreen();
            dispose();
        }
    }

    public static void main(String[] args) 
	{
        SwingUtilities.invokeLater(new Runnable() 
		{
            @Override
            public void run() 
			{
                new WelcomeScreen().setVisible(true);
            }
        });
    }
}


class BackgroundPanel extends JPanel 
{
    private Image backgroundImage;

    public BackgroundPanel() 
	{
       
        backgroundImage = new ImageIcon("resources/background.jpg").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
