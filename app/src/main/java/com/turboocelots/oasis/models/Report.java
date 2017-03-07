package com.turboocelots.oasis.models;
/**
 * Created by Tiffany on 2/25/17.
 * A instance of a Report
 */

public class Report {

    protected String reportNumber;
    protected  String dateTime;
    protected String reporterName;
    protected  double reportLat;
    protected double reportLong;
    protected ConditionOfWater waterCondition;
    protected TypeOfWater waterType;



    /**
     * Creates an instance of a report
     * @param reportNumber generated by syste,
     * @param dateTime date and time report was created
     * @param reportLat  latitude of water source
     * @param reportLong longitude of water source
     * @param reporterName the name of the reporter
     * @param waterCondition condition of the water
     * @param waterType type of water
     */
    public Report(String reportNumber,String dateTime,String reporterName,double reportLat, double reportLong,
                  ConditionOfWater waterCondition, TypeOfWater waterType){
        this.reportNumber = reportNumber;
        this.dateTime = dateTime;
        this.reporterName = reporterName;
        this.reportLat = reportLat;
        this.reportLong = reportLong;
        this.waterCondition = waterCondition;
        this.waterType = waterType;
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

    public ConditionOfWater getWaterCondition() { return waterCondition; }
    public void setWaterCondition(ConditionOfWater waterCondition) { this.waterCondition = waterCondition; }

    public TypeOfWater getWaterType() { return waterType; }
    public void setWaterType(TypeOfWater waterType) { this.waterType = waterType;}
}
