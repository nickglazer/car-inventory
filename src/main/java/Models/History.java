package main.java.Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Frank Brandstetter
 */
public class History {

    private int historyID;
    private int carID;
    private Date actionDate;
    private String description;
    public String readableDate;

    public History() {
    }

    public int getHistoryID() {
        return historyID;
    }

    public void setHistoryID(int historyID) {
        this.historyID = historyID;
    }

    public int getCarID() {
        return carID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
        DateFormat readableFormat = new SimpleDateFormat("MM/dd/yyyy");
        this.readableDate = readableFormat.format(this.actionDate);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
