package com.turboocelots.oasis.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;


import com.turboocelots.oasis.R;
import com.turboocelots.oasis.models.*;


public class EditProfileActivity extends AppCompatActivity {

    /* ************************
     Widgets we will need for binding and getting information
  */
    private EditText nameField;
    private EditText usernameField;
    private EditText passwordField;
    private EditText emailField;
    private EditText homeAddressField;
    private EditText phoneAddressField;
    private Spinner userTitleSpinner;
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
        usernameField = (EditText) findViewById(R.id.editUsername);
        passwordField = (EditText) findViewById(R.id.editPassword);
        emailField = (EditText) findViewById(R.id.editEmail);
        homeAddressField = (EditText) findViewById(R.id.editHomeAddress);
        phoneAddressField = (EditText) findViewById(R.id.editPhoneNumber);
        userTitleSpinner = (Spinner) findViewById(R.id.TitleSpinner);

//        idField = (TextView) findViewById(R.id.student_id_field);
        ArrayAdapter<UserTitle> userTitleArrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, UserTitle.values());
        userTitleArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTitleSpinner.setAdapter(userTitleArrayAdapter);
//
//
        usernameField.setText(currentUser.getUsername());
        nameField.setText(currentUser.getName());
        passwordField.setText(currentUser.getPassword());
        emailField.setText(currentUser.getEmail());
        homeAddressField.setText(currentUser.getHome());
        phoneAddressField.setText(currentUser.getPhone());
        int pos = userTitleArrayAdapter.getPosition(currentUser.getTitle());
        userTitleSpinner.setSelection(pos);

//        idField.setText("" + _student.getId());

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
        currentUser.setTitle((UserTitle) userTitleSpinner.getSelectedItem());
        finish();
    }
}
