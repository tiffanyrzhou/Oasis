package com.turboocelots.oasis.controllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
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


/**
 * Initial Activity
 * Populates the model with data from the Database
 * Displays Login and Register buttons
 */
public class WelcomeActivity extends AppCompatActivity {

    private  LoadModelTask modelTask = null;

    /**
     * Instantiates the Welcome activity
     * @param savedInstanceState Bundle saved instance to restore Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final Button loginButton = (Button) findViewById(R.id.login_id);
        final Button registerButton = (Button) findViewById(R.id.register_id);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity  = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(nextActivity);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
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
    }

    private class LoadModelTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
            DbHelper uDbHelper = new DbHelper(getApplicationContext());
            SQLiteDatabase db = uDbHelper.getReadableDatabase();
            UsersTable.loadUsers(db);
            QualityReportsTable.loadQualityReports(db);
            SourceReportsTable.loadSourceReports(db);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            showProgress(false);
        }

        @Override
        protected void onCancelled() {
            showProgress(false);
            modelTask = null;
        }
    }


}