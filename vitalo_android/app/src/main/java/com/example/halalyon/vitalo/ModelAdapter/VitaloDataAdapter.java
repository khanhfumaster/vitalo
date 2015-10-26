package com.example.halalyon.vitalo.ModelAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.halalyon.vitalo.Model.VitaloData;
import com.example.halalyon.vitalo.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by halalyon on 25/10/15.
 */
public class VitaloDataAdapter extends ArrayAdapter<VitaloData> {


    public VitaloDataAdapter(Context context, ArrayList<VitaloData> vitaloData) {
        super(context,0, vitaloData);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        VitaloData vd = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vitalodata, parent, false);
        }
        // Lookup view for data population
        TextView tvpulse = (TextView) convertView.findViewById(R.id.tvPulse);
        TextView tvspo2 = (TextView) convertView.findViewById(R.id.tvSpO2);
        TextView tvmovement = (TextView) convertView.findViewById(R.id.tvMovement);
        TextView tvtime = (TextView) convertView.findViewById(R.id.tvTime);
        TextView tvnotes = (TextView) convertView.findViewById(R.id.tvNotes);
        // Populate the data into the template view using the data object
        tvpulse.setText("Pulse:"+vd.pulse);
        tvspo2.setText("SpO2:"+vd.spo2);
        tvmovement.setText("Movement:"+vd.movement);
        Date date = new Date(vd.timestamp);
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        sdf.setCalendar(cal);
        cal.setTime(date);

        tvtime.setText("Time:"+sdf.format(date));
        tvnotes.setText("Notes:"+vd.note);
        // Return the completed view to render on screen
        return convertView;
    }
}
