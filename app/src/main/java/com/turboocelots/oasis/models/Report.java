package com.turboocelots.oasis.models;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Abstract class that represents a Report
 */

public abstract class Report {
    private String reportNumber;
    private Timestamp dateTime;
    private String reporterName;
    private double reportLat;
    private double reportLong;

    Report(String reportNumber, Timestamp dateTime, String reporterName, double reportLat, double reportLong){
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

    public int getYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(this.getDateTime().getTime());
        return cal.get(Calendar.YEAR);
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
        if (!(obj instanceof Report)) {
            return false;
        }

        final Report other = (Report) obj;
        if ((this.reportNumber == null || other.reportNumber == null)) {
            return false;
        }
        if (this.reportNumber.equals(other.reportNumber)) {
            return true;
        }
        return false;
    }

    /**
     * This method validates whether or not the Report
     * has valid fields
     * @return true if valid, false if invalid.
     */
    public boolean isValid() {
        return (
                !(this.reportNumber == null)
                && !this.reportNumber.equals("")
                && !(this.reporterName == null)
                && (this.dateTime != null)
                && isValidLatLng(this.reportLat, this.reportLong));
    }

    private static boolean isValidLatLng(double lat, double lng){
        if(lat < -90 || lat > 90)
        {
            return false;
        }
        else if(lng < -180 || lng > 180)
        {
            return false;
        }
        return true;
    }



}
