package com.example.halalyon.vitalo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.halalyon.vitalo.R;
import com.example.halalyon.vitalo.Model.VitaloData;
import com.example.halalyon.vitalo.ModelAdapter.VitaloDataAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by halalyon on 19/10/15.
 */
public class TabActivity3 extends Activity {

    private static final String[] timestyle = {"Day","Hour","Minute"};
    public final int EDIT_ITEM_REQUEST_CODE = 647;
    private Spinner spinner;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private VitaloDataAdapter vadapter;
    private ArrayList<VitaloData> data = new ArrayList<VitaloData>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab3);

        listView = (ListView) findViewById(R.id.listView);
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timestyle);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        vadapter = new VitaloDataAdapter(this, data);
        listView.setAdapter(vadapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (timestyle[position].equals("Minute")) {

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date d = null;
                    try {
                        d = sf.parse("2015-10-26 03:00");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Log.i("time", Long.toString(c.getTimeInMillis()));
                    List<VitaloData> queryresult = new Select().from(VitaloData.class).where(" timestamp > ? ", c.getTimeInMillis() - 60).execute();
                    vadapter.addAll(queryresult);

                } else if (timestyle[position].equals("Hour")) {
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date d = null;
                    try {
                        d = sf.parse("2015-10-26 03:00");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Log.i("time", Long.toString(c.getTimeInMillis()));
                    List<VitaloData> queryresult = new Select().from(VitaloData.class).where(" timestamp > ? ", c.getTimeInMillis() - 7600).execute();
                    vadapter.addAll(queryresult);


                } else {
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date d = null;
                    try {
                        d = sf.parse("2015-10-26 03:00");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Log.i("time", Long.toString(c.getTimeInMillis()));
                    List<VitaloData> queryresult = new Select().from(VitaloData.class).where(" timestamp > ? ", c.getTimeInMillis() - 86400).execute();
                    vadapter.addAll(queryresult);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String update = vadapter.getItem(position).note;
                Intent intent = new Intent(TabActivity3.this, EditNotesActivity.class);
                if (intent != null){
                    intent.putExtra("notes", update);
                    intent.putExtra("position", position);
                    startActivityForResult(intent, EDIT_ITEM_REQUEST_CODE);
                    vadapter.notifyDataSetChanged();
                }
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EDIT_ITEM_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                final String editedItem = data.getExtras().getString("updatednotes");
                int position = data.getIntExtra("position", -1);
                vadapter.getItem(position).note = editedItem;
                vadapter.getItem(position).save();
                Log.i("Updated Item in list:", editedItem + ",position:"
                        + position);
                Toast.makeText(this, "updated:" + editedItem, Toast.LENGTH_SHORT).show();
                vadapter.notifyDataSetChanged();
            }
        }
    }



}
