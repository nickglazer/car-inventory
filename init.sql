CREATE DATABASE IF NOT EXISTS car_inventory;
USE car_inventory;

CREATE TABLE IF NOT EXISTS Car (
    CarID bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    Vin VARCHAR(17),
    Make VARCHAR(20),
    Model VARCHAR(20),
    Color VARCHAR(20),
    Status VARCHAR(20),
    Year int(4),
    Mileage int,
    Engine_Liters int(2),
    Engine_Cylinders int(2),
    Vehicle_Type varchar(20),
    Body_Type varchar(20),
    Transmission varchar(20),
    Drivetrain varchar(20),
    Gas varchar(20),
    PRIMARY KEY (CarID)
);

CREATE TABLE IF NOT EXISTS Customer (
    Customer_ID bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    First_Name varchar(20),
    Last_Name varchar(20),
    Email varchar(20),
    Phone varchar(20),
    PRIMARY KEY (Customer_ID)
);

CREATE TABLE IF NOT EXISTS CustomerOrder (
    Order_ID bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    Customer_ID bigint(20) unsigned NOT NULL,
    Car_ID bigint(20) unsigned NOT NULL,
    Price float,
    Down_Payment int,
    Bank varchar(20),
    Loan_Number bigint(20),
    Loan_Months int,
    Order_Date Date,
    PRIMARY KEY (Order_ID)
);

CREATE TABLE IF NOT EXISTS CarHistory (
    History_ID bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    Car_ID bigint(20) unsigned NOT NULL,
    Action_Date Date,
    Description varchar(20),
    PRIMARY KEY (HISTORY_ID)
);
