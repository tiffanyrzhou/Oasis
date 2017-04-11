package com.turboocelots.oasis.databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.turboocelots.oasis.models.ConditionOfWater;
import com.turboocelots.oasis.models.SourceRepository;
import com.turboocelots.oasis.models.TypeOfWater;
import com.turboocelots.oasis.models.WaterSourceReport;

import java.sql.Timestamp;

/**
 * This class defines the table that will store our WaterSourceReports
 * The CREATE_SOURCE_REPORTS_TABLE and DELETE_ENTRIES refer to the SQL statements invoked
 * when DbHelper initializes or deletes the database.
 */

public class SourceReportsTable implements BaseColumns {
    private static final String TABLE_NAME = "source_reports";
    private static final String COLUMN_NAME_REPORT_NUMBER = "report_number";
    private static final String COLUMN_NAME_TIMESTAMP = "created_on";
    private static final String COLUMN_NAME_REPORTER_NAME = "reporter_name";
    private static final String COLUMN_NAME_LAT = "lat";
    private static final String COLUMN_NAME_LONG = "long";
    private static final String COLUMN_NAME_WATER_CONDITION = "water_condition";
    private static final String COLUMN_NAME_WATER_TYPE = "water_type";
    public static final String SQL_CREATE_SOURCE_REPORTS_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_REPORT_NUMBER + " TEXT, " +
                    COLUMN_NAME_TIMESTAMP + " DATETIME," +
                    COLUMN_NAME_REPORTER_NAME + " TEXT," +
                    COLUMN_NAME_LAT + " DOUBLE," +
                    COLUMN_NAME_LONG + " DOUBLE," +
                    COLUMN_NAME_WATER_CONDITION + " TEXT," +
                    COLUMN_NAME_WATER_TYPE + " TEXT" +
                    ")";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS "
            + SourceReportsTable.TABLE_NAME;

    /**
     * Adds a Source Report to the SQLite database
     * @param db the SQLiteDatabase to query on
     * @param report the Report to add to the Database
     */
    public static void addSourceReport(SQLiteDatabase db, WaterSourceReport report) {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_REPORT_NUMBER, report.getReportNumber());
        values.put(COLUMN_NAME_TIMESTAMP, report.getDateTime().getTime());
        values.put(COLUMN_NAME_REPORTER_NAME, report.getReporterName());
        values.put(COLUMN_NAME_LAT, report.getReportLat());
        values.put(COLUMN_NAME_LONG, report.getReportLong());
        values.put(COLUMN_NAME_WATER_CONDITION, report.getWaterCondition().toString());
        values.put(COLUMN_NAME_WATER_TYPE, report.getWaterType().toString());
        // Insert the new row, returning the primary key value of the new row
        db.replace(TABLE_NAME, null, values);
    }

    /**
     * Loads the Source Reports into the SourceRepository object
     * @param db the SQLiteDatabase context
     */
    public static void loadSourceReports (SQLiteDatabase db) {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.

        String[] projection = {
                SourceReportsTable._ID,
                SourceReportsTable.COLUMN_NAME_TIMESTAMP,
                SourceReportsTable.COLUMN_NAME_REPORTER_NAME,
                SourceReportsTable.COLUMN_NAME_REPORT_NUMBER,
                SourceReportsTable.COLUMN_NAME_LAT,
                SourceReportsTable.COLUMN_NAME_LONG,
                SourceReportsTable.COLUMN_NAME_WATER_CONDITION,
                SourceReportsTable.COLUMN_NAME_WATER_TYPE
        };

        // Filter results WHERE username = mUsername

        // Sort by ID
        String sortOrder =
                SourceReportsTable._ID + " DESC";

        Cursor cursor = db.query(
                SourceReportsTable.TABLE_NAME,                     // The table to query
                projection,                                // The columns to return
                null,                                      // The columns for the WHERE clause
                null,                                      // The values for the WHERE clause
                null,                                      // don't group the rows
                null,                                      // don't filter by row groups
                sortOrder                                  // The sort order
        );

        while (cursor.moveToNext()) {
            cursor.getLong(cursor.getColumnIndexOrThrow(SourceReportsTable._ID));
            long epochTime = cursor.getLong(cursor.getColumnIndexOrThrow(
                    SourceReportsTable.COLUMN_NAME_TIMESTAMP));
            String reportNumber = cursor.getString(cursor.getColumnIndexOrThrow(
                    SourceReportsTable.COLUMN_NAME_REPORT_NUMBER));
            String reporterName = cursor.getString(cursor.getColumnIndexOrThrow(
                    SourceReportsTable.COLUMN_NAME_REPORTER_NAME));
            long lat = cursor.getLong(cursor.getColumnIndexOrThrow(
                    SourceReportsTable.COLUMN_NAME_LAT));
            long lng = cursor.getLong(cursor.getColumnIndexOrThrow(
                    SourceReportsTable.COLUMN_NAME_LONG));
            String conditionString = cursor.getString(cursor.getColumnIndexOrThrow(
                    SourceReportsTable.COLUMN_NAME_WATER_CONDITION));
            String waterTypeString = cursor.getString(cursor.getColumnIndexOrThrow(
                    SourceReportsTable.COLUMN_NAME_WATER_TYPE));

            ConditionOfWater waterCondition = ConditionOfWater.valueOf(conditionString);
            TypeOfWater waterType = TypeOfWater.valueOf(waterTypeString);

            Timestamp timestamp = new Timestamp(epochTime);

            WaterSourceReport newReport = new WaterSourceReport(reportNumber, timestamp,
                    reporterName, lat, lng, waterCondition, waterType);

            SourceRepository.addReport(newReport);
        }
        cursor.close();
    }
}