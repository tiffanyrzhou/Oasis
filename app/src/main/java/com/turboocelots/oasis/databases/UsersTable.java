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
    // Cannot be instantiated
    private UsersTable() {}
}
