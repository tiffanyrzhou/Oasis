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
import com.turboocelots.oasis.models.ConditionOfWater;
import com.turboocelots.oasis.models.Model;
import com.turboocelots.oasis.models.OverallCondition;
import com.turboocelots.oasis.models.TypeOfWater;
import com.turboocelots.oasis.models.User;
import com.turboocelots.oasis.models.WaterQualityReport;

import java.util.Calendar;
import java.util.Date;

public class SubmitWaterQualityReportActivity extends AppCompatActivity {

    private TextView datetime;
    private TextView reporterName;
    private TextView reportNumber;
    private EditText reportLat;
    private EditText reportLong;
    private Spinner overallConditionSpinner;
    private EditText virusPPM;
    private  EditText contaminantsPPM;
    private Calendar currentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_water_quality_report);
        final String username = (String) getIntent().getSerializableExtra("CurrentUser");
        final User currentUser = Model.getInstance().getUser(username);

        datetime = (TextView) findViewById(R.id.dateTime_WQ);
        reporterName = (TextView) findViewById(R.id.reporterName_WQ);
        reportNumber = (TextView) findViewById(R.id.reportNumber_WQ);
        reportLat = (EditText) findViewById(R.id.lat_WQ);
        reportLong = (EditText) findViewById(R.id.long_WQ);
        virusPPM = (EditText) findViewById(R.id.virus_WQ);
        contaminantsPPM = (EditText) findViewById(R.id.contaminant_WQ);

        Button submitReport = (Button) findViewById(R.id.submit_WQ);
        Button cancel = (Button) findViewById(R.id.cancel_WQ);

        overallConditionSpinner = (Spinner) findViewById(R.id.overallCondition_WQ);
        ArrayAdapter<OverallCondition> conditionArrayAdapter = new ArrayAdapter<OverallCondition>
                (this,android.R.layout.simple_spinner_item, OverallCondition.values());
        conditionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        overallConditionSpinner.setAdapter(conditionArrayAdapter);
        currentDate = Calendar.getInstance();
        datetime.setText(currentDate.DATE + "" + currentDate.getTime());
        reporterName.setText("Reporter Username:" + currentUser.getUsername());
        reportNumber.setText("Report Number:" + Model.getInstance().getReports().size()+"");

        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity  = new Intent(SubmitWaterQualityReportActivity.this, HomeActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                startActivity(nextActivity);
            }
        });

        submitReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addReport();
                Intent nextActivity  = new Intent(SubmitWaterQualityReportActivity.this, HomeActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                startActivity(nextActivity);
            }

        });

    }

    /**
     * add report to the model
     *
     */
    private void addReport(){
        WaterQualityReport r =  new WaterQualityReport ((String)this.reportNumber.getText(), (String)this.datetime.getText(),
                (String) this.reporterName.getText(),
                Double.parseDouble(this.reportLat.getText().toString()),
                Double.parseDouble(this.reportLong.getText().toString()), currentDate,
                (OverallCondition) overallConditionSpinner.getSelectedItem(),
                virusPPM.getText().toString(), contaminantsPPM.getText().toString());
        Model.getInstance().addReport(r);
    }
}
