package com.turboocelots.oasis.controllers;

import android.content.Intent;
import android.location.Location;
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
import com.turboocelots.oasis.models.OverallCondition;


public class GenerateHistoricalReportActivity extends AppCompatActivity {
    private Spinner ppmSpinner;
    private EditText year;
    private EditText longtitude;
    private EditText latitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_historical_report);
        year = (EditText) findViewById(R.id.year_graph);
        longtitude = (EditText) findViewById(R.id.long_graph);
        latitude = (EditText) findViewById(R.id.lat_graph);


        final Button generateButton = (Button) findViewById(R.id.generate_Report);
        generateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Model.getInstance().generate_reports_Selected(Integer.parseInt(year.getText().toString()),
                        Double.parseDouble(longtitude.toString()),  Double.parseDouble(latitude.toString()));
                if(Model.getInstance().get_reports_Selected().size() != 0) {
                    Intent nextActivity  = new Intent(GenerateHistoricalReportActivity.this, ViewHistoricalReportActivity.class);
//                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                    startActivity(nextActivity);
                } else {

                }

            }
        });


    }
}
