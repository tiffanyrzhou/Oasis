package com.turboocelots.oasis.models;

import com.turboocelots.oasis.models.constants.OverallCondition;

import java.sql.Timestamp;

/**
 * Represents a WaterQualityReport
 * A report that includes OverallCondition, virusPPm, and contaminantsPPM
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
                              OverallCondition oCondition, double virusPPM,
                              double contaminantsPPM) {
        super(reportNumber,dateTime,reporterName,reportLat,reportLong);
        this.oCondition = oCondition;
        this.virusPPM = virusPPM;
        this.contaminantsPPM =contaminantsPPM;
    }

    /**
     * Gets the overall condition
     * @return the OverallCondition
     */
    public OverallCondition getOverallCondition() {
        return oCondition;
    }

    /**
     * Sets the overall Condition
     * @param oCondition the new OverallCondition
     */
    public void setOverallCondition(OverallCondition oCondition) {
        this.oCondition = oCondition;
    }

    /**
     * Gets the virus ppm
     * @return the virus ppm
     */
    public Double getVirusPPM() {
        return virusPPM;
    }

    /**
     * Sets the virus ppm
     * @param virusPPM the new PPM to set
     */
    public void setVirusPPM(double virusPPM) {
        this.virusPPM = virusPPM;
    }

    /**
     * Gets the contaminants PPM
     * @return the contaminantsPPM
     */
    public Double getContaminantsPPM() {
        return contaminantsPPM;
    }

    /**
     * Sets the contaminants PPM
     * @param contaminantsPPM the ppm to set
     */
    public void setContaminantsPPM(double contaminantsPPM) {
        this.contaminantsPPM = contaminantsPPM;
    }

    @Override
    public String getTitle() {
        return this.getDateTime() + " " + this.getOverallCondition();
    }

    @Override
    public String getDescription() {
        return this.getReporterName() + "\n" + this.getDateTime() + "\n"
                + "Overall Condition:" + this.getOverallCondition() + "\n" +
                " Virus PPM: "  + this.getVirusPPM()+ "\n" +  " Contaminants PPM: "
                +  this.getContaminantsPPM() + "\n"+ this.getReportNumber();
    }

    @Override
    public String toString(){
        return   "Water Quality Report: \n "+this.getReporterName() + "\n" + this.getDateTime() +
                "\n" + this.getReportNumber() + "\n" +
                "Latitude: " + this.getReportLat() + "\n" + "Longitude: "
                + this.getReportLong() + "\n"
                + "Overall Condition:" + oCondition.toString() + "\n" +
                " Virus PPM: "  +virusPPM+ "\n" +  " Contaminants PPM: "  + contaminantsPPM;
    }




}
