package com.turboocelots.oasis.controllers;

import android.content.Intent;
import android.content.res.ColorStateList;
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
import com.turboocelots.oasis.models.PPMType;
import com.turboocelots.oasis.models.TypeOfWater;


public class GenerateHistoricalReportActivity extends AppCompatActivity {
    private Spinner ppmSpinner;
    private EditText year;
    private EditText longtitude;
    private EditText latitude;
    private  TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_historical_report);
        year = (EditText) findViewById(R.id.year_graph);
        longtitude = (EditText) findViewById(R.id.long_graph);
        latitude = (EditText) findViewById(R.id.lat_graph);
        status = (TextView) findViewById(R.id.status_graph);
        ppmSpinner = (Spinner) findViewById(R.id.PPM_graph);


        final ArrayAdapter<PPMType> ppmTypeArrayAdapter = new ArrayAdapter<PPMType>
                (this,android.R.layout.simple_spinner_item, PPMType.values());
        ppmTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ppmSpinner.setAdapter(ppmTypeArrayAdapter);


        final Button generateButton = (Button) findViewById(R.id.generate_Report);
        generateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Model.getInstance().generate_reports_Selected(Integer.parseInt(year.getText().toString()),
                        Double.parseDouble(longtitude.getText().toString()),
                        Double.parseDouble(latitude.getText().toString()));
                if(Model.getInstance().get_reports_Selected().size() != 0) {
                    Intent nextActivity  = new Intent(GenerateHistoricalReportActivity.this, ViewHistoricalReportActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nextActivity.putExtra("CurrentType", (PPMType)ppmSpinner.getSelectedItem());
                    startActivity(nextActivity);
                } else {
                    status.setText("No Avaliable Data for current selections");
                }

            }
        });


    }
}
