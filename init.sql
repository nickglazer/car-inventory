CREATE DATABASE IF NOT EXISTS car_inventory;
USE car_inventory;

CREATE TABLE IF NOT EXISTS Car (
    carID bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    vin VARCHAR(17),
    make VARCHAR(20),
    model VARCHAR(20),
    color VARCHAR(20),
    status VARCHAR(20),
    year int(4),
    mileage int,
    engineDisplacementLiters int(2),
    engineCylinders int(2),
    vehicleType varchar(20),
    bodyType varchar(20),
    transmission varchar(20),
    drivetrain varchar(20),
    gas varchar(20),
    PRIMARY KEY (carID),
    UNIQUE (vin)
);

CREATE TABLE IF NOT EXISTS Customer (
    customerID bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    firstName varchar(20),
    lastName varchar(20),
    email varchar(20),
    phone varchar(20),
    PRIMARY KEY (customerID)
);

CREATE TABLE IF NOT EXISTS CustomerOrder (
    orderID bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    customerID bigint(20) unsigned NOT NULL,
    carID bigint(20) unsigned NOT NULL,
    price float,
    downPayment int,
    bank varchar(20),
    loanNumber bigint(20),
    loanDurationMonths int,
    orderDate Date,
    PRIMARY KEY (orderID)
);

CREATE TABLE IF NOT EXISTS CarHistory (
    historyID bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    carID bigint(20) unsigned NOT NULL,
    actionDate timestamp NOT NULL,
    description varchar(255),
    PRIMARY KEY (historyID)
);
