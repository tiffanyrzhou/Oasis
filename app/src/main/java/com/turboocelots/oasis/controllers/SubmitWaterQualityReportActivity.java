package com.turboocelots.oasis.controllers;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.turboocelots.oasis.R;
import com.turboocelots.oasis.databases.DbHelper;
import com.turboocelots.oasis.databases.QualityReportsTable;
import com.turboocelots.oasis.models.Model;
import com.turboocelots.oasis.models.OverallCondition;
import com.turboocelots.oasis.models.User;
import com.turboocelots.oasis.models.WaterQualityReport;

import java.sql.Timestamp;
import java.util.Calendar;

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
        reporterName.setText(getString(R.string.submit_report_reporter_name, currentUser.getUsername()));
        reportNumber.setText(getString(R.string.submit_report_report_number, Model.getInstance().getReports().size()));

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
                boolean success = addReport();
                if (success) {
                    Intent nextActivity  = new Intent(SubmitWaterQualityReportActivity.this, HomeActivity.class);
                    nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                    startActivity(nextActivity);
                }
            }

        });

    }

    /**
     * Adds a WaterQualityReport to the Model
     * returns true if successful, false if there was an error
     */
    private boolean addReport(){
        double parsedVirusPPM = 0.0;
        double parsedContaminantsPPM = 0.0;
        double parsedLat = 0.0;
        double parsedLng = 0.0;

        try {
            parsedLat = Double.parseDouble(this.reportLat.getText().toString());
        } catch (NumberFormatException ne)  {
            reportLat.setError(getString(R.string.error_invalid_number));
            reportLat.requestFocus();
            return false;
        } catch (NullPointerException npe) {
            reportLat.setError(getString(R.string.error_field_required));
            reportLat.requestFocus();
            return false;
        }
        try {
            parsedLng = Double.parseDouble(this.reportLong.getText().toString());
        } catch (NumberFormatException nfe) {
            reportLong.setError(getString(R.string.error_invalid_number));
            reportLong.requestFocus();
            return false;
        } catch (NullPointerException npe) {
            reportLong.setError(getString(R.string.error_field_required));
            reportLong.requestFocus();
            return false;
        }
        try {
            parsedVirusPPM = Double.parseDouble(virusPPM.getText().toString());
        } catch (NumberFormatException nfe) {
            virusPPM.setError(getString(R.string.error_invalid_number));
            virusPPM.requestFocus();
            return false;
        } catch (NullPointerException npe) {
            virusPPM.setError(getString(R.string.error_field_required));
            virusPPM.requestFocus();
            return false;
        }
        try {
            parsedContaminantsPPM = Double.parseDouble(contaminantsPPM.getText().toString());
        } catch (NumberFormatException nfe) {
            contaminantsPPM.setError(getString(R.string.error_invalid_number));
            contaminantsPPM.requestFocus();
            return false;
        } catch (NullPointerException npe) {
            contaminantsPPM.setError(getString(R.string.error_field_required));
            contaminantsPPM.requestFocus();
            return false;
        }
        WaterQualityReport r =  new WaterQualityReport ((String)this.reportNumber.getText(), new Timestamp(this.currentDate.getTimeInMillis()),
                (String) this.reporterName.getText(),
                parsedLat,
                parsedLng,
                (OverallCondition) overallConditionSpinner.getSelectedItem(),
                parsedVirusPPM, parsedContaminantsPPM);
        DbHelper uDbHelper = new DbHelper(getApplicationContext());
        SQLiteDatabase db = uDbHelper.getReadableDatabase();
        Model.getInstance().addReport(r);
        QualityReportsTable.addQualityReport(db, r);
        return true;
    }
}
