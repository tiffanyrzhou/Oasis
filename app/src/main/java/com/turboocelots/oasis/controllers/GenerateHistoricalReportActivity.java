package com.turboocelots.oasis.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.turboocelots.oasis.R;
import com.turboocelots.oasis.models.Model;
import com.turboocelots.oasis.models.PPMType;
import com.turboocelots.oasis.models.User;
import com.turboocelots.oasis.models.WaterQualityReport;

import java.util.List;

/**
 * Activity for form that allows the user to filter WaterQualityReports
 * and later graph them
 */
public class GenerateHistoricalReportActivity extends AppCompatActivity {
    private Spinner ppmSpinner;
    private EditText year;
    private EditText longitude;
    private EditText latitude;
    private  TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_historical_report);
        year = (EditText) findViewById(R.id.year_graph);
        longitude = (EditText) findViewById(R.id.long_graph);
        latitude = (EditText) findViewById(R.id.lat_graph);
        status = (TextView) findViewById(R.id.status_graph);
        ppmSpinner = (Spinner) findViewById(R.id.PPM_graph);
        final String username = (String) getIntent().getSerializableExtra("CurrentUser");
        final User currentUser = Model.getInstance().getUser(username);


        final ArrayAdapter<PPMType> ppmTypeArrayAdapter = new ArrayAdapter<>
                (this,android.R.layout.simple_spinner_item, PPMType.values());
        ppmTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ppmSpinner.setAdapter(ppmTypeArrayAdapter);


        final Button generateButton = (Button) findViewById(R.id.generate_Report);
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<WaterQualityReport> selectedReports = Model.getInstance().
                        generateSelectedReports(Integer.parseInt(year.getText().toString()),
                        Double.parseDouble(longitude.getText().toString()),
                        Double.parseDouble(latitude.getText().toString()));

                if(!selectedReports.isEmpty()) {
                    Intent nextActivity  = new Intent(GenerateHistoricalReportActivity.this,
                            ViewHistoricalReportActivity.class);
                    nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    nextActivity.putExtra("CurrentType", (PPMType) ppmSpinner.getSelectedItem());
                    nextActivity.putExtra("CurrentYear", (Integer) Integer.parseInt(
                            year.getText().toString()));
                    nextActivity.putExtra("Lat", Double.parseDouble(latitude.getText().toString()));
                    nextActivity.putExtra("Lng", Double.parseDouble(
                            longitude.getText().toString()));
                    if(currentUser != null) {
                        nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                    }
                    startActivity(nextActivity);
                } else {
                    status.setError(getString(R.string.
                            generate_historical_report_activity_no_available_data));
                }
            }
        });

        final Button cancel = (Button) findViewById(R.id.cancel_graph);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextActivity  = new Intent(GenerateHistoricalReportActivity.this,
                        HomeActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if(currentUser!= null) {
                    nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                }
                startActivity(nextActivity);
            }
        });


    }
}
