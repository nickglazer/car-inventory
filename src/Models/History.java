package Models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Frank Brandstetter on 4/19/2015.
 */
public class History
{
    public History(){}

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

    private int historyID;
    private int carID;
    private Date actionDate;
    private String description;
    public String readableDate;
}
