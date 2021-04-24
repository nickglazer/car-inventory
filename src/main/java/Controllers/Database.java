package main.java.Controllers;

import main.java.Models.Car;
import main.java.Models.Customer;
import main.java.Models.History;
import main.java.Models.Order;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Used for all database operations
 */
public class Database {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DB_URL;

    //  Database credentials
    private static String USER;
    private static String PASS;

    public Database() {
        try ( InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            Database.DB_URL
                    = String.format("jdbc:mysql://%s/%s", prop.getProperty("db_host"), prop.getProperty("db_name"));
            Database.USER = prop.getProperty("db_user");
            Database.PASS = prop.getProperty("db_password");
        } catch (IOException ex) {
            System.out.println("Cannot read configuration");
        }
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

                Car resultCar = parseCarFromResult(results);

                cars.add(resultCar);
            }

            if (rowCount == 0) {
                return null;
            }

            return cars.toArray(new Car[cars.size()]);
        } catch (SQLException e) {
            System.out.println("Error querying results");
            System.out.println(e);
            return null;
        }
    }

    public Car parseCarFromResult(ResultSet results) {
        try {
            Car resultCar = new Car();
            resultCar.setCarID(results.getInt("carID"));
            resultCar.setVin(results.getString("vin"));
            resultCar.setMake(results.getString("make"));
            resultCar.setModel(results.getString("model"));
            resultCar.setColor(results.getString("color"));
            resultCar.setYear(results.getInt("year"));
            resultCar.setMileage(results.getInt("mileage"));
            resultCar.setEngine(results.getInt("engineDisplacementLiters"));
            resultCar.setCylinders(results.getInt("engineCylinders"));
            resultCar.setVehicleType(results.getString("vehicleType"));
            resultCar.setTransmission(results.getString("transmission"));
            resultCar.setDrivetrain(results.getString("drivetrain"));
            resultCar.setGas(results.getString("gas"));
            resultCar.setStatus(results.getString("status"));

            return resultCar;
        } catch (SQLException e) {
            System.out.println("Error querying results");
            System.out.println(e);
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
            whereStatement += "WHERE Status <> 'purchased'";
        } else {
            whereStatement += "AND Status <> 'purchased'";
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
        String sqlStatement = "SELECT * FROM Car WHERE Status <> 'purchased'";
        return this.carsFromResults(this.executeQuery(sqlStatement));
    }

    public int deleteCars(List<Car> carsToDelete) {
        String deleteStatement = "DELETE FROM Car WHERE carID = ";

        int i = 0;
        for (Car currentCar : carsToDelete) {
            if (i == 0) {
                deleteStatement += Integer.toString(currentCar.getCarID());
            } else {
                deleteStatement += String.format(" OR carID = %d", currentCar.getCarID());
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
        String sqlStatement = "INSERT INTO Customer (firstName, lastName, email, phone) ";

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
                Customer resultCustomer = parseCustomerFromResult(result);
                customers.add(resultCustomer);
            }

            return (Customer[]) customers.toArray(new Customer[customers.size()]);
        } catch (SQLException e) {
            System.out.println("Error querying results");
            System.out.println(e);

            return null;
        }
    }

    public Customer parseCustomerFromResult(ResultSet result) {
        try {

            Customer resultCustomer = new Customer();

            resultCustomer.setFirstName(result.getString("firstName"));
            resultCustomer.setLastName(result.getString("lastName"));
            resultCustomer.setEmail(result.getString("email"));
            resultCustomer.setPhoneNumber(result.getString("phone"));
            resultCustomer.setID(result.getInt("customerID"));

            return resultCustomer;
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
        DateFormat mysqlFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar currentCalender = Calendar.getInstance();

        return mysqlFormat.format(currentCalender.getTime());
    }

    public boolean insertOrder(Order order, int carID, int customerID) {
        String sqlStatement = "INSERT INTO CustomerOrder (customerID, carID, orderDate, price, downPayment, ";
        sqlStatement += "bank, loanNumber, loanDurationMonths) VALUES ";
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
        String sqlStatement = String.format("UPDATE Car SET Status = '%s' WHERE carID = '%s'", status, car.getCarID());

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

        String sqlStatement = "INSERT INTO CarHistory (carID, actionDate, description) VALUES ";

        sqlStatement += String.format("(%d, '%s', '%s')", car.getCarID(), currentDate, description);

        int affectedRecords = this.executeUpdate(sqlStatement);

        if (affectedRecords == 0) {
            return false;
        }

        return true;
    }

    public History[] historyForCar(Car car) {
        String sqlStatement = String.format("SELECT * FROM CarHistory WHERE carID = %d ORDER BY actionDate DESC", car.getCarID());

        ResultSet results = this.executeQuery(sqlStatement);

        ArrayList<History> carHistory = new ArrayList<>(1);

        try {
            while (results.next()) {
                History newHistory = new History();

                newHistory.setHistoryID(results.getInt("historyID"));
                newHistory.setCarID(results.getInt("carID"));
                newHistory.setActionDate(results.getTimestamp("actionDate"));
                newHistory.setDescription(results.getString("description"));

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
        String sqlStatement = "SELECT * FROM CustomerOrder INNER JOIN Car ON Car.carID = CustomerOrder.carID "
                + "INNER JOIN Customer ON Customer.customerID = CustomerOrder.customerID ";
        if (!"".equals(firstName)) {
            sqlStatement += String.format("AND Customer.firstName = '%s'", firstName);
        }
        if (!"".equals(lastName)) {
            sqlStatement += String.format(" AND Customer.lastName = '%s'", lastName);
        }

        ResultSet results = this.executeQuery(sqlStatement);

        ArrayList<Order> orders = new ArrayList<>(1);

        try {
            while (results.next()) {
                Car orderCar = this.parseCarFromResult(results);
                Customer customer = this.parseCustomerFromResult(results);

                Order newOrder = new Order();
                newOrder.orderCar = orderCar;
                newOrder.customer = customer;
                newOrder.setOrderID(results.getInt("orderID"));
                newOrder.setCustomerID(results.getInt("customerID"));
                newOrder.setCarID(orderCar.getCarID());
                newOrder.setOrderDate(results.getDate("orderDate"));
                newOrder.setDownPayment(results.getInt("downPayment"));
                newOrder.setSalesPrice(results.getFloat("price"));
                newOrder.setBank(results.getString("bank"));
                newOrder.setLoanNumber(results.getInt("loanNumber"));
                newOrder.setLoanMonths(results.getInt("loanDurationMonths"));

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
