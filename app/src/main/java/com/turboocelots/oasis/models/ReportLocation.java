package com.turboocelots.oasis.models;

/**
 * Created by Tiffany on 3/6/17.
 * a Report Location
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
        this.longitude = r.getReportLong();
        this.latitude = r.getReportLat();
        this.title = r.getDateTime() + " " + r.getWaterCondition() + " " + r.getWaterType();
        this.description = r.getReporterName() + "\n" + r.getDateTime() + "\n" + "Water Type: " + r.getWaterType() + "\n" +
                "Water Condition: " +  r.getWaterCondition() + "\n"
                + r.getReportNumber();

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
     * @return double represnetation of longitude
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
