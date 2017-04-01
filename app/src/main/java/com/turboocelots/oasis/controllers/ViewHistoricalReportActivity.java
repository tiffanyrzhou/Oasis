package com.turboocelots.oasis.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.turboocelots.oasis.R;
import com.turboocelots.oasis.models.Model;
import com.turboocelots.oasis.models.PPMType;
import com.turboocelots.oasis.models.Report;
import com.turboocelots.oasis.models.User;
import com.turboocelots.oasis.models.WaterQualityReport;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ViewHistoricalReportActivity extends AppCompatActivity {
    private HashMap<Integer,Double> datapoints = new HashMap<>();
    //private Integer current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_historical_report);
        final PPMType currentType = (PPMType) getIntent().getSerializableExtra("CurrentType");
        final  Integer currentYear = (Integer) getIntent().getSerializableExtra("CurrentYear");
        final String username = (String) getIntent().getSerializableExtra("CurrentUser");
        final User currentUser =  Model.getInstance().getUser(username);;


        final Button backButton = (Button) findViewById(R.id.backToHome_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextActivity  = new Intent(ViewHistoricalReportActivity.this, HomeActivity.class);
                nextActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                nextActivity.putExtra("CurrentUser", currentUser.getUsername());
                startActivity(nextActivity);
            }
        });
        GraphView graph = (GraphView) findViewById(R.id.graph);
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(new DataPoint[] {
        });
        series.setShape(PointsGraphSeries.Shape.POINT);
        for (WaterQualityReport r : Model.getInstance().get_reports_Selected()){
            if (currentType.equals(PPMType.VIRUS)) {
                if (datapoints.containsKey(r.getDate().get(Calendar.MONTH) + 1)){
                    datapoints.put(r.getDate().get(Calendar.MONTH) + 1 , (datapoints.get(r.getDate().get(Calendar.MONTH)+1) +
                            Double.parseDouble(r.getVirusPPM()))/2);
                } else {
                    datapoints.put(r.getDate().get(Calendar.MONTH) +1, Double.parseDouble(r.getVirusPPM()));
                }
            } else {
                if (datapoints.containsKey(r.getDate().get(Calendar.MONTH)+1)){
                    datapoints.put(r.getDate().get(Calendar.MONTH) +1, (datapoints.get(r.getDate().get(Calendar.MONTH) +1) +
                            Double.parseDouble(r.getContaminantsPPM()))/2);
                } else {
                    datapoints.put(r.getDate().get(Calendar.MONTH)+1, Double.parseDouble(r.getContaminantsPPM()));
                }

            }
        }
        for (Integer m : datapoints.keySet()) {
            series.appendData(new DataPoint(m, datapoints.get(m)),true, 20);
        }
        graph.addSeries(series);
        graph.getViewport().setScrollableY(true);
        graph.getViewport().setScalableY(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(13);
        graph.getViewport().setXAxisBoundsManual(true);
        GridLabelRenderer glr = graph.getGridLabelRenderer();
        glr.setPadding(32);
        glr.setHorizontalAxisTitle("Month " + currentYear);
        glr.setVerticalAxisTitle("PPM Level: " + currentType.toString());


    }
}
