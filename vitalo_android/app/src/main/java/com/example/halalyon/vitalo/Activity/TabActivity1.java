package com.example.halalyon.vitalo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.halalyon.vitalo.R;
import com.example.halalyon.vitalo.Model.VitaloData;
import com.example.halalyon.vitalo.WebService.WebService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by halalyon on 24/10/15.
 */
public class TabActivity1 extends Activity {

    WebService ws = new WebService();
    JSONObject json;
    TextView pulse;
    TextView spO2;
    TextView movement;
    TextView displaypulse;
    TextView displayspO2;
    TextView displaymovement;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1);
        pulse = (TextView) findViewById(R.id.pulse);
        spO2 = (TextView) findViewById(R.id.spO2);
        movement = (TextView) findViewById(R.id.movement);
        displaypulse = (TextView) findViewById(R.id.displaypulse);
        displayspO2 = (TextView) findViewById(R.id.displayspO2);
        displaymovement = (TextView) findViewById(R.id.displaymovement);

        Timer timer = new Timer();
        long delay = 1000;
        long period = 1000;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                autorun();
            }
        }, delay, period);

    }

    public void autorun(){
        json = ws.request1("1234765");
        try {
            VitaloData vd = new VitaloData();
            vd.pulse = json.getString("pulse");
            vd.spo2 = json.getString("spo2");
            vd.movement = json.getString("movement");
            vd.setCurrentTime();
            vd.save();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        displaypulse.setText(json.getString("pulse"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        displayspO2.setText(json.getString("spo2"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    try {
                        displaymovement.setText(json.getString("movement"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });



        } catch (JSONException e) {
            e.printStackTrace();
        }


       }
}