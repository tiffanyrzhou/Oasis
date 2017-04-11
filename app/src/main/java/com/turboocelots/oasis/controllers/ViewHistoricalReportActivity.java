package com.turboocelots.oasis.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.PointsGraphSeries;
import com.turboocelots.oasis.R;
import com.turboocelots.oasis.models.Model;
import com.turboocelots.oasis.models.PPMType;
import com.turboocelots.oasis.models.WaterQualityReport;

import java.util.HashMap;
import java.util.List;

/**
 * Activity that allows you to graph Historical Water Quality Data
 */
public class ViewHistoricalReportActivity extends AppCompatActivity {
    private final HashMap<Integer,Double> dataPoints = new HashMap<>();
    //private Integer current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_historical_report);
        final PPMType currentType = (PPMType) getIntent().getSerializableExtra("CurrentType");
        final Integer currentYear = (Integer) getIntent().getSerializableExtra("CurrentYear");
        final Double lat = (Double) getIntent().getSerializableExtra("Lat");
        final Double lng = (Double) getIntent().getSerializableExtra("Lng");

        final Button backButton = (Button) findViewById(R.id.backToHome_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        GraphView graph = (GraphView) findViewById(R.id.graph);
        PointsGraphSeries<DataPoint> series = new PointsGraphSeries<>(new DataPoint[] {
        });
        series.setShape(PointsGraphSeries.Shape.POINT);
        List<WaterQualityReport> selectedReports =
                Model.getInstance().generateSelectedReports(currentYear, lat, lng);
        getDataPoints(currentType, selectedReports);

        for (Integer m : dataPoints.keySet()) {
            series.appendData(new DataPoint(m, dataPoints.get(m)),true, 20);
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

    private void getDataPoints(PPMType type, Iterable<WaterQualityReport> selectedReports) {
        for (WaterQualityReport r : selectedReports){
            if (type.equals(PPMType.VIRUS)) {
                if (dataPoints.containsKey(r.getMonth() + 1)) {
                    dataPoints.put(r.getMonth() + 1, (dataPoints.get(r.getMonth() + 1) + r.
                            getVirusPPM()) / 2);
                } else {
                    dataPoints.put(r.getMonth() + 1, r.getVirusPPM());
                }
            } else {
                if (dataPoints.containsKey(r.getMonth() + 1)) {
                    dataPoints.put(r.getMonth() + 1, (dataPoints.get(r.getMonth() + 1) +
                            r.getContaminantsPPM()) / 2);
                } else {
                    dataPoints.put(r.getMonth() + 1, r.getContaminantsPPM());
                }
            }
        }

    }
}
