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
     * ReportLocation constructor
     * @param r the Report object to pull data from
     */
    public ReportLocation(Report r) {
        this.longitude = r.getReportLong();
        this.latitude = r.getReportLat();
        this.title = r.getTitle();
        this.description = r.getDescription();
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
     * @param latitude the latitude of the Report
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
     * Using title to set the title of report location
     * @param title the Title of the Report Location
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
     * Set the description using the param
     * @param description The description of the ReportLocation
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
     * Sets the longitude
     * @param longitude The longitude of the ReportLocation
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
