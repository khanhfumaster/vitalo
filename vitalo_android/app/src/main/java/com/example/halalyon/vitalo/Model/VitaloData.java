package com.example.halalyon.vitalo.Model;

import android.util.Log;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by halalyon on 25/10/15.
 */
@Table(name = "VitaloData")
public class VitaloData extends Model {
    // This is the unique id given by the server
    @Column(name = "remote_id")
    public long remoteId;

    @Column(name = "Pulse")
    public String pulse;

    @Column(name = "Spo2")
    public String spo2;

    @Column(name = "Movement")
    public String movement;

    @Column(name = "Timestamp")
    public Long timestamp;

    @Column(name = "Notes")
    public String note;

    // Make sure to have a default constructor for every ActiveAndroid model
    public VitaloData(){
        super();
    }

    public VitaloData(int dataId, String pulse, String spo2, String movement, Long timestamp, String note){

        super();
        this.remoteId = dataId;
        this.pulse = pulse;
        this.spo2 = spo2;
        this.movement = movement;
        this.timestamp = timestamp;
        this.note = note;
    }

//    public void setDateFromString(String date) {
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        sf.setLenient(true);
//        try {
//            this.timestamp = sf.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//
    public void setCurrentTime(){
        Calendar c = Calendar.getInstance();
        this.timestamp = c.getTimeInMillis();
        Log.i("time",timestamp.toString());
    }


}
