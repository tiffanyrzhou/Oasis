package com.turboocelots.oasis.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
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

public class ViewHistoricalReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_historical_report);
        final PPMType currentType = (PPMType) getIntent().getSerializableExtra("CurrentType");
        GraphView graph = (GraphView) findViewById(R.id.graph);
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(new DataPoint[] {
        });
        series.setShape(PointsGraphSeries.Shape.POINT);
        for (WaterQualityReport r : Model.getInstance().get_reports_Selected()){
            if (currentType.equals(PPMType.VIRUS)) {
                series.appendData(new DataPoint(r.getDate().get(Calendar.MONTH),Double.parseDouble(r.getVirusPPM())),true,50);
            } else {
                series.appendData(new DataPoint(r.getDate().get(Calendar.MONTH),Double.parseDouble(r.getContaminantsPPM())),true,50);
            }
        }
        graph.addSeries(series);



    }
}
