package com.turboocelots.oasis.models;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Tiffany on 2/25/17.
 * A instance of a WaterSourceReport
 */

public class WaterSourceReport extends Report {


    private ConditionOfWater waterCondition;
    private TypeOfWater waterType;

    /**
     * Creates an instance of a report
     * @param reportNumber generated by system,
     * @param dateTime date and time report was created
     * @param reportLat  latitude of water source
     * @param reportLong longitude of water source
     * @param reporterName the name of the reporter
     * @param waterCondition condition of the water
     * @param waterType type of water
     */
    public WaterSourceReport(String reportNumber, Timestamp dateTime, String reporterName, double reportLat, double reportLong,
                             ConditionOfWater waterCondition, TypeOfWater waterType) {
        super(reportNumber,dateTime,reporterName,reportLat,reportLong);
        this.waterCondition = waterCondition;
        this.waterType = waterType;
    }



    public ConditionOfWater getWaterCondition() { return waterCondition; }
    public void setWaterCondition(ConditionOfWater waterCondition) { this.waterCondition = waterCondition; }

    public TypeOfWater getWaterType() { return waterType; }
    public void setWaterType(TypeOfWater waterType) { this.waterType = waterType;}

    @Override
    public String toString() {
        return "Water Source Report: \n" + reporterName + "\n" + dateTime +"\n" + reportNumber + "\n" +
                "Latitude: " + reportLat + "\n" + "Longitude: " + reportLong + "\n"
                + "Water Condition:" + waterCondition.toString() + "\n" +
                  " Water Type: "  + waterType.toString();
    }
}
