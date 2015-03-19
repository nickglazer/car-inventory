package Controllers;
import Models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

    public Database()
    {

    }

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

    public void insertCar(Map stringFields, Map intFields)
    {
        String insertStatement = this.createCarInsertStatement(intFields, stringFields);

        int affectedRows = this.executeUpdate(insertStatement);

        System.out.println(affectedRows);


    }

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

    private String[] keysOfMap(Map targetMap)
    {
        return (String[]) targetMap.keySet().toArray(new String[targetMap.size()]);
    }

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

    public Car[] searchForCars(Map stringFields, Map intFields)
    {
        String sqlStatement = this.carSearchSQL(stringFields, intFields);

        ResultSet results = this.executeQuery(sqlStatement);

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

    private int executeUpdate(String sqlStatement)
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

    private ResultSet executeQuery(String sqlStatement)
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
