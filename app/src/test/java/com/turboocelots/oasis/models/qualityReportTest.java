package com.turboocelots.oasis.models;
import java.util.Calendar;
import java.sql.Timestamp;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by jacobspeed on 4/10/17.
 */

public class qualityReportTest {
    Model model;
    Report exampleQualityReport;

    @Before
    public void setUp() throws Exception {
        String reportNumber = "000001";
        Timestamp dateTime = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        String reporterName = "Jacob";
        double reportLat = 34.610478;
        double reportLong = -83.687479;
        OverallCondition oCondition = OverallCondition.Safe;
        double virusPPM = 34;
        double contaminantsPPM = 12;

        exampleQualityReport = new WaterQualityReport(reportNumber, dateTime, reporterName, reportLat, reportLong, oCondition, virusPPM, contaminantsPPM);
        model = new Model();
    }

    @Test
    public void addReport_full_parameters() throws Exception {
        model.getInstance().addReport(exampleQualityReport);
        assertTrue(model.getInstance().getReports().contains(exampleQualityReport));
    }

    @Test
    public void addReport_null_parameters() throws Exception {
        exampleQualityReport.setReporterName(null);
        model.getInstance().addReport(exampleQualityReport);
        assertFalse(model.getInstance().getReports().contains(exampleQualityReport));
    }

    @Test
    public void addReport_same_parameters() throws Exception {
        Report reportCopy = exampleQualityReport;
        model.getInstance().addReport(exampleQualityReport);
        model.getInstance().addReport(reportCopy);
        assertFalse(model.getInstance().getReports().contains(exampleQualityReport) && model.getReports().contains(reportCopy));
    }

    @Test
    public void addReport_all_invalid_parameters() throws Exception {
        exampleQualityReport.setReportLat(495875437);
        exampleQualityReport.setReportLong(354354332);
        exampleQualityReport.setReporterName(null);
        exampleQualityReport.setDateTime(null);
        exampleQualityReport.setReportNumber("-5");
        model.getInstance().addReport(exampleQualityReport);
        assertFalse(model.getInstance().getReports().contains(exampleQualityReport));
    }
}
