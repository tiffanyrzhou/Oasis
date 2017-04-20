package com.turboocelots.oasis.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository containing all the WaterSourceReports
 */


final public class SourceRepository {
    private static final List<WaterSourceReport> waterSources = new ArrayList<>();

    /**
     * Private constructor; cannot be invoked
     */
    private SourceRepository() {}
    /**
     * Clears the singleton instance
     */
    public static void clear() {
        waterSources.clear();
    }

    /**
     * Gets the WaterSourceReports
     * @return a List of waterSourceReports in the app
     */
    public static List<WaterSourceReport> getReports(){
        return waterSources;
    }

    /**
     * Adds a WaterSourceReport to the app
     * @param report the waterQualityReport to be added
     * @return true if successful, false otherwise
     */
    public static boolean addReport(WaterSourceReport report) {
        if (report == null) return false;
        if (!report.isValid())  {
            return false;
        }
        if (!waterSources.contains(report)) {
            waterSources.add(report);
            return true;
        }
        return false;
    }



}
