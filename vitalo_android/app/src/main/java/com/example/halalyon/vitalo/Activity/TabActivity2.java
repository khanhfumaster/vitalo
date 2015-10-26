package com.example.halalyon.vitalo.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.example.halalyon.vitalo.R;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.chart.XYChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.io.IOException;
import java.util.Random;

/**
 * Created by halalyon on 19/10/15.
 */
public class TabActivity2 extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2);

//        XYSeries series = new XYSeries("Hourly pulse");
//        series.add(0, 50);
//        series.add(1, 45);
//        series.add(2, 53);
//        series.add(3, 55);
//        series.add(4, 60);
//        series.add(5, 62);
//        series.add(6, 45);
//        XYSeriesRenderer render = new XYSeriesRenderer();
//        render.setLineWidth(2);
//        render.setColor(Color.RED);
//        render.setDisplayBoundingPoints(true);
//        render.setPointStyle(PointStyle.CIRCLE);
//        render.setPointStrokeWidth(3);
//
//        XYMultipleSeriesRenderer mrender = new XYMultipleSeriesRenderer();
//        mrender.addSeriesRenderer(render);
//
//        mrender.setMarginsColor(Color.argb(0x00, 0xff, 0x00, 0x00)); // transparent margins
//// Disable Pan on two axis
//        mrender.setPanEnabled(false, false);
//        mrender.setYAxisMax(35);
//        mrender.setYAxisMin(0);
//        mrender.setShowGrid(true); // we show the grid
//
//        Intent intent = ChartFactory.getLineChartIntent(this, getDemoDataset(), mrender);
//        startActivity(intent);
//
//
//
//
//    }
//
//    private XYMultipleSeriesDataset getDemoDataset() {
//        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
//        final int nr = 10;
//        Random r = new Random();
//        for (int i = 0; i < 20; i++) {
//            XYSeries series = new XYSeries("Demo series " + (i + 1));
//            for (int k = 0; k < nr; k++) {
//                series.add(k, 20 + r.nextInt() % 100);
//            }
//            dataset.addSeries(series);
//        }
//        return dataset;
//    }

    }
}