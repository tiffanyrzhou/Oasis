package com.turboocelots.oasis.databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.turboocelots.oasis.models.constants.OverallCondition;
import com.turboocelots.oasis.models.QualityRepository;
import com.turboocelots.oasis.models.WaterQualityReport;

import java.sql.Timestamp;

/**
 * This class defines the table that will store our WaterQualityReports.
 *
 * The CREATE_QUALITY_REPORTS_TABLE and DELETE_ENTRIES are SQL statements that are invoked
 * by DbHelper when the database is initialized or deleted
 */
public class QualityReportsTable implements BaseColumns {
    private static final String TABLE_NAME = "quality_reports";
    private static final String COLUMN_NAME_REPORT_NUMBER = "report_number";
    private static final String COLUMN_NAME_TIMESTAMP = "created_on";
    private static final String COLUMN_NAME_REPORTER_NAME = "reporter_name";
    private static final String COLUMN_NAME_LAT = "lat";
    private static final String COLUMN_NAME_LONG = "long";
    private static final String COLUMN_NAME_OVERALL_CONDITION = "overall_condition";
    private static final String COLUMN_NAME_VIRUS_PPM = "virus_ppm";
    private static final String COLUMN_NAME_CONTAMINANTS_PPM = "contaminants_ppm";

    public static final String SQL_CREATE_QUALITY_REPORTS_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME_REPORT_NUMBER + " TEXT," +
                    COLUMN_NAME_TIMESTAMP + " DATETIME," +
                    COLUMN_NAME_REPORTER_NAME + " TEXT," +
                    COLUMN_NAME_LAT + " DOUBLE," +
                    COLUMN_NAME_LONG + " DOUBLE," +
                    COLUMN_NAME_OVERALL_CONDITION + " TEXT," +
                    COLUMN_NAME_VIRUS_PPM + " DOUBLE," +
                    COLUMN_NAME_CONTAMINANTS_PPM + " DOUBLE" +
                    ")";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            + QualityReportsTable.TABLE_NAME;


    /**
     * Add Quality report to the SQL database
     * @param db the SQLiteDatabase context
     * @param report the Report to add
     */
    public static void addQualityReport(SQLiteDatabase db, WaterQualityReport report) {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_REPORT_NUMBER, report.getReportNumber());
        values.put(COLUMN_NAME_TIMESTAMP, report.getDateTime().getTime());
        values.put(COLUMN_NAME_REPORTER_NAME, report.getReporterName());
        values.put(COLUMN_NAME_LAT, report.getReportLat());
        values.put(COLUMN_NAME_LONG, report.getReportLong());
        values.put(COLUMN_NAME_OVERALL_CONDITION, report.getOverallCondition().toString());
        values.put(COLUMN_NAME_VIRUS_PPM, report.getVirusPPM());
        values.put(COLUMN_NAME_CONTAMINANTS_PPM, report.getContaminantsPPM());
        // Insert the new row, returning the primary key value of the new row
        db.replace(TABLE_NAME, null, values);
    }

    /**
     * A function that will populate the QualityRepository with the corresponding
     * QualityReports stored in the SQLite database
     * @param db the SQLiteDatabase context to query from
     */
    public static void loadQualityReports (SQLiteDatabase db) {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                QualityReportsTable._ID,
                QualityReportsTable.COLUMN_NAME_TIMESTAMP,
                QualityReportsTable.COLUMN_NAME_REPORT_NUMBER,
                QualityReportsTable.COLUMN_NAME_REPORTER_NAME,
                QualityReportsTable.COLUMN_NAME_LAT,
                QualityReportsTable.COLUMN_NAME_LONG,
                QualityReportsTable.COLUMN_NAME_OVERALL_CONDITION,
                QualityReportsTable.COLUMN_NAME_VIRUS_PPM,
                QualityReportsTable.COLUMN_NAME_CONTAMINANTS_PPM
        };

        // Filter results WHERE username = mUsername

        // Sort by ID
        String sortOrder =
                QualityReportsTable._ID + " DESC";

        Cursor cursor = db.query(
                QualityReportsTable.TABLE_NAME,                     // The table to query
                projection,                                // The columns to return
                null,                                      // The columns for the WHERE clause
                null,                                      // The values for the WHERE clause
                null,                                      // don't group the rows
                null,                                      // don't filter by row groups
                sortOrder                                  // The sort order
        );

        while (cursor.moveToNext()) {
            cursor.getLong(cursor.getColumnIndexOrThrow(QualityReportsTable._ID));
            long epochTime = cursor.getLong(cursor.getColumnIndexOrThrow(
                    QualityReportsTable.COLUMN_NAME_TIMESTAMP));
            String reportNumber = cursor.getString(cursor.getColumnIndexOrThrow(
                    QualityReportsTable.COLUMN_NAME_REPORT_NUMBER));
            String reporterName = cursor.getString(cursor.getColumnIndexOrThrow(
                    QualityReportsTable.COLUMN_NAME_REPORTER_NAME));
            double lat = cursor.getDouble(cursor.getColumnIndexOrThrow(
                    QualityReportsTable.COLUMN_NAME_LAT));
            double lng = cursor.getDouble(cursor.getColumnIndexOrThrow(
                    QualityReportsTable.COLUMN_NAME_LONG));
            String conditionString = cursor.getString(cursor.getColumnIndexOrThrow(
                    QualityReportsTable.COLUMN_NAME_OVERALL_CONDITION));
            double virusPPM = cursor.getDouble(cursor.getColumnIndexOrThrow(
                    QualityReportsTable.COLUMN_NAME_VIRUS_PPM));
            double contaminantsPPM = cursor.getDouble(cursor.getColumnIndexOrThrow(
                    QualityReportsTable.COLUMN_NAME_CONTAMINANTS_PPM));

            OverallCondition overallCondition = OverallCondition.valueOf(conditionString);

            Timestamp timestamp = new Timestamp(epochTime);

            WaterQualityReport newReport = new WaterQualityReport(reportNumber,
                    timestamp, reporterName, lat, lng, overallCondition, virusPPM, contaminantsPPM);
            QualityRepository.addReport(newReport);
        }
        cursor.close();
    }


}
