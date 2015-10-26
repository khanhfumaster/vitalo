package com.example.halalyon.vitalo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.ListView;

import com.example.halalyon.vitalo.R;
import com.example.halalyon.vitalo.Model.VitaloAlarm;
import com.example.halalyon.vitalo.ModelAdapter.VitaloAlarmAdapter;
import com.example.halalyon.vitalo.WebService.WebService2;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by halalyon on 24/10/15.
 */
public class TabActivity4 extends Activity{
    WebService2 ws2 = new WebService2();
    JSONObject list =null;
    DatePicker dp;
    ListView listView;
    private VitaloAlarmAdapter vaAdapter;
    private ArrayList<VitaloAlarm> vaList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab4);

//        list = ws2.request2("1");
//        Log.i("tag", list.toString());
        VitaloAlarm va = new VitaloAlarm();
//        try {
//            va.message = list.getString("message");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        String datetime = null;
//        try {
//            datetime = list.getString("created_at");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        va.date = datetime.substring(0, 9);
//        va.time = datetime.substring(11, 18);
        va.message = "Pulse value has fallen below the minimum threshold of 99.0";
        va.time = "11:49:42";
        va.date = "2015-10-26";
        va.save();


        dp = (DatePicker) findViewById(R.id.datePicker);
        listView = (ListView) findViewById(R.id.lvAlarm);
        vaAdapter = new VitaloAlarmAdapter(this, vaList);
        listView.setAdapter(vaAdapter);
        //List<VitaloAlarm> queryresult = new Select().from(VitaloAlarm.class).execute();
        vaAdapter.add(va);

//        dp.init(2015, 10, 26, new DatePicker.OnDateChangedListener(){
//            @Override
//            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(year, monthOfYear, dayOfMonth);
//                SimpleDateFormat sf = new SimpleDateFormat("YYYY-MM-DD");
//                Toast.makeText(TabActivity4.this, sf.format(calendar.getTime()), Toast.LENGTH_SHORT).show();
//                List<VitaloAlarm> queryresult = new Select().from(VitaloAlarm.class).where(" date = ? ", sf.format(calendar.getTime())).execute();
//                vaAdapter.addAll(queryresult);
//            }
//        });

    }
}
