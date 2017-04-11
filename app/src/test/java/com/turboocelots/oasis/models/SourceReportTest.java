package com.turboocelots.oasis.models;
import java.util.Calendar;
import java.sql.Timestamp;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * Created by caylavinzons on 4/9/17.
 */
public class SourceReportTest {

    private Report exampleSourceReport;

    @Before
    public void setUp() throws Exception {
        Model.getInstance().clear(); // Clear the entire Model instance
        String reportNumber = "0000001";
        Timestamp dateTime = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        String reporterName = "Cayla";
        double reportLat = 40.7635569;
        double reportLong = -73.972309;
        ConditionOfWater waterCondition = ConditionOfWater.CLEAR;
        TypeOfWater waterType = TypeOfWater.BOTTLED;

        exampleSourceReport = new WaterSourceReport(reportNumber, dateTime, reporterName, reportLat, reportLong, waterCondition, waterType);
    }

    @Test
    public void addReport_full_params() throws Exception {
        Model.getInstance().addReport(exampleSourceReport);
        assertTrue(Model.getInstance().getReports().contains(exampleSourceReport));
    }

    @Test
    public void addReport_null_param() throws Exception {
        exampleSourceReport.setReporterName(null);
        Model.getInstance().addReport(exampleSourceReport);
        assertFalse(Model.getInstance().getReports().contains(exampleSourceReport));
    }

    @Test
    public void addReport_same_param() throws Exception {
        Report reportCopy = exampleSourceReport;
        Model.getInstance().addReport(exampleSourceReport);
        Model.getInstance().addReport(reportCopy);
        assertFalse(Model.getInstance().getReports().size() > 1);
    }

    @Test
    public void addReport_all_invalid_param() throws Exception {
        //invalid location coordinates
        exampleSourceReport.setReportLat(999999999);
        exampleSourceReport.setReportLong(999999999);
        exampleSourceReport.setReporterName(null);
        exampleSourceReport.setDateTime(null);
        exampleSourceReport.setReportNumber("-1");
        Model.getInstance().addReport(exampleSourceReport);
        assertFalse(Model.getInstance().getReports().contains(exampleSourceReport));
    }

}