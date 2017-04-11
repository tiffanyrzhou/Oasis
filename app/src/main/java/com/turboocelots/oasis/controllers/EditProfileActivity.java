package com.turboocelots.oasis.controllers;

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
import com.turboocelots.oasis.models.Model;
import com.turboocelots.oasis.models.User;
import com.turboocelots.oasis.models.UserTitle;

/**
 * Activity for EditProfile
 * Allows the User to modify their profile information
 */
public class EditProfileActivity extends AppCompatActivity {

    /* ************************
     Widgets we will need for binding and getting information
  */
    private EditText nameField;
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
     * @param savedInstanceState Bundle object that allows this activity to restore to
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        final String username = (String) getIntent().getSerializableExtra("CurrentUser");
        final User currentUser = Model.getInstance().getUser(username);
        if (currentUser == null) {
            finish();
        }

        final Button cancelButton = (Button) findViewById(R.id.EditProfileCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelPressed(v);
            }
        });

        final Button saveButton = (Button) findViewById(R.id.EditProfileSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSavePressed(v);
            }
        });

        // Grab the dialog widgets so we can get info for later
        nameField = (EditText) findViewById(R.id.editName);
        TextView usernameField = (TextView) findViewById(R.id.editUsername);
        passwordField = (EditText) findViewById(R.id.editPassword);
        emailField = (EditText) findViewById(R.id.editEmail);
        homeAddressField = (EditText) findViewById(R.id.editHomeAddress);
        phoneAddressField = (EditText) findViewById(R.id.editPhoneNumber);

        userTitleSpinner = (Spinner)findViewById(R.id.TitleSpinner);

        ArrayAdapter<UserTitle> userTitleArrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, UserTitle.values());
        userTitleArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        userTitleSpinner.setAdapter(userTitleArrayAdapter);

        if(currentUser != null) {
            usernameField.setText(currentUser.getUsername());
            nameField.setText(currentUser.getName());
            passwordField.setText(currentUser.getPassword());
            emailField.setText(currentUser.getEmail());
            homeAddressField.setText(currentUser.getHome());
            phoneAddressField.setText(currentUser.getPhone());
            int pos = userTitleArrayAdapter.getPosition(currentUser.getTitle());
            userTitleSpinner.setSelection(pos);
        }

    }

    private void onCancelPressed(View view) {
        finish();
    }


    /**
     * Saves all of the updated data to the corresponding User object in the Model class
     * Then, exits the activity
     * @param view the EditProfile View
     */
    private void onSavePressed(View view) {
        final String username = (String) getIntent().getSerializableExtra("CurrentUser");
        final User currentUser = Model.getInstance().getUser(username);
        if(currentUser!= null) {
            currentUser.setHome(homeAddressField.getText().toString());
            currentUser.setEmail(emailField.getText().toString());
            currentUser.setName(nameField.getText().toString());
            currentUser.setPhone(phoneAddressField.getText().toString());
            currentUser.setPassword(passwordField.getText().toString());
            currentUser.setTitle((UserTitle) userTitleSpinner.getSelectedItem());
            updateTask = new UpdateUserTask(currentUser);
            updateTask.execute((Void) null);
        }
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
            UsersTable.updateUser(db, user);
            return true;
        }

        /**
         * Transitions to the HomeActivity, passing the current username
         * using putExtra
         *
         * @param success whether or not the task was successful
         */
        @Override
        protected void onPostExecute(final Boolean success) {
            updateTask = null;
            if (success) {
                finish();
            }
        }

        @Override
        protected void onCancelled() {
            updateTask = null;
        }
    }
}
