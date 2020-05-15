package com.vickx.obnlite.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.vickx.obnlite.Models.DAO.ObservationDAO;
import com.vickx.obnlite.VFactory;

import java.time.LocalDateTime;

public class Observation implements Parcelable {


    private LocalDateTime start = null;
    private Dossier dossier = null;
    private ObnState state = ObnState.stopped;

    private Observation(Dossier dossier,LocalDateTime start, ObnState state)
    {
        this.start = start;
        this.dossier = dossier;
        this.state = state;
    }

    public static Observation startNew(Dossier dossier){
        Observation observation = new Observation(dossier, LocalDateTime.now(), ObnState.started);
        ObservationDAO.log(observation, new Event("Observation","starts"));
        return observation;
    }

    public String onString(){
        return "Observation started on " + this.start.getDayOfMonth() + "/" + this.start.getMonthValue() + "/" + this.start.getYear() + " at " + this.start.getHour() + ":" + this.start.getMinute();
    }

    public ObnState getState() {
        return state;
    }

    public void stop() {
        ObservationDAO.log(this,new Event("Observation","stops"));
        this.state = ObnState.stopped;
    }


    public Dossier getDossier() {
        return this.dossier;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.dossier,0);
        dest.writeString(this.start.getDayOfMonth()+"/" + this.start.getMonthValue() + "/" + this.start.getYear() + " " + this.start.getHour() + ":" + this.start.getMinute() + ":" + this.start.getSecond());
        dest.writeInt(this.state.ordinal());
    }

    public static Parcelable.Creator<Observation> CREATOR = new Parcelable.Creator<Observation>(){

        @Override
        public Observation createFromParcel(Parcel source) {

            Dossier dossier= source.readParcelable(Dossier.class.getClassLoader());
            String timeString = source.readString();
            ObnState state;
            if (source.readInt() == 1) {
                state = ObnState.started;
            } else {
                state = ObnState.stopped;
            }
            LocalDateTime start;
            if(timeString != null) {
                String[] parts = timeString.split(" ");
                String[] dateParts = parts[0].split("/");
                String[] timeParts = parts[1].split(":");
                 start = LocalDateTime.of(
                        Integer.parseInt(dateParts[2]),
                        Integer.parseInt(dateParts[1]),
                        Integer.parseInt(dateParts[0]),
                        Integer.parseInt(timeParts[0]),
                        Integer.parseInt(timeParts[1]),
                        Integer.parseInt(timeParts[2]));

            }else
                start = LocalDateTime.now();


            return new Observation(dossier,start,state);
        }

        @Override
        public Observation[] newArray(int size) {
            return new Observation[size];
        }
    };

    public enum ObnState{
        started,stopped
    }
}
