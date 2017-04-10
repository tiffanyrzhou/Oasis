package com.turboocelots.oasis.models;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Tiffany on 3/7/17.
 */

public class WaterQualityReport extends Report {
    private OverallCondition oCondition;
    private double virusPPM;
    private double contaminantsPPM;

    /**
     * Creates an instance of a report
     * @param reportNumber generated by system,
     * @param dateTime a Timestamp class that references the day it was created
     * @param reportLat  latitude of water source
     * @param reportLong longitude of water source
     * @param reporterName the name of the reporter
     * @param oCondition condition of water
     * @param contaminantsPPM measurement of water quality
     * @param virusPPM measurement of water quality
     */
    public WaterQualityReport(String reportNumber, Timestamp dateTime, String reporterName,
                              double reportLat, double reportLong,
                              OverallCondition oCondition, double virusPPM, double contaminantsPPM){
        super(reportNumber,dateTime,reporterName,reportLat,reportLong);
        this.oCondition = oCondition;
        this.virusPPM = virusPPM;
        this.contaminantsPPM =contaminantsPPM;
    }

    public OverallCondition getOverallCondition() {
        return oCondition;
    }

    public void setOverallCondition(OverallCondition oCondition) {
        this.oCondition = oCondition;
    }

    public Double getVirusPPM() {
        return virusPPM;
    }

    public void setVirusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }

    public Double getContaminantsPPM() {
        return contaminantsPPM;
    }

    public void setContaminantsPPM(double contaminantsPPM) {
        this.contaminantsPPM = contaminantsPPM;
    }

    @Override
    public String toString(){
        return   "Water Quality Report: \n "+reporterName + "\n" + dateTime +"\n" + reportNumber + "\n" +
                "Latitude: " + reportLat + "\n" + "Longitude: " + reportLong + "\n"
                + "Overall Condition:" + oCondition.toString() + "\n" +
                " Virus PPM: "  +virusPPM+ "\n" +  " Contaminants PPM: "  + contaminantsPPM;

    }




}
