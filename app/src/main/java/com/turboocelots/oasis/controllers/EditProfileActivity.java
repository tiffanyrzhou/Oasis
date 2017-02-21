package com.turboocelots.oasis.controllers;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
        final Reporter currentUser = (Reporter) getIntent().getSerializableExtra("CurrentUser");
        /**
         * Grab the dialog widgets so we can get info for later
         */
        nameField = (EditText) findViewById(R.id.editName);
        usernameField = (EditText) findViewById(R.id.editUsername);
        passwordField = (EditText) findViewById(R.id.editPassword);
        emailField = (EditText) findViewById(R.id.editEmail);
        homeAddressField = (EditText) findViewById(R.id.editHomeAddress);
        phoneAddressField = (EditText) findViewById(R.id.editPhoneNumber);

//        idField = (TextView) findViewById(R.id.student_id_field);
//
//
//        nameField.setText(_reporter.get);
        usernameField.setText(currentUser.getUsername());
        passwordField.setText(currentUser.getPassword());
        emailField.setText(currentUser.getEmail());
        homeAddressField.setText(currentUser.getHome());
        phoneAddressField.setText(currentUser.getPhone());
//        idField.setText("" + _student.getId());

    }
}
