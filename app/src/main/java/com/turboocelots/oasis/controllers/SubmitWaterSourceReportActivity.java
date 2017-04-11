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

import java.sql.Timestamp;
import java.util.Calendar;

import com.turboocelots.oasis.R;
import com.turboocelots.oasis.databases.DbHelper;
import com.turboocelots.oasis.databases.SourceReportsTable;
import com.turboocelots.oasis.models.ConditionOfWater;
import com.turboocelots.oasis.models.Model;
import com.turboocelots.oasis.models.WaterSourceReport;
import com.turboocelots.oasis.models.TypeOfWater;
import com.turboocelots.oasis.models.User;

/**
 * Activity that controls Submitting the WaterSourceReports
 */
public class SubmitWaterSourceReportActivity extends AppCompatActivity {
    private TextView reporterName;
    private TextView reportNumber;
    private EditText reportLat;
    private EditText reportLong;
    private Spinner waterTypeSpinner;
    private Spinner waterConditionSpinner;


    private final static double LOWEST_LAT = -90;
    private final static double HIGHEST_LAT = 90;
    private final static double LOWEST_LNG = -180;
    private final static double HIGHEST_LNG = 180;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_report);
        final String username = (String) getIntent().getSerializableExtra("CurrentUser");
        final User currentUser = Model.getInstance().getUser(username);
        //find attributes by ID
        TextView datetime = (TextView) findViewById(R.id.date_time);
        reporterName = (TextView) findViewById(R.id.reporter_name);
        reportNumber = (TextView) findViewById(R.id.report_number);
        reportLat = (EditText) findViewById(R.id.lat_address);
        reportLong = (EditText) findViewById(R.id.long_address);
        waterTypeSpinner = (Spinner) findViewById(R.id.water_type_spinner);
        waterConditionSpinner = (Spinner) findViewById(R.id.water_condition_spinner);
        Button submitReport = (Button) findViewById(R.id.submitReport_reportActivity_button);
        Button cancel = (Button) findViewById(R.id.cancelReport_button);

        //initialize buttons
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submitReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = addReport();
                if (success && (currentUser != null)) {
                    Intent nextActivity  = new Intent(SubmitWaterSourceReportActivity.this,
                            HomeActivity.class);
                    nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                    startActivity(nextActivity);
                }
            }

        });

        // initialize text view
        datetime.setText(Calendar.getInstance().getTime().toString());
        reporterName.setText(getString(R.string.submit_report_reporter_name,
                currentUser.getUsername()));
        reportNumber.setText(getString(R.string.submit_report_report_number,
                Model.getInstance().getReports().size()));

        // initialize spinner values
        ArrayAdapter<TypeOfWater> waterTypeArrayAdapter = new ArrayAdapter<>
                (this,android.R.layout.simple_spinner_item, TypeOfWater.values());
        waterTypeArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        waterTypeSpinner.setAdapter(waterTypeArrayAdapter);

        ArrayAdapter<ConditionOfWater> waterConditionArrayAdapter = new ArrayAdapter<>
                (this,android.R.layout.simple_spinner_item, ConditionOfWater.values());
        waterConditionArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        waterConditionSpinner.setAdapter(waterConditionArrayAdapter);
    }

    /**
     * Adds WaterSourceReports to the Model
     * @return true if successful, false if failed
     */
    private boolean addReport() {
        double parsedLat;
        double parsedLng;
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

        if ((parsedLat < LOWEST_LAT) || (parsedLat > HIGHEST_LAT)) {
            reportLat.setError(getString(R.string.submit_report_lat_out_of_range));
            reportLat.requestFocus();
            return false;
        }

        if ((parsedLng < LOWEST_LNG) || (parsedLat > HIGHEST_LNG)) {
            reportLong.setError(getString(R.string.submit_report_lng_out_of_range));
            reportLong.requestFocus();
            return false;
        }

        WaterSourceReport r = new WaterSourceReport((String)this.reportNumber.getText(),
                new Timestamp(Calendar.getInstance().getTimeInMillis()),
                (String) this.reporterName.getText(),
                parsedLat,
                parsedLng,
                (ConditionOfWater) this.waterConditionSpinner.getSelectedItem(),
                (TypeOfWater) this.waterTypeSpinner.getSelectedItem());

        DbHelper uDbHelper = new DbHelper(getApplicationContext());
        SQLiteDatabase db = uDbHelper.getReadableDatabase();
        Model.getInstance().addReport(r);
        SourceReportsTable.addSourceReport(db, r);
        return true;
    }

}
