package com.turboocelots.oasis.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.turboocelots.oasis.R;
import com.turboocelots.oasis.models.Model;

 /**
 * Activity that allows you to view all Reports
 */
public class ViewReportListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report_list);
        final Button cancelButton = (Button) findViewById(R.id.CancelButton);
        final ListView reportList = (ListView) findViewById(R.id.ReportList);

        //Create listAdapter for ListView
        ListAdapter reportListAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                Model.getInstance().getReports());
        reportList.setAdapter(reportListAdapter);

        //Initializes Buttons
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
