/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import java.util.Date;

/**
 *
 * @author Nicholas
 */
public class Order {

    private int salesPrice;
    private int customerID;
    private int carID;
    private int downPayment;
    private String bank;
    private int loanNumber;
    private int loanMonths;
    private int orderID;
    private Date orderDate;

    public Order (int salesPrice, int customerID, int carID, int downPayment,
     String bank, int loanNumber, int loanMonths, int orderID, Date orderDate) {
        this.salesPrice = salesPrice;
        this.customerID = customerID;
        this.carID = carID;
        this.downPayment = downPayment;
        this.bank = bank;
        this.loanNumber = loanNumber;
        this.loanMonths = loanMonths;
        this.orderID = orderID;
        this.orderDate = orderDate;
    }
    
    /**
     * @return the salesPrice
     */
    public int getSalesPrice() {
        return salesPrice;
    }

    /**
     * @param salesPrice the salesPrice to set
     */
    public void setSalesPrice(int salesPrice) {
        this.salesPrice = salesPrice;
    }

    /**
     * @return the customerID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID to set
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

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
     * @return the downPayment
     */
    public int getDownPayment() {
        return downPayment;
    }

    /**
     * @param downPayment the downPayment to set
     */
    public void setDownPayment(int downPayment) {
        this.downPayment = downPayment;
    }

    /**
     * @return the bank
     */
    public String getBank() {
        return bank;
    }

    /**
     * @param bank the bank to set
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * @return the loanNumber
     */
    public int getLoanNumber() {
        return loanNumber;
    }

    /**
     * @param loanNumber the loanNumber to set
     */
    public void setLoanNumber(int loanNumber) {
        this.loanNumber = loanNumber;
    }

    /**
     * @return the loanMonths
     */
    public int getLoanMonths() {
        return loanMonths;
    }

    /**
     * @param loanMonths the loanMonths to set
     */
    public void setLoanMonths(int loanMonths) {
        this.loanMonths = loanMonths;
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    
}
