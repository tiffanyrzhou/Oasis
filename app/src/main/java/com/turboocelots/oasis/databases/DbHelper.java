package com.turboocelots.oasis.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * This Class Contains code to manage migrations of the SQLite database
 * In order to upgrade the version of the database, and trigger a migration (currently
 * just drops all tables int he database), increment the DATABASE_VERSION variable.
 *
 * The corresponding Table configurations are implemented in the table classes also in
 * this package as static constant strings.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 13;
    private static final String DATABASE_NAME = "oasis.db";



    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsersTable.SQL_CREATE_USERS_TABLE);
        db.execSQL(QualityReportsTable.SQL_CREATE_QUALITY_REPORTS_TABLE);
        db.execSQL(SourceReportsTable.SQL_CREATE_SOURCE_REPORTS_TABLE);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(UsersTable.SQL_DELETE_ENTRIES);
        db.execSQL(QualityReportsTable.SQL_DELETE_ENTRIES);
        db.execSQL(SourceReportsTable.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
