package com.turboocelots.oasis.models;

/**
 * Created by Tiffany on 3/6/17.
 * a WaterSourceReport Location
 */

public class ReportLocation {
    private double longitude;
    private double latitude;
    private String title;
    private String description;

    /**
     * constructor of  report location
     * uses r to parse information
     * @param r
     */
    public ReportLocation(Report r) {

        if (r instanceof WaterSourceReport){
            WaterSourceReport wsR = (WaterSourceReport)r;
            this.longitude = wsR.getReportLong();
            this.latitude = wsR.getReportLat();
            this.title = wsR.getDateTime() + " " + wsR.getWaterCondition() + " " + wsR.getWaterType();
            this.description = wsR.getReporterName() + "\n" + wsR.getDateTime() + "\n" + "Water Type: " + wsR.getWaterType() + "\n" +
                    "Water Condition: " +  wsR.getWaterCondition() + "\n"
                    + wsR.getReportNumber();
        } else if (r instanceof WaterQualityReport) {
            WaterQualityReport wqR = (WaterQualityReport)r;
            this.longitude = wqR.getReportLong();
            this.latitude = wqR.getReportLat();
            this.title = wqR.getDateTime() + " " + wqR.getOverallCondition();
            this.description = wqR.getReporterName() + "\n" + wqR.getDateTime() + "\n"
                    + "Overall Condition:" + wqR.getOverallCondition() + "\n" +
                    " Virus PPM: "  + wqR.getVirusPPM()+ "\n" +  " Contaminants PPM: "
                    +  wqR.getContaminantsPPM() + "\n"+ wqR.getReportNumber();

        }

    }

    /**
     *
     * @return double representation of latitude of the report
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * set the latitude of the report
     * @param latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * get the title of this reportLocation
     * @return string representation of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * using title to set the title of report location
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets the description
     * @return string representation of location description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set the description using the param
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * gets longitude
     * @return double representation of longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * sets the longitude
     * @param longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
