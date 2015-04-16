package Models;

import java.util.ArrayList;
import java.util.Date;


public class Car
{
    private int carID;
    private String vin;
    private String make;
    private String model;
    private String color;
    private String status;
    private int year;
    private int mileage;
    private int cylinders;
    private int engine;
    private int body;
    private String vehicleType;
    private String transmission;
    private String drivetrain;
    private String gas;
    private ArrayList<Date> date;
    private ArrayList<String> description; 
    
    public Car (
     int carID, String vin, String make, String model, String color, String status,
     int year, int mileage, int cylinders, int engine, int body,
     String vehicleType, String transmission, String drivetrain, String gas)
    {
        this.carID = carID;
        this.vin= vin;
        this.make = make;
        this.model = model;
        this.color = color;
        this.status = status;
        this.year = year;
        this.mileage = mileage;
        this.cylinders = cylinders;
        this.engine = engine;
        this.body = body;
        this.vehicleType = vehicleType;
        this.transmission = transmission;
        this.drivetrain = drivetrain;
        this.gas = gas;
        
        date = new ArrayList();
        description = new ArrayList();
    }

    public Car(){}

    /**
     * @return the carID
     */
    public int getCarID() {
        return carID;
    }

    /**
     * @param carID the carID to set
     */
    public void setCarID(int carID) {
        this.carID = carID;
    }

    /**
     * @return the vin
     */
    public String getVin() {
        return vin;
    }

    /**
     * @param vin the vin to set
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @param make the make to set
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the mileage
     */
    public int getMileage() {
        return mileage;
    }

    /**
     * @param mileage the mileage to set
     */
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    /**
     * @return the cylinders
     */
    public int getCylinders() {
        return cylinders;
    }

    /**
     * @param cylinders the cylinders to set
     */
    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    /**
     * @return the engine
     */
    public int getEngine() {
        return engine;
    }

    /**
     * @param engine the engine to set
     */
    public void setEngine(int engine) {
        this.engine = engine;
    }

    /**
     * @return the body
     */
    public int getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(int body) {
        this.body = body;
    }

    /**
     * @return the vehicleType
     */
    public String getVehicleType() {
        return vehicleType;
    }

    /**
     * @param vehicleType the vehicleType to set
     */
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * @return the transmission
     */
    public String getTransmission() {
        return transmission;
    }

    /**
     * @param transmission the transmission to set
     */
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * @return the drivetrain
     */
    public String getDrivetrain() {
        return drivetrain;
    }

    /**
     * @param drivetrain the drivetrain to set
     */
    public void setDrivetrain(String drivetrain) {
        this.drivetrain = drivetrain;
    }

    /**
     * @return the gas
     */
    public String getGas() {
        return gas;
    }

    /**
     * @param gas the gas to set
     */
    public void setGas(String gas) {
        this.gas = gas;
    }

    /**
     * @return the date
     */
    public ArrayList<Date> getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(ArrayList<Date> date) {
        this.date = date;
    }

    /**
     * @return the description
     */
    public ArrayList<String> getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }
}

