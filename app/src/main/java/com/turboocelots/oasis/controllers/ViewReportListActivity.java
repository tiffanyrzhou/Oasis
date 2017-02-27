package com.turboocelots.oasis.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ListView;
import com.turboocelots.oasis.R;

public class ViewReportListActivity extends AppCompatActivity {
    private Button viewButton;
    private Button cancelButton;
    private ListView reportList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report_list);
        viewButton = (Button) findViewById(R.id.ViewButton);
        cancelButton = (Button) findViewById(R.id.CancelButton);
        reportList = (ListView) findViewById(R.id.ReportList);

        //initialize buttons
    }
}
