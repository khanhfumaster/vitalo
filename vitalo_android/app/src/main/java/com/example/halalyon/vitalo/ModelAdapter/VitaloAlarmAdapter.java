package com.example.halalyon.vitalo.ModelAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.halalyon.vitalo.Model.VitaloAlarm;
import com.example.halalyon.vitalo.R;

import java.util.ArrayList;

/**
 * Created by halalyon on 26/10/15.
 */
public class VitaloAlarmAdapter extends ArrayAdapter<VitaloAlarm> {


    public VitaloAlarmAdapter(Context context, ArrayList<VitaloAlarm> vitaloData) {
        super(context,0, vitaloData);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        VitaloAlarm va = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vitaloalarm, parent, false);
        }
        // Lookup view for data population
        TextView tvmessage = (TextView) convertView.findViewById(R.id.tvMessage);
        TextView tvtime = (TextView) convertView.findViewById(R.id.tvTi);
        TextView tvdate = (TextView) convertView.findViewById(R.id.tvDt);
        // Populate the data into the template view using the data object
        tvmessage.setText("Messages:" + va.message);
        tvtime.setText("Time:" + va.time);
        tvdate.setText("Date:"+va.date);

        // Return the completed view to render on screen
        return convertView;
    }
}

