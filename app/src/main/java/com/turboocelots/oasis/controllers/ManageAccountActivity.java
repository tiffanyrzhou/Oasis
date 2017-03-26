package com.turboocelots.oasis.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.turboocelots.oasis.R;
import com.turboocelots.oasis.models.Model;
import com.turboocelots.oasis.models.User;

public class ManageAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);
        final Button banAccountButton = (Button) findViewById(R.id.ban_account_button);
        final Button deleteAccountButton = (Button) findViewById(R.id.delete_account_button);
        final Button cancelButton = (Button) findViewById(R.id.cancel_button_ManageAccount);
        final String selectedUsername = (String) getIntent().getSerializableExtra("SelectedAccount");
        final User selectedUser = Model.getInstance().getUser(selectedUsername);

        //Initializes Buttons
        banAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedUser.setIsBanned((true));
                Intent nextActivity  = new Intent(ManageAccountActivity.this, ViewAccountsActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(nextActivity);
            }
        });

        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                selectedUser.setIsDeleted((true));
                Intent nextActivity  = new Intent(ManageAccountActivity.this, ViewAccountsActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(nextActivity);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity  = new Intent(ManageAccountActivity.this, ViewAccountsActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(nextActivity);
            }
        });
    }
}
