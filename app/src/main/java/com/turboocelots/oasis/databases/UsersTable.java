package com.turboocelots.oasis.databases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import com.turboocelots.oasis.models.Administrator;
import com.turboocelots.oasis.models.Manager;
import com.turboocelots.oasis.models.Reporter;
import com.turboocelots.oasis.models.User;
import com.turboocelots.oasis.models.UserRepository;
import com.turboocelots.oasis.models.UserTitle;
import com.turboocelots.oasis.models.UserType;
import com.turboocelots.oasis.models.Worker;

import java.util.ArrayList;
import java.util.Collection;

/***
 * The SQLite database representation for the Users table
 */

public final class UsersTable implements BaseColumns {
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_NAME_USERNAME = "username";
    private static final String COLUMN_NAME_PASSWORD = "password";
    private static final String COLUMN_NAME_NAME = "full_name";
    private static final String COLUMN_NAME_EMAIL = "email";
    private static final String COLUMN_NAME_HOME = "home";
    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_PHONE = "phone";
    private static final String COLUMN_NAME_USER_TYPE = "user_type";

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

    /**
     * Populates the model with all users in the SQLite database
     * @param db the SQLiteDatabase context to load from
     */
    public static void loadUsers(SQLiteDatabase db)  {
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                UsersTable._ID,
                UsersTable.COLUMN_NAME_USERNAME,
                UsersTable.COLUMN_NAME_PASSWORD,
                UsersTable.COLUMN_NAME_NAME,
                UsersTable.COLUMN_NAME_TITLE,
                UsersTable.COLUMN_NAME_EMAIL,
                UsersTable.COLUMN_NAME_HOME,
                UsersTable.COLUMN_NAME_PHONE,
                UsersTable.COLUMN_NAME_USER_TYPE
        };

        // Filter results WHERE username = mUsername

        // Sort by ID
        String sortOrder =
                UsersTable._ID + " DESC";

        Cursor cursor = db.query(
                UsersTable.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                    // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        while (cursor.moveToNext()) {
            cursor.getLong(cursor.getColumnIndexOrThrow(UsersTable._ID));
            String username = cursor.getString(cursor.getColumnIndexOrThrow(
                    UsersTable.COLUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(
                    UsersTable.COLUMN_NAME_PASSWORD));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(
                    UsersTable.COLUMN_NAME_NAME));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(
                    UsersTable.COLUMN_NAME_TITLE));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(
                    UsersTable.COLUMN_NAME_EMAIL));
            String home = cursor.getString(cursor.getColumnIndexOrThrow(
                    UsersTable.COLUMN_NAME_HOME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(
                    UsersTable.COLUMN_NAME_PHONE));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(
                    UsersTable.COLUMN_NAME_USER_TYPE));
            UserType userType = UserType.valueOf(type);
            User newUser;

            if (userType.equals(UserType.Administrator)) {
                newUser = new Administrator(username, password, name, email, home,
                        UserTitle.valueOf(title), phone);
            } else if (userType.equals(UserType.Worker)) {
                newUser = new Worker(username, password, name, email, home,
                        UserTitle.valueOf(title), phone);
            } else if (userType.equals(UserType.Manager)) {
                newUser = new Manager(username, password, name, email, home,
                        UserTitle.valueOf(title), phone);
            } else {
                newUser = new Reporter(username, password, name, email, home,
                        UserTitle.valueOf(title), phone);
            }
            UserRepository.addUser(newUser);
        }
        cursor.close();
    }

    /**
     * Adds a user to the SQLite Database
     * @param db the SQLiteDatabase Context
     * @param newUser the newUSer to add
     * @return if it was successful
     */
    public static boolean registerUser(SQLiteDatabase db, User newUser) {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                UsersTable._ID,
                UsersTable.COLUMN_NAME_USERNAME,
                UsersTable.COLUMN_NAME_PASSWORD
        };

        // Filter results WHERE username = mUsername

        String selection = UsersTable.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = { newUser.getUsername() };

        // Sort by ID
        String sortOrder =
                UsersTable._ID + " DESC";

        Cursor cursor = db.query(
                UsersTable.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        Collection<Long> itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(UsersTable._ID));
            itemIds.add(itemId);
        }
        cursor.close();
        if (itemIds.isEmpty()) { // No users currently have this username
            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(UsersTable.COLUMN_NAME_USERNAME, newUser.getUsername());
            values.put(UsersTable.COLUMN_NAME_PASSWORD, newUser.getPassword());
            values.put(UsersTable.COLUMN_NAME_NAME, "");
            values.put(UsersTable.COLUMN_NAME_TITLE, UserTitle.NA.toString());
            values.put(UsersTable.COLUMN_NAME_EMAIL, "");
            values.put(UsersTable.COLUMN_NAME_HOME, "");
            values.put(UsersTable.COLUMN_NAME_PHONE, "");
            values.put(UsersTable.COLUMN_NAME_USER_TYPE, newUser.getUserType().toString());
            // Insert the new row, returning the primary key value of the new row
            db.insert(UsersTable.TABLE_NAME, null, values);
            return true;
        }
        return false;
    }

    /**
     * Updates a user in the database
     * @param db the SQLiteDatabase context
     * @param user the user to update
     */
    public static void updateUser(SQLiteDatabase db, User user) {

        // update results WHERE username = mUsername

        String whereClause = UsersTable.COLUMN_NAME_USERNAME + " = ?";
        String[] whereArgs = {user.getUsername()};

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(UsersTable.COLUMN_NAME_USERNAME, user.getUsername());
        values.put(UsersTable.COLUMN_NAME_PASSWORD, user.getPassword());
        values.put(UsersTable.COLUMN_NAME_NAME, user.getName());
        values.put(UsersTable.COLUMN_NAME_TITLE, user.getTitle().toString());
        values.put(UsersTable.COLUMN_NAME_EMAIL, user.getEmail());
        values.put(UsersTable.COLUMN_NAME_HOME, user.getHome());
        values.put(UsersTable.COLUMN_NAME_PHONE, user.getPhone());
        values.put(UsersTable.COLUMN_NAME_USER_TYPE, user.getUserType().toString());
        // Update the user
        db.update(UsersTable.TABLE_NAME, values, whereClause, whereArgs);
    }

    /**
     * Searches the database for a given username, returns true if it is
     * in the database
     * @param db the SQLiteDatabase context
     * @param username username of the user
     * @param password password of the user
     * @return true if user is in the database
     */
    public static boolean attemptLogin(SQLiteDatabase db, String username, String password) {

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                UsersTable._ID,
                UsersTable.COLUMN_NAME_USERNAME,
                UsersTable.COLUMN_NAME_PASSWORD,
                UsersTable.COLUMN_NAME_NAME,
                UsersTable.COLUMN_NAME_TITLE,
                UsersTable.COLUMN_NAME_EMAIL,
                UsersTable.COLUMN_NAME_HOME,
                UsersTable.COLUMN_NAME_PHONE,
                UsersTable.COLUMN_NAME_USER_TYPE

        };

        // Filter results WHERE username = mUsername

        String selection = UsersTable.COLUMN_NAME_USERNAME + " = ?";
        String[] selectionArgs = { username };

        // Sort by ID
        String sortOrder =
                UsersTable._ID + " DESC";

        Cursor cursor = db.query(
                UsersTable.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        Collection<User> itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {

            String dbPassword = cursor.getString(cursor.getColumnIndexOrThrow(
                    UsersTable.COLUMN_NAME_PASSWORD));

            if (password.equals(dbPassword)) {

                cursor.getLong(cursor.getColumnIndexOrThrow(UsersTable._ID));

                String name = cursor.getString(cursor.getColumnIndexOrThrow(
                        UsersTable.COLUMN_NAME_NAME));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(
                        UsersTable.COLUMN_NAME_TITLE));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(
                        UsersTable.COLUMN_NAME_EMAIL));
                String home = cursor.getString(cursor.getColumnIndexOrThrow(
                        UsersTable.COLUMN_NAME_HOME));
                String phone = cursor.getString(cursor.getColumnIndexOrThrow(
                        UsersTable.COLUMN_NAME_PHONE));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(
                        UsersTable.COLUMN_NAME_USER_TYPE));
                UserType userType = UserType.valueOf(type);
                User newUser;
                if (userType.equals(UserType.Administrator)) {
                    newUser = new Administrator(username, password, name, email,
                            home, UserTitle.valueOf(title), phone);
                } else if (userType.equals(UserType.Worker)) {
                    newUser = new Worker(username, password, name, email, home,
                            UserTitle.valueOf(title), phone);
                } else if (userType.equals(UserType.Manager)) {
                    newUser = new Manager(username, password, name, email, home,
                            UserTitle.valueOf(title), phone);
                } else {
                    newUser = new Reporter(username, password, name, email, home,
                            UserTitle.valueOf(title), phone);
                }
                itemIds.add(newUser);
            }
        }
        cursor.close();
        return (!itemIds.isEmpty());
    }

    private UsersTable() {}
}
