package com.turboocelots.oasis.databases;

import android.provider.BaseColumns;

/***
 * The SQLite database representation for the Users table
 */

public final class UsersTable implements BaseColumns {
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_NAME_USERNAME = "username";
    public static final String COLUMN_NAME_PASSWORD = "password";
    public static final String COLUMN_NAME_NAME = "full_name";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final String COLUMN_NAME_HOME = "home";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_PHONE = "phone";
    public static final String COLUMN_NAME_USER_TYPE = "user_type";

    public static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + UsersTable.TABLE_NAME;
    // Cannot be instantiated

    public static final String SQL_CREATE_USERS_TABLE =
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
    private UsersTable() {}
}
