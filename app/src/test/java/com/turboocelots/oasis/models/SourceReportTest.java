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

    Model model;
    Report exampleSourceReport;

    @Before
    public void setUp() throws Exception {
        String reportNumber = "0000001";
        Timestamp dateTime = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        String reporterName = "Cayla";
        double reportLat = 40.7635569;
        double reportLong = -73.972309;
        ConditionOfWater waterCondition = ConditionOfWater.CLEAR;
        TypeOfWater waterType = TypeOfWater.BOTTLED;

        exampleSourceReport = new WaterSourceReport(reportNumber, dateTime, reporterName, reportLat, reportLong, waterCondition, waterType);
        model = new Model();
    }

    @Test
    public void addReport_full_params() throws Exception {
        model.getInstance().addReport(exampleSourceReport);
        assertTrue(model.getReports().contains(exampleSourceReport));
    }

    @Test
    public void addReport_null_param() throws Exception {
        exampleSourceReport.setReporterName(null);
        assertFalse(model.getReports().contains(exampleSourceReport));
    }

    @Test
    public void addReport_same_param()throws Exception {
        Report reportCopy = exampleSourceReport;
        model.getInstance().addReport(exampleSourceReport);
        model.getInstance().addReport(reportCopy);
        assertFalse(model.getReports().contains(exampleSourceReport) && model.getReports().contains(reportCopy));
    }

    @Test
    public void addReport_all_invalid_param() throws Exception {
        //invalid location coordinates
        exampleSourceReport.setReportLocation(999999999);
        exampleSourceReport.setReportLong(999999999);
        exampleSourceReport.setReporterName(null);
        exampleSourceReport.setDateTime(null);
        exampleSourceReport.setReportNumber("-1");
        model.getInstance().addReport(exampleSourceReport);
        assertFalse(model.getReports().contains(exampleSourceReport));
    }

}