package com.turboocelots.oasis.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mlin on 4/11/17.
 */

final public class QualityRepository {
    private static final List<WaterQualityReport> qualityReports = new ArrayList<>();

    /**
     * Clears the list
     */
    public static void clear() {
        qualityReports.clear();
    }
    /**
     * Adds a waterQualityReport to the app
     * @param report the waterQualityReport to be added
     * @return true if successful, false otherwise
     */
    public static boolean addReport(WaterQualityReport report) {
        if (report == null) return false;
        if (!report.isValid())  {
            return false;
        }
        if (!qualityReports.contains(report)) {
            qualityReports.add(report);
            return true;
        }
        return false;
    }

    /**
     * Gets the WaterQualityReports
     * @return a List of waterSourceReports in the app
     */
    public static List<WaterQualityReport> getReports(){
        return qualityReports;
    }

    /**
     * Filters Reports within longitude and latitude over a given year
     * @param year the year to filter by
     * @param longitude the longitude to match
     * @param lat the latitude to match
     * @return List of WaterQualityReports
     */
    public static List<WaterQualityReport> selectReports(int year, double longitude, double lat){
        List<WaterQualityReport> selectedReports = new ArrayList<>();
        for (WaterQualityReport r: qualityReports) {
            if (r.getYear() == year && (Math.abs(r.getReportLat() - lat) < 0.01) &&
                    (Math.abs(r.getReportLong() - longitude) < 0.01)) {
                selectedReports.add(r);
            }
        }
        return selectedReports;
    }
}
