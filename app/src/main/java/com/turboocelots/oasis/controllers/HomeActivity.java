package com.turboocelots.oasis.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.turboocelots.oasis.R;
import com.turboocelots.oasis.models.User;
import com.turboocelots.oasis.models.UserRepository;

/**
 * The Home screen after the User Logs in
 * Provides a portal for access to all functions of the app
 */
public class HomeActivity extends AppCompatActivity {


    private User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String username = (String) getIntent().getSerializableExtra("CurrentUser");
        currentUser = UserRepository.getUser(username);

        bindSubmitWaterSourceButton();
        bindLogoutButton();
        bindEditProfileButton();
        bindSubmitWaterQualityReportButton();
        bindViewHistoricalPurityReportButton();
        bindViewReportButton();
        bindViewWaterSourceReportButton();

        final TextView userTypeText = (TextView) findViewById(R.id.userText_id);
        userTypeText.setText(currentUser.getUserType().toString());
    }
    private void bindLogoutButton() {
        final Button logoutButton = (Button) findViewById(R.id.logout_id);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity  = new Intent(HomeActivity.this, WelcomeActivity.class);
                startActivity(nextActivity);
            }
        });
    }

    private void bindEditProfileButton() {
        final Button editProfileButton = (Button) findViewById(R.id.edit_profile_button);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity  = new Intent(HomeActivity.this, EditProfileActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                startActivity(nextActivity);
            }
        });
    }

    private void bindSubmitWaterQualityReportButton() {
        final Button submitWaterQualityReportButton = (Button) findViewById(R.id.submitWaterQuality);
        if (!currentUser.canSubmitQualityReport()) {
            submitWaterQualityReportButton.setVisibility(View.GONE);
        } else {
            submitWaterQualityReportButton.setVisibility(View.VISIBLE);
            submitWaterQualityReportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextActivity  = new Intent(HomeActivity.this,
                            SubmitWaterQualityReportActivity.class);
                    nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                    startActivity(nextActivity);
                }
            });
        }
    }

    private void bindSubmitWaterSourceButton() {
        final Button submitReportButton = (Button) findViewById(R.id.submit_report_button);
        if (!currentUser.canSubmitWaterSourceReport()) {
            submitReportButton.setVisibility(View.GONE);
        } else {
            submitReportButton.setVisibility(View.VISIBLE);
            submitReportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextActivity  = new Intent(HomeActivity.this,
                            SubmitWaterSourceReportActivity.class);
                    nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                    startActivity(nextActivity);
                }
            });
        }
    }

    private void bindViewHistoricalPurityReportButton() {
        final  Button viewHistoricalPurityReportButton =
                (Button) findViewById(R.id.view_historical_button);
        if (!currentUser.canSubmitQualityReport()){
            viewHistoricalPurityReportButton.setVisibility(View.GONE);
        } else {
            viewHistoricalPurityReportButton.setVisibility(View.VISIBLE);
            viewHistoricalPurityReportButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextActivity  = new Intent(HomeActivity.this,
                            GenerateHistoricalReportActivity.class);
                    nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                    startActivity(nextActivity);
                }
            });

        }
    }

    private void bindViewWaterSourceReportButton() {
        final Button viewWaterAvailabilityButton =
                (Button) findViewById(R.id.waterAvailability_button);
        if (!currentUser.canSubmitWaterSourceReport()) {
            viewWaterAvailabilityButton.setVisibility(View.GONE);
        } else {
            viewWaterAvailabilityButton.setVisibility(View.VISIBLE);
            viewWaterAvailabilityButton .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextActivity  = new Intent(HomeActivity.this, MapsActivity.class);
                    nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                    startActivity(nextActivity);
                }
            });
        }
    }

    private void bindViewReportButton() {
        final Button viewReportList = (Button) findViewById(R.id.view_report_list_button);
        if (!currentUser.canSubmitWaterSourceReport()) {
            viewReportList.setVisibility(View.GONE);
        } else {
            viewReportList.setVisibility(View.VISIBLE);
            viewReportList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent nextActivity  = new Intent(HomeActivity.this,
                            ViewReportListActivity.class);
                    nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                    startActivity(nextActivity);
                }
            });
        }
    }
}
