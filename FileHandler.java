import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class FileHandler 
{
    private static final String FILE_PATH = "resources/phones.txt";

    public static void savePhone(Phone phone) 
	{
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) 
		{
            writer.write(phone.toString() + "\n");
            System.out.println("Phone data saved to file: " + FILE_PATH);
        } catch (IOException e) 
		{
            e.printStackTrace();
        }
    }

    public static boolean isPhoneRegistered(String serialNumber) 
	{
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) 
		{
            while (scanner.hasNextLine()) 
			{
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts[1].equals(serialNumber)) 
				{
                    return true;
                }
            }
        } catch (IOException e) 
		{
            e.printStackTrace();
        }
        return false;
    }
}