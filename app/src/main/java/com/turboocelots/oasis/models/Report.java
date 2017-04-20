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

    private final static double LOWEST_LAT = -90;
    private final static double HIGHEST_LAT = 90;
    private final static double LOWEST_LNG = -180;
    private final static double HIGHEST_LNG = 180;
    Report(String reportNumber, Timestamp dateTime, String
            reporterName, double reportLat, double reportLong){
        this.reportNumber = reportNumber;
        this.dateTime = dateTime;
        this.reporterName = reporterName;
        this.reportLat = reportLat;
        this.reportLong = reportLong;
    }

    /**
     * Gets a report number
     * @return the report number
     */
    public String getReportNumber() { return reportNumber; }

    /**
     * Sets a reportNumber
     * @param reportNumber the number to set this report to
     */
    public void setReportNumber(String reportNumber) { this.reportNumber = reportNumber; }

    /**
     * Gets the time that this Report was generated
     * @return the time this Report was generated
     */
    public Timestamp getDateTime() { return dateTime; }

    /**
     * Sets the time that this report was generated
     * @param dateTime the time this Report was generated
     */
    public void setDateTime(Timestamp dateTime) { this.dateTime = dateTime; }


    /**
     * Gets the month that this report was generated
     * @return the month this report was generated, starting at 0
     */
    public int getMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(this.getDateTime().getTime());
        return cal.get(Calendar.MONTH);
    }

    /**
     * Gets the year this report was generated
     * @return the year this report was generated, e.g. 1997
     *
     */
    public int getYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(this.getDateTime().getTime());
        return cal.get(Calendar.YEAR);
    }

    /**
     * Gets the reporterName
     * @return the name of the User who filed this Report
     */
    public String getReporterName() { return reporterName; }

    /**
     * Sets the reporterName
     * @param reporterName the name to set this Report reporterName to
     */
    public void setReporterName(String reporterName) { this.reporterName = reporterName; }

    /**
     * Gets the Report latitude
     * @return the Report latitude
     */
    public double getReportLat() { return reportLat; }

    /**
     * Sets the report Latitude
     * @param reportLat the latitude of this Report
     */
    public void setReportLat(double reportLat) { this.reportLat = reportLat; }

    /**
     * Gets the report Longitude
     * @return the Report longitude
     */
    public double getReportLong() {return reportLong;}

    /**
     * Sets the Report Longitude
     * @param reportLong the Longitude of this report
     */
    public void setReportLong(double reportLong) { this.reportLong = reportLong;}

    /**
     * Checks for Equality
     * @param obj the other object to compare to
     * @return true if the other object is a Report, and has the same ReportNumber
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Report)) {
            return false;
        }

        final Report other = (Report) obj;
        return !((this.reportNumber == null) || (other.reportNumber == null))
                && this.reportNumber.equals(other.reportNumber);
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

    /**
     * Abstract method to generate Title of Report
     * @return String representing title of Report
     */

    public abstract String getTitle();

    /**
     * Abstract method to generate Description of Report
     * @return String representing Description of Report
     */
    public abstract String getDescription();

    private static boolean isValidLatLng(double lat, double lng){
        if((lat < LOWEST_LAT) || (lat > HIGHEST_LAT))
        {
            return false;
        }
        else if((lng < LOWEST_LNG) || (lng > HIGHEST_LNG))
        {
            return false;
        }
        return true;
    }



}
