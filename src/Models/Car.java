package Models;


public class Car
{
    public Car (String v, String ma, String mo, String c,
    String s, int y, int mi, int l)
    {
        setVin(v);
        setMake(ma);
        setModel(mo);
        setColor(c);
        setStatus(s);
        setYear(y);
        setMileage(mi);
        setLot(l);
    }

    public Car(){}

    private int carID;
    private String vin;
    private String make;
    private String model;
    private String color;
    private String status;
    private int year;
    private int mileage;
    private int lot;

    public int getCarID(){ return carID; }
    public void setCarID(int carID){ this.carID = carID; }

    public void setVin(String arg)
    {
        vin = arg;
    }
    public String getVin()
    {
        return vin;
    }
    
    public void setMake(String arg)
    {
        make = arg;
    }
    public String getMake()
    {
        return make;
    }
    
    public void setModel(String arg)
    {
        model = arg;
    }
    public String getModel()
    {
        return model;
    }
    
    public void setColor(String arg)
    {
        color = arg;
    }
    public String getColor()
    {
        return color;
    }
    
    public void setStatus(String arg)
    {
        status = arg;
    }
    public String getStatus()
    {
        return status;
    }
    
    public void setYear(int arg)
    {
        year = arg;
    }
    public int getYear()
    {
        return year;
    }
    
    public void setMileage(int arg)
    {
        mileage = arg;
    }
    public int getMileage()
    {
        return mileage;
    }
    
    public void setLot(int arg)
    {
        lot = arg;
    }
    public int getLot()
    {
        return lot;
    }
    
    public void printCarInfo()
    {
        System.out.println("VIN: " + getVin());
        System.out.println("MAKE: " + getMake());
        System.out.println("MODEL: " + getModel());
        System.out.println("YEAR: " + getYear());
        System.out.println("COLOR: " + getColor());
        System.out.println("MILEAGE: " + getMileage());
        System.out.println("LOT: " + getLot());
        System.out.println("STATUS: " + getStatus());
    }
    
    
    /* Test
    
    public static void main (String[] args)
    {
        Car testCar = new Car ("000000FFFF", "Ford", "Mustang", "Red", "AVAILABLE", 
        1978, 203451, 10);
    
        testCar.printCarInfo();
    }
    
    */
}

