package com.turboocelots.oasis.models;
import com.turboocelots.oasis.models.constants.ConditionOfWater;
import com.turboocelots.oasis.models.constants.TypeOfWater;

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

    private WaterSourceReport exampleSourceReport;

    @Before
    public void setUp() throws Exception {
        SourceRepository.clear(); // Clear the entire UserRepository instance
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
        SourceRepository.addReport(exampleSourceReport);
        assertTrue(SourceRepository.getReports().contains(exampleSourceReport));
    }

    @Test
    public void addReport_null_param() throws Exception {
        exampleSourceReport.setReporterName(null);
        SourceRepository.addReport(exampleSourceReport);
        assertFalse(SourceRepository.getReports().contains(exampleSourceReport));
    }

    @Test
    public void addReport_same_param() throws Exception {
        WaterSourceReport reportCopy = exampleSourceReport;
        SourceRepository.addReport(exampleSourceReport);
        SourceRepository.addReport(reportCopy);
        assertFalse(SourceRepository.getReports().size() > 1);
    }

    @Test
    public void addReport_all_invalid_param() throws Exception {
        //invalid location coordinates
        exampleSourceReport.setReportLat(999999999);
        exampleSourceReport.setReportLong(999999999);
        exampleSourceReport.setReporterName(null);
        exampleSourceReport.setDateTime(null);
        exampleSourceReport.setReportNumber("-1");
        SourceRepository.addReport(exampleSourceReport);
        assertFalse(SourceRepository.getReports().contains(exampleSourceReport));
    }
}