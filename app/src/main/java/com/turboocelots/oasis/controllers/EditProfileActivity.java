package com.turboocelots.oasis.controllers;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;


import com.turboocelots.oasis.R;
import com.turboocelots.oasis.databases.DbHelper;
import com.turboocelots.oasis.databases.UsersTable;
import com.turboocelots.oasis.models.*;


public class EditProfileActivity extends AppCompatActivity {

    /* ************************
     Widgets we will need for binding and getting information
  */
    private EditText nameField;
    private TextView usernameField;
    private EditText passwordField;
    private EditText emailField;
    private EditText homeAddressField;
    private EditText phoneAddressField;
    private Spinner userTitleSpinner;

    private UpdateUserTask updateTask = null;

    /**
     * Instantiates the Activity. The activity is implicitly given a reference to the user
     * by username via the getSerializableExtra method
     * This is then used to modify the user via the Model class
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        final String username = (String) getIntent().getSerializableExtra("CurrentUser");
        final User currentUser = Model.getInstance().getUser(username);

        final Button cancelButton = (Button) findViewById(R.id.EditProfileCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onCancelPressed(v);
            }
        });

        final Button saveButton = (Button) findViewById(R.id.EditProfileSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSavePressed(v);
            }
        });
        /**
         * Grab the dialog widgets so we can get info for later
         */
        nameField = (EditText) findViewById(R.id.editName);
        usernameField = (TextView) findViewById(R.id.editUsername);
        passwordField = (EditText) findViewById(R.id.editPassword);
        emailField = (EditText) findViewById(R.id.editEmail);
        homeAddressField = (EditText) findViewById(R.id.editHomeAddress);
        phoneAddressField = (EditText) findViewById(R.id.editPhoneNumber);

        userTitleSpinner = (Spinner)findViewById(R.id.TitleSpinner);

        ArrayAdapter<UserTitle> userTitleArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, UserTitle.values());
        userTitleArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTitleSpinner.setAdapter(userTitleArrayAdapter);

        usernameField.setText(currentUser.getUsername());
        nameField.setText(currentUser.getName());
        passwordField.setText(currentUser.getPassword());
        emailField.setText(currentUser.getEmail());
        homeAddressField.setText(currentUser.getHome());
        phoneAddressField.setText(currentUser.getPhone());
        int pos = userTitleArrayAdapter.getPosition(currentUser.getTitle());
        userTitleSpinner.setSelection(pos);
    }

    /**
     * Button handler for cancel
     *
     * @param view the button pressed
     */
    protected void onCancelPressed(View view) {
        finish();
    }


    /**
     * Saves all of the updated data to the corresponding User object in the Model class
     * Then, exits the activity
     * TODO: database arbitration to update
     * @param view
     */
    protected void onSavePressed(View view) {
        final String username = (String) getIntent().getSerializableExtra("CurrentUser");
        final User currentUser = Model.getInstance().getUser(username);
        currentUser.setHome(homeAddressField.getText().toString());
        currentUser.setEmail(emailField.getText().toString());
        currentUser.setName(nameField.getText().toString());
        currentUser.setPhone(phoneAddressField.getText().toString());
        currentUser.setPassword(passwordField.getText().toString());
        currentUser.setTitle((UserTitle) userTitleSpinner.getSelectedItem());
        updateTask = new UpdateUserTask(currentUser);
        updateTask.execute((Void) null);
    }

    public class UpdateUserTask extends AsyncTask<Void, Void, Boolean> {

        private final User user;

        UpdateUserTask(User _user) {
            user = _user;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            DbHelper uDbHelper = new DbHelper(getApplicationContext());
            SQLiteDatabase db = uDbHelper.getReadableDatabase();


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
            // Insert the new row, returning the primary key value of the new row
            long newRowId = db.update(UsersTable.TABLE_NAME, values, whereClause, whereArgs);
            return true;
        }

        /**
         * Transitions to the HomeActivity, passing the current username
         * using putExtra
         *
         * @param success
         */
        @Override
        protected void onPostExecute(final Boolean success) {
            updateTask = null;
            if (success) {
                finish();
            } else {

            }
        }

        @Override
        protected void onCancelled() {
            updateTask = null;
        }
    }
}
