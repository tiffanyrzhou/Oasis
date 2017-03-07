package com.turboocelots.oasis.controllers;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Calendar;

import com.google.android.gms.location.LocationListener;
import com.turboocelots.oasis.R;
import com.turboocelots.oasis.models.ConditionOfWater;
import com.turboocelots.oasis.models.Model;
import com.turboocelots.oasis.models.Report;
import com.turboocelots.oasis.models.TypeOfWater;
import com.turboocelots.oasis.models.User;

public class SubmitWaterSourceReportActivity extends AppCompatActivity {
    private TextView datetime;
    private TextView reporterName;
    private TextView reportNumber;
    private EditText reportLat;
    private EditText reportLong;
    private Spinner waterTypeSpinner;
    private Spinner waterConditionSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);
        final String username = (String) getIntent().getSerializableExtra("CurrentUser");
        final User currentUser = Model.getInstance().getUser(username);
        //find attributes by ID
        datetime = (TextView) findViewById(R.id.date_time);
        reporterName = (TextView) findViewById(R.id.reporter_number);
        reportNumber = (TextView) findViewById(R.id.report_name);
        reportLat = (EditText) findViewById(R.id.lat_address);
        reportLong = (EditText) findViewById(R.id.long_address);
        waterTypeSpinner = (Spinner) findViewById(R.id.water_type_spinner);
        waterConditionSpinner = (Spinner) findViewById(R.id.water_condition_spinner);
        Button submitReport = (Button) findViewById(R.id.submitReport_reportActivity_button);
        Button cancel = (Button) findViewById(R.id.cancelReport_button);

        //initialize buttons
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity  = new Intent(SubmitWaterSourceReportActivity.this, HomeActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                startActivity(nextActivity);
            }
        });

        submitReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addReport();
                Intent nextActivity  = new Intent(SubmitWaterSourceReportActivity.this, HomeActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                startActivity(nextActivity);
            }

        });

        // initialize text view
        datetime.setText( "Date/Time:" + Calendar.getInstance().DATE + "" + Calendar.getInstance().getTime());
        reporterName.setText("Reporter Username:" + currentUser.getUsername());
        reportNumber.setText("Report Number:" + Model.getInstance().getReports().size()+"");

        // initialize spinner values
        ArrayAdapter<TypeOfWater> waterTypeArrayAdapter = new ArrayAdapter<TypeOfWater>
                (this,android.R.layout.simple_spinner_item, TypeOfWater.values());
        waterTypeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterTypeSpinner.setAdapter(waterTypeArrayAdapter);

        ArrayAdapter<ConditionOfWater> waterConditionArrayAdapter = new ArrayAdapter<ConditionOfWater>
                (this,android.R.layout.simple_spinner_item, ConditionOfWater.values());
        waterConditionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        waterConditionSpinner.setAdapter(waterConditionArrayAdapter);
    }

    /**
     * Add reports to the Model
     * @return void
     */
    private void addReport(){
        Report r = new Report((String)this.reportNumber.getText(), (String)this.datetime.getText(),
                (String) this.reporterName.getText(),
                Double.parseDouble(this.reportLat.getText().toString()),
                Double.parseDouble(this.reportLong.getText().toString()),
                (ConditionOfWater) this.waterConditionSpinner.getSelectedItem(),
                (TypeOfWater) this.waterTypeSpinner.getSelectedItem());
                 Model.getInstance().addReport(r);
    }


}
