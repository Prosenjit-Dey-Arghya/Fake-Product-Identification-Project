import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class VerifyPhoneScreen extends JFrame 
{
    private JTextField brandField;
    private JButton uploadButton;
    private JButton backButton;
    private JLabel resultLabel;
    private JPanel panel;

    public VerifyPhoneScreen() 
	{
        setTitle("Verify Your Phone");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new BackgroundPanel();
        panel.setLayout(null);

       
        brandField = new JTextField();
        brandField.setBounds(190, 270, 200, 30);
        panel.add(new JLabel("Phone Brand:")).setBounds(70, 270, 100, 30);
        panel.add(brandField);

      
        uploadButton = new JButton("Upload QR Code");
        uploadButton.setBounds(70, 310, 150, 40);
        uploadButton.setBackground(Color.BLACK);
        uploadButton.setForeground(Color.WHITE);
        uploadButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        uploadButton.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) 
				{
                    File selectedFile = fileChooser.getSelectedFile();
                    String decodedSerialNumber = QRCodeVerifier.decodeQRCode(selectedFile);
                    if (decodedSerialNumber != null && FileHandler.isPhoneRegistered(decodedSerialNumber)) 
					{
                        resultLabel.setText("Verified " + brandField.getText());
                    } else 
					{
                        resultLabel.setText("Fake QR Code!");
                    }
                }
            }
        });
        panel.add(uploadButton);

       
        backButton = new JButton("Back");
        backButton.setBounds(250, 310, 150, 40);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backButton.addActionListener(new ActionListener() 
		{
            @Override
            public void actionPerformed(ActionEvent e) 
			{
                dispose();
                SwingUtilities.invokeLater(() -> new WelcomeScreen().setVisible(true));
            }
        });
        panel.add(backButton);

       
        resultLabel = new JLabel("", SwingConstants.CENTER);
        resultLabel.setBounds(70, 370, 340, 30);
        panel.add(resultLabel);

        add(panel);
        setVisible(true);
    }

   
    class BackgroundPanel extends JPanel 
	{
        private Image backgroundImage;

        public BackgroundPanel() 
		{
            backgroundImage = new ImageIcon("C:\\Users\\Dey Family\\Downloads\\Fake Product Identification Project\\resources\\sub_back2.jpg").getImage();
        }

        @Override
        protected void paintComponent(Graphics g) 
		{
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
