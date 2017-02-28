package com.turboocelots.oasis.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.turboocelots.oasis.R;
import com.turboocelots.oasis.models.Model;
import com.turboocelots.oasis.models.Report;
import com.turboocelots.oasis.models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ViewReportListActivity extends AppCompatActivity {
    private Button viewButton;
    private Button cancelButton;
    private ListView reportList;
    public List<String> reportDisplay;
    public List<Report> reports;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report_list);
        viewButton = (Button) findViewById(R.id.ViewButton);
        cancelButton = (Button) findViewById(R.id.CancelButton);
        reportList = (ListView) findViewById(R.id.ReportList);
        final String username = (String) getIntent().getSerializableExtra("CurrentUser");
        final User currentUser = Model.getInstance().getUser(username);



        //Create listAdapter for ListView
        ArrayAdapter<String> reportListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                createList());
        reportList.setAdapter(reportListAdapter);

        //Initializes Buttons
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity  = new Intent(ViewReportListActivity.this, HomeActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                startActivity(nextActivity);
            }
        });
    }

    private List<String> createList() {
        reports = Model.getInstance().getReports();
        String display;
        reportDisplay = new ArrayList<>(reports.size());
        for (Report rep : reports) {
            display = "Report Number: " +  rep.getReportNumber() + "     " + "Creator: " + rep.getReporterName();
            reportDisplay.add(display);
        }
        return reportDisplay;
    }
}
