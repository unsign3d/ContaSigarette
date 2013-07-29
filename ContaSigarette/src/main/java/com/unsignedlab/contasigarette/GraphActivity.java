package com.unsignedlab.contasigarette;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GraphActivity extends Activity {

    private XYPlot mPlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_main);

        //initialize the plot
        this.mPlot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

        //retrive the data
        SharedPreferences prefs = this.getSharedPreferences("DATABASE", 0);
        TreeMap<String, ?> keys = new TreeMap<String, Object>(prefs.getAll());


        List <Number> mData = new ArrayList<Number>();

        for(Map.Entry<String,?> entry : keys.entrySet()){
            if(entry.getValue() instanceof Integer){
                mData.add((Number) entry.getValue());
            }
        }

        XYSeries series = new SimpleXYSeries(mData,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,
                "Sigarette");
        LineAndPointFormatter series1Format = new LineAndPointFormatter();
        series1Format.setPointLabelFormatter(new PointLabelFormatter());
        series1Format.setPointLabelFormatter(new PointLabelFormatter());
        series1Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf1);

        // add a new series' to the xyplot:
        mPlot.addSeries(series, series1Format);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.graph, menu);
        return true;
    }
    
}
