package com.example.halalyon.vitalo.Activity;

import android.app.ActivityGroup;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import com.example.halalyon.vitalo.R;

public class MainActivity extends ActivityGroup {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup(this.getLocalActivityManager());
        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third Tab");
        TabHost.TabSpec tab4 = tabHost.newTabSpec("Fourth Tab");

        tab1.setIndicator("RealData", getResources().getDrawable(R.drawable.realdata_icon));
        tab1.setContent(new Intent(this, TabActivity1.class));

        tab2.setIndicator("Chart", getResources().getDrawable(R.drawable.chart_icon));
        tab2.setContent(new Intent(this, TabActivity2.class));

        tab3.setIndicator("History", getResources().getDrawable(R.drawable.history_icon));
        tab3.setContent(new Intent(this, TabActivity3.class));

        tab4.setIndicator("Alarm", getResources().getDrawable(R.drawable.alarm_icon));
        tab4.setContent(new Intent(this, TabActivity4.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
        tabHost.addTab(tab4);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            public void onTabChanged(String arg0) {
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    tabHost.getTabWidget().getChildAt(i)
                            .setBackgroundColor(Color.parseColor("#F9F9DD")); // unselected
                }
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab())
                        .setBackgroundColor(Color.parseColor("#96938B")); // selected

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
