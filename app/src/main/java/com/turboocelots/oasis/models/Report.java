package com.turboocelots.oasis.models;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Tiffany on 3/7/17.
 */

public abstract class Report {
    protected String reportNumber;
    protected Timestamp dateTime;
    protected String reporterName;
    protected double reportLat;
    protected double reportLong;

    public Report(String reportNumber, Timestamp dateTime, String reporterName, double reportLat, double reportLong){
        this.reportNumber = reportNumber;
        this.dateTime = dateTime;
        this.reporterName = reporterName;
        this.reportLat = reportLat;
        this.reportLong = reportLong;
    }

    public String getReportNumber() { return reportNumber; }
    public void setReportNumber(String reportNumber) { this.reportNumber = reportNumber; }

    public Timestamp getDateTime() { return dateTime; }
    public void setDateTime(Timestamp dateTime) { this.dateTime = dateTime; }

    public int getMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(this.getDateTime().getTime());
        return cal.get(Calendar.MONTH);
    }

    public String getReporterName() { return reporterName; }
    public void setReporterName(String reporterName) { this.reporterName = reporterName; }

    public double getReportLat() { return reportLat; }
    public void setReportLocation(double reportLat) { this.reportLat = reportLat; }

    public double getReportLong() {return reportLong;}
    public void setReportLong(double reportLong) { this.reportLong = reportLong;}

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!Report.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final Report other = (Report) obj;

        if ((this.reportNumber == null)  || other.reportNumber == null) {
            return false;
        }
        if (!this.reportNumber.equals(other.reportNumber)) {
            return false;
        }
        return true;
    }


}
