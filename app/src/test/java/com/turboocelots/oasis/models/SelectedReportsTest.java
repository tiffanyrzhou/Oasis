package com.turboocelots.oasis.models;

import com.turboocelots.oasis.models.constants.OverallCondition;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by mlin on 4/11/17.
 */

public class SelectedReportsTest {
    private WaterQualityReport exampleSourceReport;
    private Calendar date;

    @Before
    public void setUp() throws Exception {
        date = Calendar.getInstance();
        date.set(2017, 3, 16);
        Timestamp timestamp = new Timestamp(date.getTimeInMillis());
        QualityRepository.clear(); // Clear the entire UserRepository instance
        String reportNumber = "0000001";
        String reporterName = "Michael";
        double reportLat = 40.7635569;
        double reportLong = -73.972309;

        OverallCondition oCondition = OverallCondition.Safe;
        double virusPPM = 1.5;
        double contaminants = 2.5;

        exampleSourceReport = new WaterQualityReport(reportNumber, timestamp, reporterName, reportLat, reportLong, oCondition, virusPPM, contaminants);
    }

    @Test
    public void checkNearbyPurityReport() throws Exception {
        //valid location coordinates
        QualityRepository.addReport(exampleSourceReport);
        assertTrue(QualityRepository.selectReports(date.get(Calendar.YEAR), 40.7635569, -73.972309).size() > 0);
    }

    @Test
    public void checkWrongYear() throws Exception {
        //invalid location coordinates
        QualityRepository.addReport(exampleSourceReport);
        assertFalse(QualityRepository.selectReports(date.get(Calendar.YEAR) - 1, 40.7635569, -73.972309).size() > 0);
    }


    @Test
    public void checkWrongCoordinate() throws Exception {
        //invalid location coordinates
        QualityRepository.addReport(exampleSourceReport);
        assertFalse(QualityRepository.selectReports(date.get(Calendar.YEAR) - 1, 41.7635569, -73.972309).size() > 0);
    }
}
