package com.turboocelots.oasis.controllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

import com.turboocelots.oasis.R;
import com.turboocelots.oasis.databases.DbHelper;
import com.turboocelots.oasis.databases.QualityReportsTable;
import com.turboocelots.oasis.databases.SourceReportsTable;
import com.turboocelots.oasis.databases.UsersTable;
import com.turboocelots.oasis.models.Administrator;
import com.turboocelots.oasis.models.ConditionOfWater;
import com.turboocelots.oasis.models.Manager;
import com.turboocelots.oasis.models.Model;
import com.turboocelots.oasis.models.OverallCondition;
import com.turboocelots.oasis.models.Reporter;
import com.turboocelots.oasis.models.TypeOfWater;
import com.turboocelots.oasis.models.User;
import com.turboocelots.oasis.models.UserTitle;
import com.turboocelots.oasis.models.UserType;
import com.turboocelots.oasis.models.WaterQualityReport;
import com.turboocelots.oasis.models.WaterSourceReport;
import com.turboocelots.oasis.models.Worker;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends AppCompatActivity {
    /**
     * Instantiates the Welcome activity
     * @param savedInstanceState
     */

    private  LoadModelTask modelTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final Button loginButton = (Button) findViewById(R.id.login_id);
        final Button registerButton = (Button) findViewById(R.id.register_id);


        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity  = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(nextActivity);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity  = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(nextActivity);
            }
        });

        showProgress(true);
        modelTask = new LoadModelTask();
        modelTask.execute((Void) null);

        // We should load everything from the model
    }

    /**
     * Shows the progress UI and hides the login and register buttons.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        final Button loginButton = (Button) findViewById(R.id.login_id);
        final Button registerButton = (Button) findViewById(R.id.register_id);


        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginButton.setVisibility(show ? View.GONE : View.VISIBLE);
            loginButton.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginButton.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            registerButton.setVisibility(show ? View.GONE : View.VISIBLE);
            registerButton.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginButton.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            loginButton.setVisibility(show ? View.VISIBLE : View.GONE);
            registerButton.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    private void loadSourceReports (SQLiteDatabase db) {

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


            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(SourceReportsTable._ID));
            long epochTime = cursor.getLong(cursor.getColumnIndexOrThrow(SourceReportsTable.COLUMN_NAME_TIMESTAMP));
            String reportNumber = cursor.getString(cursor.getColumnIndexOrThrow(SourceReportsTable.COLUMN_NAME_REPORT_NUMBER));
            String reporterName = cursor.getString(cursor.getColumnIndexOrThrow(SourceReportsTable.COLUMN_NAME_REPORTER_NAME));
            long lat = cursor.getLong(cursor.getColumnIndexOrThrow(SourceReportsTable.COLUMN_NAME_LAT));
            long lng = cursor.getLong(cursor.getColumnIndexOrThrow(SourceReportsTable.COLUMN_NAME_LONG));
            String conditionString = cursor.getString(cursor.getColumnIndexOrThrow(SourceReportsTable.COLUMN_NAME_WATER_CONDITION));
            String waterTypeString = cursor.getString(cursor.getColumnIndexOrThrow(SourceReportsTable.COLUMN_NAME_WATER_TYPE));

            ConditionOfWater waterCondition = ConditionOfWater.valueOf(conditionString);
            TypeOfWater waterType = TypeOfWater.valueOf(waterTypeString);

            Timestamp timestamp = new Timestamp(epochTime);

            WaterSourceReport newReport = new WaterSourceReport(reportNumber, timestamp, reporterName, lat, lng, waterCondition, waterType);

            Model.getInstance().addReport(newReport);
        }
        cursor.close();
    }

    private void loadQualityReports (SQLiteDatabase db) {

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
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(QualityReportsTable._ID));
            long epochTime = cursor.getLong(cursor.getColumnIndexOrThrow(QualityReportsTable.COLUMN_NAME_TIMESTAMP));
            String reportNumber = cursor.getString(cursor.getColumnIndexOrThrow(QualityReportsTable.COLUMN_NAME_REPORT_NUMBER));
            String reporterName = cursor.getString(cursor.getColumnIndexOrThrow(QualityReportsTable.COLUMN_NAME_REPORTER_NAME));
            double lat = cursor.getDouble(cursor.getColumnIndexOrThrow(QualityReportsTable.COLUMN_NAME_LAT));
            double lng = cursor.getDouble(cursor.getColumnIndexOrThrow(QualityReportsTable.COLUMN_NAME_LONG));
            String conditionString = cursor.getString(cursor.getColumnIndexOrThrow(QualityReportsTable.COLUMN_NAME_OVERALL_CONDITION));
            double virusPPM = cursor.getDouble(cursor.getColumnIndexOrThrow(QualityReportsTable.COLUMN_NAME_VIRUS_PPM));
            double contaminantsPPM = cursor.getDouble(cursor.getColumnIndexOrThrow(QualityReportsTable.COLUMN_NAME_CONTAMINANTS_PPM));

            OverallCondition overallCondition = OverallCondition.valueOf(conditionString);

            Timestamp timestamp = new Timestamp(epochTime);

            WaterQualityReport newReport = new WaterQualityReport(reportNumber, timestamp, reporterName, lat, lng, overallCondition, virusPPM, contaminantsPPM);
            Model.getInstance().addReport(newReport);
        }
        cursor.close();
    }

    private void loadUsers (SQLiteDatabase db) {

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

        List<User> itemIds = new ArrayList<User>();
        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(UsersTable._ID));
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_NAME_PASSWORD));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_NAME_NAME));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_NAME_TITLE));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_NAME_EMAIL));
            String home = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_NAME_HOME));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_NAME_PHONE));
            String type = cursor.getString(cursor.getColumnIndexOrThrow(UsersTable.COLUMN_NAME_USER_TYPE));
            UserType userType = UserType.valueOf(type);
            User newUser;

            if (userType.equals(UserType.Administrator)) {
                newUser = new Administrator(username, password, name, email, home, UserTitle.valueOf(title), phone);
            } else if (userType.equals(UserType.Worker)) {
                newUser = new Worker(username, password, name, email, home, UserTitle.valueOf(title), phone, userType);
            } else if (userType.equals(UserType.Manager)) {
                newUser = new Manager(username, password, name, email, home, UserTitle.valueOf(title), phone);
            } else {
                newUser = new Reporter(username, password, name, email, home, UserTitle.valueOf(title), phone, userType);
            }
            Model.getInstance().addUser(newUser);
        }
        cursor.close();
    }



    public class LoadModelTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            DbHelper uDbHelper = new DbHelper(getApplicationContext());
            SQLiteDatabase db = uDbHelper.getReadableDatabase();
            loadUsers(db);
            loadQualityReports(db);
            loadSourceReports(db);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            showProgress(false);

            if (success) {

            } else {
                // TODO: print error
            }
        }

        @Override
        protected void onCancelled() {
            showProgress(false);
            modelTask = null;
        }
    }


}