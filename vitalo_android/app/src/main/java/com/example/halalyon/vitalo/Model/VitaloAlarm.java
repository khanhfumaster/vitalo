package com.example.halalyon.vitalo.Model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
/**
 * Created by halalyon on 26/10/15.
 */
@Table(name = "VitaloAlarm")
public class VitaloAlarm extends Model{

    @Column(name = "remote_id")
    public long remoteId;

    @Column(name = "message")
    public String message;

    @Column(name = "time")
    public String time;

    @Column(name = "date")
    public String date;


    public VitaloAlarm(){
        super();
    }

    public VitaloAlarm(long remoteId, String message, String time, String date){
        this.remoteId = remoteId;
        this.message = message;
        this.time = time;
        this.date = date;

    }

}
