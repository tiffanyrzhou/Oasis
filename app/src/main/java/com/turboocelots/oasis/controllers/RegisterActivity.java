package com.turboocelots.oasis.controllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;


import com.turboocelots.oasis.R;
import com.turboocelots.oasis.databases.DbHelper;
import com.turboocelots.oasis.databases.UsersTable;
import com.turboocelots.oasis.models.Administrator;
import com.turboocelots.oasis.models.Manager;
import com.turboocelots.oasis.models.Model;
import com.turboocelots.oasis.models.Reporter;
import com.turboocelots.oasis.models.User;
import com.turboocelots.oasis.models.UserTitle;
import com.turboocelots.oasis.models.UserType;
import com.turboocelots.oasis.models.Worker;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView usernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Spinner userTypeSpinner;
    private String currentUser;

    /**
     * Creates the Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Set up the login form.
        usernameView = (AutoCompleteTextView) findViewById(R.id.username);
        populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptRegister();
                    return true;
                }
                return false;
            }
        });

        Button registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

        Button cancelButton = (Button) findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        userTypeSpinner = (Spinner) findViewById(R.id.userTypeSpinner_id);

        ArrayAdapter<UserType> userTypeArrayAdapter = new ArrayAdapter<UserType>(this,android.R.layout.simple_spinner_item, UserType.values());
        userTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(userTypeArrayAdapter);
    }

    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }

    /**
     * Attempts to register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */


    private void attemptRegister() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        usernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = usernameView.getText().toString();
        String password = mPasswordView.getText().toString();
        UserType user = (UserType) userTypeSpinner.getSelectedItem();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            usernameView.setError(getString(R.string.error_field_required));
            focusView = usernameView;
            cancel = true;
        } else if (!isUsernameValid(username)) {
            usernameView.setError(getString(R.string.error_used_username));
            focusView = usernameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            showProgress(true);
            mAuthTask = new UserLoginTask(username, password, user);
            mAuthTask.execute((Void) null);
        }
    }

    /**
     * Checks if username has valid characters
     * @param username in the username field
     * @return whether the username has been used or not
     */

    private boolean isUsernameValid(String username) {
        return true;
    }

    /**
     * Should check if the password has illegal characters
     * @param password the entered password
     * @return
     */

    private boolean isPasswordValid(String password) {
        return true;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> usernames = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            usernames.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addUsernamesToAutocomplete(usernames);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addUsernamesToAutocomplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(RegisterActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        usernameView.setAdapter(adapter);
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUsername;
        private final String mPassword;
        private final UserType user;

        UserLoginTask(String username, String password, UserType userType) {
            mUsername = username;
            mPassword = password;
            user = userType;
        }

        //TODO: actually implement  authentication
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            DbHelper uDbHelper = new DbHelper(getApplicationContext());
            SQLiteDatabase db = uDbHelper.getReadableDatabase();


            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    UsersTable._ID,
                    UsersTable.COLUMN_NAME_USERNAME,
                    UsersTable.COLUMN_NAME_PASSWORD
            };

            // Filter results WHERE username = mUsername

            String selection = UsersTable.COLUMN_NAME_USERNAME + " = ?";
            String[] selectionArgs = { mUsername };

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

            List itemIds = new ArrayList<>();
            while(cursor.moveToNext()) {
                long itemId = cursor.getLong(
                        cursor.getColumnIndexOrThrow(UsersTable._ID));
                itemIds.add(itemId);
            }
            cursor.close();
            if (itemIds.size() == 0) { // No users currently have this username
                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();
                values.put(UsersTable.COLUMN_NAME_USERNAME, mUsername);
                values.put(UsersTable.COLUMN_NAME_PASSWORD, mPassword);
                values.put(UsersTable.COLUMN_NAME_NAME, "");
                values.put(UsersTable.COLUMN_NAME_TITLE, UserTitle.NA.toString());
                values.put(UsersTable.COLUMN_NAME_EMAIL, "");
                values.put(UsersTable.COLUMN_NAME_HOME, "");
                values.put(UsersTable.COLUMN_NAME_PHONE, "");
                values.put(UsersTable.COLUMN_NAME_USER_TYPE, user.toString());
                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(UsersTable.TABLE_NAME, null, values);
                User newUser;

                if (user.equals(UserType.Administrator)) {
                    newUser = new Administrator(mUsername, mPassword, "", "", "", UserTitle.NA, "");
                } else if (user.equals(UserType.Worker)) {
                    newUser = new Worker(mUsername, mPassword, "", "", "", UserTitle.NA, "", user);
                } else if (user.equals(UserType.Manager)) {
                    newUser = new Manager(mUsername, mPassword, "", "", "", UserTitle.NA, "");
                } else {
                    newUser = new Reporter(mUsername, mPassword, "", "", "", UserTitle.NA, "", user);
                }

                Model.getInstance().addUser(newUser);
                currentUser = mUsername;
                return true;
            }
            return false;
        }

        /**
         * Transitions to the HomeActivity, passing the current username
         * using putExtra
         * @param success
         */
        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                // Add user
                Intent nextActivity  = new Intent(RegisterActivity.this, HomeActivity.class);
                nextActivity.putExtra("CurrentUser", currentUser);
                startActivity(nextActivity);
            } else {
                mPasswordView.setError(getString(R.string.error_used_username));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

