import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPhoneScreen extends JFrame 
{
    private JTextField nameField;
    private JTextField serialField;
    private JTextField categoryField;
    private JTextField priceField;
    private JButton registerButton;
    private JButton backButton; 
    private JLabel resultLabel; 
    private String qrCodePath;
    private String txtFilePath;
    private JPanel panel;

    public RegisterPhoneScreen(String qrCodePath, String txtFilePath) 
	{
        this.qrCodePath = qrCodePath;
        this.txtFilePath = txtFilePath;


        setTitle("Register Your Phone");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        setLocationRelativeTo(null); 


        panel = new BackgroundPanel(); 
        panel.setLayout(null); 


        nameField = new JTextField();
        nameField.setBounds(150, 240, 200, 30);
        panel.add(new JLabel("Phone Name:")).setBounds(30, 240, 100, 30);
        panel.add(nameField);


        serialField = new JTextField();
        serialField.setBounds(150, 280, 200, 30);
        panel.add(new JLabel("Serial Number:")).setBounds(30, 280, 100, 30);
        panel.add(serialField);


        categoryField = new JTextField();
        categoryField.setBounds(150, 320, 200, 30);
        panel.add(new JLabel("Category:")).setBounds(30, 320, 100, 30);
        panel.add(categoryField);

   
        priceField = new JTextField();
        priceField.setBounds(150, 360, 200, 30);
        panel.add(new JLabel("Price:")).setBounds(30, 360, 100, 30);
        panel.add(priceField);


        registerButton = new JButton("Register");
        registerButton.setBounds(30, 410, 150, 40); 
        registerButton.setBackground(Color.BLACK);
        registerButton.setForeground(Color.WHITE);
        registerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        registerButton.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                String name = nameField.getText();
                String serialNumber = serialField.getText();
                String category = categoryField.getText();

                try {
                    double price = Double.parseDouble(priceField.getText());

                    if (name.isEmpty() || serialNumber.isEmpty() || category.isEmpty()) 
					{
                        resultLabel.setText("Please fill in all fields.");
                        return; 
                    }

                    Phone phone = new Phone(name, serialNumber, category, price);
                    FileHandler.savePhone(phone);
                    QRCodeGenerator.generateQRCode(serialNumber, qrCodePath);
                    resultLabel.setText("Phone Registered and QR Code Generated");


                    nameField.setText("");
                    serialField.setText("");
                    categoryField.setText("");
                    priceField.setText("");

                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid price. Please enter a number.");
                } catch (Exception ex) 
				{ 
                    resultLabel.setText("An error occurred: " + ex.getMessage());
                    ex.printStackTrace(); 
                }
            }
        });
        panel.add(registerButton);

       
        backButton = new JButton("Back");
        backButton.setBounds(210, 410, 150, 40); 
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backButton.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                
                dispose();
                
                SwingUtilities.invokeLater(new Runnable() 
				{
                    @Override
                    public void run() 
					{
                        new WelcomeScreen().setVisible(true);
                    }
                });
            }
        });
        panel.add(backButton);

     
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setBounds(30, 460, 340, 30);
        panel.add(resultLabel);

    
        this.add(panel);
        setVisible(true);
    }


    class BackgroundPanel extends JPanel 
	{
        private Image backgroundImage;

        public BackgroundPanel() 
		{
          
            backgroundImage = new ImageIcon("C:\\Users\\Dey Family\\Downloads\\Fake Product Identification Project\\resources\\RegiBackground.jpg").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) 
		{
            super.paintComponent(g);
            
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
