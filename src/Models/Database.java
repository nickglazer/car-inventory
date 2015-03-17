package Models;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by recheejozil on 2/19/15.
 */
public class Database
{
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static final String DB_URL = "jdbc:mysql://recheejinstance.cx1ynpvooriw.us-west-2.rds.amazonaws.com:3306/CarSales";

    //  Database credentials
    private static final String USER = "recheej";
    private static final String PASS = "usf_dev1992";

    private static Connection databaseConnection;

    public Database()
    {
        if(Database.databaseConnection == null)
        {
            Database.databaseConnection = Database.getDatabaseConnection();
        }
    }

    private static Connection getDatabaseConnection()
    {
        Connection databaseConnection;

        try
        {
            Class.forName(Database.JDBC_DRIVER);
            databaseConnection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);

            return databaseConnection;
        }
        catch (Exception e)
        {
            System.out.println("Problems connecting to database");
            System.out.println(e);

            return null;
        }
    }

    private ResultSet executeQuery(String sqlStatement)
    {
        try
        {
            Statement executeStatement = Database.databaseConnection.createStatement();
            return executeStatement.executeQuery(sqlStatement);
        }
        catch (SQLException e)
        {
            System.out.println("Error executing query");
            System.out.println(e);

            return null;
        }
    }

    /**
     * Retrieves all cars in the database
     * @return A car array with all cars in the database
     */
    public Car[] getAllCars()
    {
        ResultSet result = this.executeQuery("select * from Cars");

        ArrayList<Car> cars = new ArrayList<Car>(1);

        try
        {
            while(result.next())
            {
                Car resultCar = new Car();

                resultCar.setCarID(result.getInt("CarID"));
                resultCar.setVin(result.getString("Vin"));
                resultCar.setMake(result.getString("Make"));
                resultCar.setModel(result.getString("Model"));
                resultCar.setColor(result.getString("Color"));
                resultCar.setStatus(result.getString("Status"));
                resultCar.setYear(result.getInt("Year"));
                resultCar.setMileage(result.getInt("Mileage"));
                resultCar.setLot(result.getInt("Lot"));

                cars.add(resultCar);
            }

            return (Car[]) cars.toArray(new Car[cars.size()]);
        }
        catch (SQLException e)
        {
            System.out.println("Error querying results");
            System.out.println(e);

            return null;
        }

    }
}
