package Controllers;
import Models.Car;
import Models.Customer;
import Models.Order;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Used for all database operations
 * @author Rechee Jozil
 */
public class Database
{
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static final String DB_URL = "jdbc:mysql://recheejinstance.cx1ynpvooriw.us-west-2.rds.amazonaws.com:3306/CarSales";

    //  Database credentials
    private static final String USER = "recheej";
    private static final String PASS = "usf_dev1992";

    public Database()
    {

    }

    /**
     * Returns a Connection from the database which is then used to run queries
     * @return A connection object if we can successfully connect to database. Null otherwise
     */
    private Connection getDatabaseConnection()
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

    /**
     * Inserts a new car record into database
     * @param stringFields A map which has database columns as keys and database value as value. (String columns only).
     * @param intFields A map which has database columns as keys and database value as value. (Integer columns only).
     */
    public void insertCar(Map stringFields, Map intFields)
    {
        String insertStatement = this.createCarInsertStatement(intFields, stringFields);

        int affectedRows = this.executeUpdate(insertStatement);

        System.out.println(affectedRows);
    }

    /**
     * Returns the number of database fields which are not null from a map object
     * @param keys An array of the keys of the passed in fields
     * @param fields A map of the database columns, with their associated values
     * @see Controllers.Database#insertCar(java.util.Map, java.util.Map)
     * @return int that has the count of active fields
     */
    private int activeFieldCount(String[] keys, Map fields)
    {
        int activeCount = 0;
        for(String key : keys)
        {
            String field = (String) fields.get(key);
            if(field != null && !field.equals(""))
            {
                activeCount++;
            }
        }

        return activeCount;
    }

    /**
     * Takes a database column map and returns the keys as an array
     * @param targetMap Map that has the database columns as keys
     * @return String array that contains keys of database map
     */
    private String[] keysOfMap(Map targetMap)
    {
        return (String[]) targetMap.keySet().toArray(new String[targetMap.size()]);
    }

    /**
     * Takes database maps and returns the insert query statement
     * @param intFields Database map for the int columns
     * @param stringFields Database map for string columns
     * @return String value of car insert statement
     */
    private String createCarInsertStatement(Map intFields, Map stringFields)
    {
        String[] intKeys = this.keysOfMap(intFields);
        String[] stringKeys = this.keysOfMap(stringFields);

        int activeInts = this.activeFieldCount(intKeys, intFields);
        int activeStrings = this.activeFieldCount(stringKeys, stringFields);

        int totalInsertSize = activeInts + activeStrings;

        int insertCount = 0;

        String columnStatement = "(";

        String valuesStatement = "(";

        for(String key: intKeys)
        {
            String fieldToInsert = (String) intFields.get(key);

            if(fieldToInsert == null || fieldToInsert.equals(""))
            {
                continue;
            }

            insertCount++;

            if(insertCount == totalInsertSize)
            {
                columnStatement += String.format("%s)", key);

                valuesStatement += String.format("%d)", Integer.parseInt(fieldToInsert));

                return String.format("insert into Cars %s values %s", columnStatement, valuesStatement);
            }
            else
            {
                columnStatement += String.format("%s, ", key);

                valuesStatement += String.format("%d, ", Integer.parseInt(fieldToInsert));
            }
        }

        for(String key: stringKeys)
        {
            String fieldToInsert = (String) stringFields.get(key);

            if(fieldToInsert == null || fieldToInsert.equals(""))
            {
                continue;
            }

            insertCount++;

            if(insertCount == totalInsertSize)
            {
                columnStatement += String.format("%s)", key);

                valuesStatement += String.format("'%s')", fieldToInsert);

                return String.format("insert into Cars %s values %s", columnStatement, valuesStatement);
            }
            else
            {
                columnStatement += String.format("%s, ", key);

                valuesStatement += String.format("'%s', ", fieldToInsert);
            }

        }

        return null;
    }

    public Car[] carsFromResults(ResultSet results)
    {
        ArrayList<Car> cars = new ArrayList<Car>(1);

        int rowCount = 0;
        try
        {
            while(results.next())
            {
                rowCount++;

                Car resultCar = new Car();
                resultCar.setCarID(results.getInt("CarID"));
                resultCar.setMake(results.getString("Make"));
                resultCar.setModel(results.getString("Model"));
                resultCar.setYear(results.getInt("Year"));
                resultCar.setMileage(results.getInt("Mileage"));

                cars.add(resultCar);
            }

            if(rowCount == 0)
            {
                return null;
            }

            return cars.toArray(new Car[cars.size()]);
        }
        catch(SQLException e)
        {
            return null;
        }
    }

    /**
     * Returns list of cars for given search fields
     * @param stringFields Database map for the string columns
     * @param intFields Database map for the int columns
     * @return Array of cars returned from search or null if there's no cars for that search
     */
    public Car[] searchForCars(Map stringFields, Map intFields)
    {
        String sqlStatement = this.carSearchSQL(stringFields, intFields);

        ResultSet results = this.executeQuery(sqlStatement);

        return this.carsFromResults(results);
    }

    /**
     * Very similar to how we get the insert SQL
     * @see Controllers.Database#insertCar(java.util.Map, java.util.Map)
     * @param stringFields Database map for the string columns
     * @param intFields Database map for the int columns
     * @return String for car search sql
     */
    private String carSearchSQL(Map stringFields, Map intFields) {
        String[] stringKeys = this.keysOfMap(stringFields);
        String[] intKeys = this.keysOfMap(intFields);

        String sqlStatement = "select CarID, Make, Model, Year, Mileage from Cars ";

        int searchCount = 0;

        String whereStatement = "";

        for (String key: stringKeys)
        {
            String searchField = (String) stringFields.get(key);

            if(searchField == null || searchField.equals(""))
            {
                continue;
            }

            searchCount++;

            if(searchCount == 1)
            {
                whereStatement += String.format("where %s = '%s' ", key, searchField);
            }
            else
            {
                whereStatement += String.format("and %s = '%s' ", key, searchField);
            }
        }

        for (String key: intKeys)
        {
            String searchField = (String) intFields.get(key);

            if(searchField == null || searchField.equals(""))
            {
                continue;
            }

            searchCount++;

            if(searchCount == 1)
            {
                whereStatement += String.format("where %s = %d ", key, Integer.parseInt( searchField));
            }
            else
            {
                whereStatement += String.format("and %s = %d ", key, Integer.parseInt(searchField));
            }
        }

        sqlStatement += whereStatement;

        return sqlStatement;
    }

    /**
     * Used for executing SQL statements other than SELECT
     * @param sqlStatement The sql statement you want to execute
     * @return An int that indicates the amount of rows that were updated
     * or 0 if there was something wrong updated or the query doesn't affect any rows
     */
    public int executeUpdate(String sqlStatement)
    {
        try
        {
            Statement executeStatement = this.getDatabaseConnection().createStatement();
            return executeStatement.executeUpdate(sqlStatement);
        }
        catch (SQLException e)
        {
            System.out.println("Error executing query");
            System.out.println(e);

            return 0;
        }
    }

    /**
     * Used to execute SELECT queries
     * @param sqlStatement The select query you want to run
     * @return A ResultSet object if select was successful. Null otherwise
     */
    public ResultSet executeQuery(String sqlStatement)
    {
        try
        {
            Statement executeStatement = this.getDatabaseConnection().createStatement();
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

    private int deleteCars(Car[] carsToDelete)
    {
        String deleteStatement = "delete from Cars where CarID = ";

        for(int i = 0; i < carsToDelete.length; i++)
        {
            Car currentCar = carsToDelete[i];

            if(i == 0)
            {
                deleteStatement += Integer.toString(currentCar.getCarID());
            }
            else
            {
                deleteStatement += String.format(" and CarID = %d", currentCar.getCarID());
            }
        }

        return this.executeUpdate(deleteStatement);
    }

    /**
     * Inserts new customer into database
     * @param customerToInsert The customer object that you want to insert
     * @return True if insert was successful. False otherwise
     */
    public boolean addCustomer(Customer customerToInsert)
    {
        String sqlStatement = "insert into Customers (First_Name, Last_Name, Email, Phone) ";

        sqlStatement += String.format("values ('%s', '%s', '%s', '%s')", customerToInsert.getFirstName(), customerToInsert.getLastName(),
                customerToInsert.getEmail(), customerToInsert.getPhoneNumber());

        int rowsAffected = this.executeUpdate(sqlStatement);

        if(rowsAffected == 0)
        {
            return false;
        }

        return true;
    }

    public Customer[] allCustomers()
    {
        ResultSet result = this.executeQuery("select * from Customers");

        ArrayList<Customer> customers = new ArrayList<Customer>(1);

        try
        {
            while(result.next())
            {
                Customer resultCustomer = new Customer();

                resultCustomer.setFirstName(result.getString("First_Name"));
                resultCustomer.setLastName(result.getString("Last_Name"));
                resultCustomer.setEmail(result.getString("Email"));
                resultCustomer.setPhoneNumber(result.getString("Phone"));
                resultCustomer.setID(result.getInt("Customer_ID"));

                customers.add(resultCustomer);
            }

            return (Customer[]) customers.toArray(new Customer[customers.size()]);
        }
        catch (SQLException e)
        {
            System.out.println("Error querying results");
            System.out.println(e);

            return null;
        }
    }

    public static String currentDate()
    {
        DateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar currentCalender = Calendar.getInstance();

        return mysqlFormat.format(currentCalender.getTime());
    }

    public boolean insertOrder(Order order, int carID, int customerID)
    {
        String sqlStatement = "insert into Orders (Customer_ID, Car_ID, Order_Date, Price, Down_Payment, ";
        sqlStatement += "Bank, Loan_Number, Loan_Months) values ";
        sqlStatement += String.format("(%d, %d, '%s', %f, %d, '%s', %d, %d)", customerID, carID,
                Database.currentDate(), order.getSalesPrice(), order.getDownPayment(), order.getBank(),
                order.getLoanNumber(), order.getLoanMonths());

        int affectedRecords = this.executeUpdate(sqlStatement);

        if(affectedRecords == 0)
        {
            return false;
        }

        return true;
    }

    /**
     * Adds history to a car
     * @param car The car that you want to record history
     * @param description the message you want to record
     * @return
     */
    public boolean recordCarHistory(Car car, String description)
    {
        String currentDate = Database.currentDate();

        String sqlStatement = "insert into Car_History (Car_ID, Action_Date, Description) values ";

        sqlStatement += String.format("(%d, '%s', '%s')", car.getCarID(), currentDate, description);

        int affectedRecords = this.executeUpdate(sqlStatement);

        if(affectedRecords == 0)
        {
            return false;
        }

        return true;


    }
}
