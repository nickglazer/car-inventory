package main.java.Controllers;

import main.java.Models.Car;
import main.java.Models.Customer;
import main.java.Models.History;
import main.java.Models.Order;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Used for all database operations
 */
public class Database {

    // TODO make url and credentials based on env or flag
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/car_inventory";

    //  Database credentials
    private static final String USER = "user";
    private static final String PASS = "password";

    public Database() {

    }

    /**
     * Returns a Connection from the database which is then used to run queries
     *
     * @return A connection object if we can successfully connect to database.
     * Null otherwise
     */
    private Connection getDatabaseConnection() {
        Connection databaseConnection;

        try {
            Class.forName(Database.JDBC_DRIVER);
            databaseConnection = DriverManager.getConnection(Database.DB_URL, Database.USER, Database.PASS);

            return databaseConnection;
        } catch (Exception e) {
            System.out.println("Problems connecting to database");
            System.out.println(e);

            return null;
        }
    }

    /**
     * Inserts a new car record into database
     *
     * @param stringFields A map which has database columns as keys and database
     * value as value. (String columns only).
     * @param intFields A map which has database columns as keys and database
     * value as value. (Integer columns only).
     */
    public void insertCar(Map stringFields, Map intFields) {
        String insertStatement = this.createCarInsertStatement(intFields, stringFields);
        this.executeUpdate(insertStatement);
    }

    /**
     * Returns the number of database fields which are not null from a map
     * object
     *
     * @param keys An array of the keys of the passed in fields
     * @param fields A map of the database columns, with their associated values
     * @see Controllers.Database#insertCar(java.util.Map, java.util.Map)
     * @return int that has the count of active fields
     */
    private int activeFieldCount(String[] keys, Map fields) {
        int activeCount = 0;
        for (String key : keys) {
            String field = (String) fields.get(key);
            if (field != null && !field.equals("")) {
                activeCount++;
            }
        }

        return activeCount;
    }

    /**
     * Takes a database column map and returns the keys as an array
     *
     * @param targetMap Map that has the database columns as keys
     * @return String array that contains keys of database map
     */
    private String[] keysOfMap(Map targetMap) {
        return (String[]) targetMap.keySet().toArray(new String[targetMap.size()]);
    }

    /**
     * Takes database maps and returns the insert query statement
     *
     * @param intFields Database map for the int columns
     * @param stringFields Database map for string columns
     * @return String value of car insert statement
     */
    private String createCarInsertStatement(Map intFields, Map stringFields) {
        String[] intKeys = this.keysOfMap(intFields);
        String[] stringKeys = this.keysOfMap(stringFields);

        int activeInts = this.activeFieldCount(intKeys, intFields);
        int activeStrings = this.activeFieldCount(stringKeys, stringFields);

        int totalInsertSize = activeInts + activeStrings;

        int insertCount = 0;

        String columnStatement = "(";

        String valuesStatement = "(";

        for (String key : intKeys) {
            String fieldToInsert = (String) intFields.get(key);

            if (fieldToInsert == null || fieldToInsert.equals("")) {
                continue;
            }

            insertCount++;

            if (insertCount == totalInsertSize) {
                columnStatement += String.format("%s)", key);
                valuesStatement += String.format("%d)", Integer.parseInt(fieldToInsert));

                return String.format("INSERT INTO Car %s VALUES %s", columnStatement, valuesStatement);
            } else {
                columnStatement += String.format("%s, ", key);
                valuesStatement += String.format("%d, ", Integer.parseInt(fieldToInsert));
            }
        }

        for (String key : stringKeys) {
            String fieldToInsert = (String) stringFields.get(key);

            if (fieldToInsert == null || fieldToInsert.equals("")) {
                continue;
            }

            insertCount++;

            if (insertCount == totalInsertSize) {
                columnStatement += String.format("%s)", key);
                valuesStatement += String.format("'%s')", fieldToInsert);

                return String.format("INSERT INTO Car %s VALUES %s", columnStatement, valuesStatement);
            } else {
                columnStatement += String.format("%s, ", key);
                valuesStatement += String.format("'%s', ", fieldToInsert);
            }

        }

        return null;
    }

    public Car[] carsFromResults(ResultSet results) {
        ArrayList<Car> cars = new ArrayList<>(1);

        int rowCount = 0;
        try {
            while (results.next()) {
                rowCount++;

                Car resultCar = new Car();
                resultCar.setCarID(results.getInt("CarID"));
                resultCar.setVin(results.getString("Vin"));
                resultCar.setMake(results.getString("Make"));
                resultCar.setModel(results.getString("Model"));
                resultCar.setColor(results.getString("Color"));
                resultCar.setYear(results.getInt("Year"));
                resultCar.setMileage(results.getInt("Mileage"));
                resultCar.setEngine(results.getInt("Engine_Liters"));
                resultCar.setCylinders(results.getInt("Engine_Cylinders"));
                resultCar.setVehicleType(results.getString("Vehicle_Type"));
                resultCar.setTransmission(results.getString("Transmission"));
                resultCar.setDrivetrain(results.getString("Drivetrain"));
                resultCar.setGas(results.getString("Gas"));
                resultCar.setStatus(results.getString("Status"));

                cars.add(resultCar);
            }

            if (rowCount == 0) {
                return null;
            }

            return cars.toArray(new Car[cars.size()]);
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Returns list of cars for given search fields
     *
     * @param stringFields Database map for the string columns
     * @param intFields Database map for the int columns
     * @return Array of cars returned from search or null if there's no cars for
     * that search
     */
    public Car[] searchForCars(Map stringFields, Map intFields) {
        String sqlStatement = this.carSearchSQL(stringFields, intFields);

        ResultSet results = this.executeQuery(sqlStatement);

        return this.carsFromResults(results);
    }

    /**
     * Very similar to how we get the insert SQL
     *
     * @see Controllers.Database#insertCar(java.util.Map, java.util.Map)
     * @param stringFields Database map for the string columns
     * @param intFields Database map for the int columns
     * @return String for car search sql
     */
    private String carSearchSQL(Map stringFields, Map intFields) {
        String[] stringKeys = this.keysOfMap(stringFields);
        String[] intKeys = this.keysOfMap(intFields);

        String sqlStatement = "SELECT * FROM Car ";

        int searchCount = 0;

        String whereStatement = "";

        for (String key : stringKeys) {
            String searchField = (String) stringFields.get(key);

            if (searchField == null || searchField.equals("")) {
                continue;
            }

            searchCount++;

            if (searchCount == 1) {
                whereStatement += String.format("WHERE %s = '%s' ", key, searchField);
            } else {
                whereStatement += String.format("AND %s = '%s' ", key, searchField);
            }
        }

        for (String key : intKeys) {
            String searchField = (String) intFields.get(key);

            if (searchField == null || searchField.equals("")) {
                continue;
            }

            searchCount++;

            if (searchCount == 1) {
                whereStatement += String.format("WHERE %s = %d ", key, Integer.parseInt(searchField));
            } else {
                whereStatement += String.format("AND %s = %d ", key, Integer.parseInt(searchField));
            }
        }

        if (searchCount == 0) {
            whereStatement += "WHERE Status <> 'Purchased'";
        } else {
            whereStatement += "AND Status <> 'Purchased'";
        }

        sqlStatement += whereStatement;

        return sqlStatement;
    }

    /**
     * Used for executing SQL statements other than SELECT
     *
     * @param sqlStatement The sql statement you want to execute
     * @return An int that indicates the amount of rows that were updated or 0
     * if there was something wrong updated or the query doesn't affect any rows
     */
    public int executeUpdate(String sqlStatement) {
        try {
            Statement executeStatement = this.getDatabaseConnection().createStatement();
            return executeStatement.executeUpdate(sqlStatement);
        } catch (SQLException e) {
            System.out.println("Error executing query");
            System.out.println(e);

            return 0;
        }
    }

    /**
     * Used to execute SELECT queries
     *
     * @param sqlStatement The select query you want to run
     * @return A ResultSet object if select was successful. Null otherwise
     */
    public ResultSet executeQuery(String sqlStatement) {
        try {
            Statement executeStatement = this.getDatabaseConnection().createStatement();
            return executeStatement.executeQuery(sqlStatement);
        } catch (SQLException e) {
            System.out.println("Error executing query");
            System.out.println(e);

            return null;
        }
    }

    /**
     * Retrieves all cars in the database
     *
     * @return A car array with all cars in the database
     */
    public Car[] getAllCars() {
        ResultSet result = this.executeQuery("SELECT * FROM Car");

        return this.carsFromResults(result);
    }

    public Car[] allNewCars() {
        String sqlStatement = "SELECT * FROM Car WHERE Status <> 'Purchased'";

        return this.carsFromResults(this.executeQuery(sqlStatement));
    }

    public int deleteCars(List<Car> carsToDelete) {
        String deleteStatement = "DELETE FROM Car WHERE CarID = ";

        int i = 0;
        for (Car currentCar : carsToDelete) {
            if (i == 0) {
                deleteStatement += Integer.toString(currentCar.getCarID());
            } else {
                deleteStatement += String.format(" OR CarID = %d", currentCar.getCarID());
            }
            i++;
        }

        return this.executeUpdate(deleteStatement);
    }

    /**
     * Inserts new customer into database
     *
     * @param customerToInsert The customer object that you want to insert
     * @return True if insert was successful. False otherwise
     */
    public boolean addCustomer(Customer customerToInsert) {
        String sqlStatement = "INSERT INTO Customer (First_Name, Last_Name, Email, Phone) ";

        sqlStatement += String.format("VALUES ('%s', '%s', '%s', '%s')", customerToInsert.getFirstName(), customerToInsert.getLastName(),
                customerToInsert.getEmail(), customerToInsert.getPhoneNumber());

        int rowsAffected = this.executeUpdate(sqlStatement);

        if (rowsAffected == 0) {
            return false;
        }

        return true;
    }

    public Customer[] customersFromResult(ResultSet result) {
        ArrayList<Customer> customers = new ArrayList<>(1);

        try {
            while (result.next()) {
                Customer resultCustomer = new Customer();

                resultCustomer.setFirstName(result.getString("First_Name"));
                resultCustomer.setLastName(result.getString("Last_Name"));
                resultCustomer.setEmail(result.getString("Email"));
                resultCustomer.setPhoneNumber(result.getString("Phone"));
                resultCustomer.setID(result.getInt("Customer_ID"));

                customers.add(resultCustomer);
            }

            return (Customer[]) customers.toArray(new Customer[customers.size()]);
        } catch (SQLException e) {
            System.out.println("Error querying results");
            System.out.println(e);

            return null;
        }
    }

    public Customer[] allCustomers() {
        ResultSet result = this.executeQuery("SELECT * FROM Customer");

        return this.customersFromResult(result);
    }

    public static String currentDate() {
        DateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar currentCalender = Calendar.getInstance();

        return mysqlFormat.format(currentCalender.getTime());
    }

    public boolean insertOrder(Order order, int carID, int customerID) {
        String sqlStatement = "INSERT INTO CustomerOrder (Customer_ID, Car_ID, Order_Date, Price, Down_Payment, ";
        sqlStatement += "Bank, Loan_Number, Loan_Months) VALUES ";
        sqlStatement += String.format("(%d, %d, '%s', %f, %d, '%s', %d, %d)", customerID, carID,
                Database.currentDate(), order.getSalesPrice(), order.getDownPayment(), order.getBank(),
                order.getLoanNumber(), order.getLoanMonths());

        int affectedRecords = this.executeUpdate(sqlStatement);

        if (affectedRecords == 0) {
            return false;
        }

        return true;
    }

    public void setCarStatus(Car car, String status) {
        String sqlStatement = String.format("UPDATE Car SET Status = '%s' WHERE CarID = '%s'", status, car.getCarID());

        this.executeUpdate(sqlStatement);
    }

    /**
     * Adds history to a car
     *
     * @param car The car that you want to record history
     * @param description the message you want to record
     * @return
     */
    public boolean recordCarHistory(Car car, String description) {
        String currentDate = Database.currentDate();

        String sqlStatement = "INSERT INTO CarHistory (Car_ID, Action_Date, Description) VALUES ";

        sqlStatement += String.format("(%d, '%s', '%s')", car.getCarID(), currentDate, description);

        int affectedRecords = this.executeUpdate(sqlStatement);

        if (affectedRecords == 0) {
            return false;
        }

        return true;
    }

    public History[] historyForCar(Car car) {
        String sqlStatement = String.format("SELECT * FROM CarHistory WHERE Car_ID = %d", car.getCarID());

        ResultSet results = this.executeQuery(sqlStatement);

        ArrayList<History> carHistory = new ArrayList<>(1);

        try {
            while (results.next()) {
                History newHistory = new History();

                newHistory.setHistoryID(results.getInt("History_ID"));
                newHistory.setCarID(results.getInt("Car_ID"));
                newHistory.setActionDate(results.getDate("Action_Date"));
                newHistory.setDescription(results.getString("Description"));

                carHistory.add(newHistory);
            }

            return (History[]) carHistory.toArray(new History[carHistory.size()]);
        } catch (SQLException e) {
            System.out.println("Error querying results");
            System.out.println(e);

            return null;
        }
    }

    public Order[] ordersByName(String firstName, String lastName) {
        String sqlStatement = "SELECT * FROM CustomerOrder INNER JOIN Car ON Car.CarID = CustomerOrder.Car_ID "
                + "INNER JOIN Customer ON Customer.Customer_ID = CustomerOrder.Customer_ID ";
        sqlStatement += String.format("AND Customer.First_Name = '%s' AND Customer.Last_Name = '%s'", firstName, lastName);

        ResultSet results = this.executeQuery(sqlStatement);

        ArrayList<Order> orders = new ArrayList<>(1);

        try {
            while (results.next()) {
                sqlStatement = String.format("SELECT * FROM Car WHERE CarID = %d", results.getInt("CarID"));

                Car orderCar = this.carsFromResults(this.executeQuery(sqlStatement))[0];

                Order newOrder = new Order();
                newOrder.orderCar = orderCar;
                newOrder.setOrderID(results.getInt("Order_ID"));
                newOrder.setCustomerID(results.getInt("Customer_ID"));
                newOrder.setCarID(orderCar.getCarID());
                newOrder.setOrderDate(results.getDate("Order_Date"));
                // TODO where is price?
                newOrder.setSalesPrice(results.getFloat("Down_Payment"));
                newOrder.setBank(results.getString("Bank"));
                newOrder.setLoanNumber(results.getInt("Loan_Number"));
                newOrder.setLoanMonths(results.getInt("Loan_Months"));

                orders.add(newOrder);
            }

            return (Order[]) orders.toArray(new Order[orders.size()]);
        } catch (SQLException e) {
            System.out.println("Error querying results");
            System.out.println(e);

            return null;
        }
    }
}
