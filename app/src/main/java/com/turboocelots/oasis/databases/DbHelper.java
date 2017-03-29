package com.turboocelots.oasis.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "oasis.db";

    private static final String SQL_CREATE_USERS_TABLE =
            "CREATE TABLE " + UsersTable.TABLE_NAME + " (" +
                    UsersTable._ID + " INTEGER PRIMARY KEY," +
                    UsersTable.COLUMN_NAME_USERNAME + " TEXT," +
                    UsersTable.COLUMN_NAME_PASSWORD + " TEXT," +
                    UsersTable.COLUMN_NAME_NAME + " TEXT," +
                    UsersTable.COLUMN_NAME_EMAIL + " TEXT," +
                    UsersTable.COLUMN_NAME_HOME + " TEXT," +
                    UsersTable.COLUMN_NAME_TITLE + " TEXT," +
                    UsersTable.COLUMN_NAME_PHONE + " TEXT," +
                    UsersTable.COLUMN_NAME_USER_TYPE + " TEXT" +
                    ")";

    private static final String SQL_CREATE_REPORTS_TABLE =
            "CREATE TABLE " + UsersTable.TABLE_NAME + " (" +
                    UsersTable._ID + " INTEGER PRIMARY KEY," +
                    UsersTable.COLUMN_NAME_USERNAME + " TEXT," +
                    UsersTable.COLUMN_NAME_PASSWORD + " TEXT," +
                    UsersTable.COLUMN_NAME_NAME + " TEXT," +
                    UsersTable.COLUMN_NAME_EMAIL + " TEXT," +
                    UsersTable.COLUMN_NAME_HOME + " TEXT," +
                    UsersTable.COLUMN_NAME_TITLE + " TEXT," +
                    UsersTable.COLUMN_NAME_PHONE + " TEXT," +
                    UsersTable.COLUMN_NAME_USER_TYPE + " TEXT" +
                    ")";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UsersTable.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS_TABLE);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
