package com.turboocelots.oasis.models;

/**
 * Created by Tiffany on 3/7/17.
 */

public abstract class Report {
    protected String reportNumber;
    protected  String dateTime;
    protected String reporterName;
    protected  double reportLat;
    protected double reportLong;

    public Report(String reportNumber,String dateTime,String reporterName,double reportLat, double reportLong){
        this.reportNumber = reportNumber;
        this.dateTime = dateTime;
        this.reporterName = reporterName;
        this.reportLat = reportLat;
        this.reportLong = reportLong;

    }

    public String getReportNumber() { return reportNumber; }
    public void setReportNumber(String reportNumber) { this.reportNumber = reportNumber; }

    public String getDateTime() { return dateTime; }
    public void setDateTime(String dateTime) { this.dateTime = dateTime; }

    public String getReporterName() { return reporterName; }
    public void setReporterName(String reporterName) { this.reporterName = reporterName; }

    public double getReportLat() { return reportLat; }
    public void setReportLocation(double reportLat) { this.reportLat = reportLat; }

    public double getReportLong() {return reportLong;}
    public void setReportLong(double reportLong) { this.reportLong = reportLong;}

}