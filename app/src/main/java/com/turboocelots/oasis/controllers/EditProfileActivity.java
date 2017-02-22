package com.turboocelots.oasis.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.turboocelots.oasis.R;
import com.turboocelots.oasis.models.*;


public class EditProfileActivity extends AppCompatActivity {

    /* ************************
     Widgets we will need for binding and getting information
  */
    private TextView idField;
    private EditText nameField;
    private EditText usernameField;
    private EditText passwordField;
    private EditText emailField;
    private EditText homeAddressField;
    private EditText phoneAddressField;

    /* ***********************
   Data for Reporter being edited.
 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        final String username = (String) getIntent().getSerializableExtra("CurrentUser");
        final Reporter currentUser = Model.getInstance().getReporter(username);

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
        passwordField = (EditText) findViewById(R.id.editPassword);
        emailField = (EditText) findViewById(R.id.editEmailAddress);
        homeAddressField = (EditText) findViewById(R.id.editHomeAddress);
        phoneAddressField = (EditText) findViewById(R.id.editPhoneNumber);


        nameField.setText(currentUser.getName());
        passwordField.setText(currentUser.getPassword());
        emailField.setText(currentUser.getEmail());
        homeAddressField.setText(currentUser.getHome());
        phoneAddressField.setText(currentUser.getPhone());


    }

    /**
     * Button handler for cancel
     *
     * @param view the button pressed
     */
    protected void onCancelPressed(View view) {
        finish();
    }

    protected void onSavePressed(View view) {
        final String username = (String) getIntent().getSerializableExtra("CurrentUser");
        final Reporter currentUser = Model.getInstance().getReporter(username);
        currentUser.setHome(homeAddressField.getText().toString());
        currentUser.setEmail(emailField.getText().toString());
        currentUser.setName(nameField.getText().toString());
        currentUser.setPhone(phoneAddressField.getText().toString());
        currentUser.setPassword(passwordField.getText().toString());
        finish();
    }
}
