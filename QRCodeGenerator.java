import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import java.io.File;
import java.io.FileOutputStream;

public class QRCodeGenerator 
{
    public static void generateQRCode(String serialNumber, String resourcesDirectory) 
	{ 
        try 
		{
            File directory = new File(resourcesDirectory);
            if (!directory.exists()) 
			{
                directory.mkdirs(); 
            }

            String filePath = resourcesDirectory + File.separator + serialNumber + ".png";
            File file = new File(filePath);

            try (FileOutputStream fos = new FileOutputStream(file)) 
			{
                QRCode.from(serialNumber).to(ImageType.PNG).writeTo(fos);
            }

            System.out.println("QR Code generated at: " + file.getAbsolutePath());
        } catch (Exception e) 
		{
            e.printStackTrace();
        }
    }
}