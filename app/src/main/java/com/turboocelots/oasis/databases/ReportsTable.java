package com.turboocelots.oasis.databases;

import android.provider.BaseColumns;

import java.util.Calendar;

/**
 * Created by mlin on 3/29/17.
 */

public class ReportsTable implements BaseColumns {
    public static final String TABLE_NAME = "reports";
    public static final String COLUMN_NAME_TIMESTAMP = "created_on";
    public static final String COLUMN_NAME_REPORTER_ID = "reporterId";
    public static final String COLUMN_NAME_LAT = "lat";
    public static final String COLUMN_NAME_LONG = "long";

}
