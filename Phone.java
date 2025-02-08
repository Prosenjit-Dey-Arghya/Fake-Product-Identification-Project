public class Phone {
    private String name;
    private String serialNumber;
    private String category;
    private double price;

    public Phone(String name, String serialNumber, String category, double price) 
	{
        this.name = name;
        this.serialNumber = serialNumber;
        this.category = category;
        this.price = price;
    }

    public String getName() 
	{
        return name;
    }

    public String getSerialNumber() 
	{
        return serialNumber;
    }

    public String getCategory() 
	{
        return category;
    }

    public double getPrice() 
	{
        return price;
    }

    @Override
    public String toString() 
	{
        return name + "," + serialNumber + "," + category + "," + price;
    }
}