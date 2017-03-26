package com.turboocelots.oasis.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.turboocelots.oasis.R;
import com.turboocelots.oasis.models.Model;
import com.turboocelots.oasis.models.User;
import com.turboocelots.oasis.models.UserType;

public class HomeActivity extends AppCompatActivity {

    /**
     * Creates the HomeActivity
     * The username of the current user is passesd through "CurrentUser" in getSerializableExtra
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final String username = (String) getIntent().getSerializableExtra("CurrentUser");
        final User currentUser = Model.getInstance().getUser(username);

        final Button logoutButton = (Button) findViewById(R.id.logout_id);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity  = new Intent(HomeActivity.this, WelcomeActivity.class);
//                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                startActivity(nextActivity);
            }
        });

        final Button editProfileButton = (Button) findViewById(R.id.edit_profile_button);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity  = new Intent(HomeActivity.this, EditProfileActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                startActivity(nextActivity);
            }
        });

        final Button submitReportButton = (Button) findViewById(R.id.submit_report_button);
        if (currentUser.getUserType() == UserType.Administrator || currentUser.getIsBanned() == true) {
            submitReportButton.setVisibility(View.GONE);
        } else {
            submitReportButton.setVisibility(View.VISIBLE);
            submitReportButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent nextActivity  = new Intent(HomeActivity.this, SubmitWaterSourceReportActivity.class);
                    nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                    startActivity(nextActivity);
                }
            });
        }

        final Button viewWaterAvailabilityButton = (Button) findViewById(R.id.waterAvaliability_button);
        if (currentUser.getUserType() == UserType.Administrator) {
            viewWaterAvailabilityButton.setVisibility(View.GONE);
        } else {
            viewWaterAvailabilityButton.setVisibility(View.VISIBLE);
            viewWaterAvailabilityButton .setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent nextActivity  = new Intent(HomeActivity.this, MapsActivity.class);
                    nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                    startActivity(nextActivity);
                }
            });
        }

        final Button viewReportList = (Button) findViewById(R.id.view_report_list_button);
        if (currentUser.getUserType() == UserType.Administrator) {
            viewReportList.setVisibility(View.GONE);
        } else {
            viewReportList.setVisibility(View.VISIBLE);
            viewReportList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity  = new Intent(HomeActivity.this, ViewReportListActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                startActivity(nextActivity);
            }
        });
    }
        final Button manageAccountsButton = (Button) findViewById(R.id.manage_accounts);
        if (currentUser.getUserType() != UserType.Administrator) {
            manageAccountsButton.setVisibility(View.GONE);
        } else {
            manageAccountsButton.setVisibility(View.VISIBLE);
            manageAccountsButton .setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent nextActivity  = new Intent(HomeActivity.this, ManageAccountsActivity.class);
                    nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                    startActivity(nextActivity);
                }
            });
        }

        final TextView userTypeText = (TextView) findViewById(R.id.userText_id);
        userTypeText.setText(currentUser.getUserType().toString());
    }
}
