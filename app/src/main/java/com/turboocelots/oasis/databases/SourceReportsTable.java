package com.turboocelots.oasis.databases;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.turboocelots.oasis.models.WaterSourceReport;

/**
 * Created by mlin on 3/29/17.
 */

public class SourceReportsTable implements BaseColumns {
    public static final String TABLE_NAME = "source_reports";
    public static final String COLUMN_NAME_REPORT_NUMBER = "report_number";
    public static final String COLUMN_NAME_TIMESTAMP = "created_on";
    public static final String COLUMN_NAME_REPORTER_NAME = "reporter_name";
    public static final String COLUMN_NAME_LAT = "lat";
    public static final String COLUMN_NAME_LONG = "long";
    public static final String COLUMN_NAME_WATER_CONDITION = "water_condition";
    public static final String COLUMN_NAME_WATER_TYPE = "water_type";
    public static final String SQL_CREATE_SOURCE_REPORTS_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_REPORT_NUMBER + " TEXT, " +
                    COLUMN_NAME_TIMESTAMP + " DATETIME," +
                    COLUMN_NAME_REPORTER_NAME + " TEXT," +
                    COLUMN_NAME_LAT + " TEXT," +
                    COLUMN_NAME_LONG + " TEXT," +
                    COLUMN_NAME_WATER_CONDITION + " TEXT," +
                    COLUMN_NAME_WATER_TYPE + " TEXT" +
                    ")";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + SourceReportsTable.TABLE_NAME;



    public static final boolean addQualityReport(SQLiteDatabase db, WaterSourceReport report) {
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
        long newRowId = db.replace(TABLE_NAME, null, values);
        return true;

    }
}