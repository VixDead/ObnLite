package com.vickx.obnlite.Models;


import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;

public class Dossier implements Base {

    private int id;
    private String name;

    public static final Parcelable.Creator<Dossier> CREATOR = new Parcelable.Creator<Dossier>(){

        @Override
        public Dossier createFromParcel(Parcel source) {
            return new Dossier(source);
        }

        @Override
        public Dossier[] newArray(int size) {
            return new Dossier[size];
        }
    };

    public Dossier(){
        this.id = 0;
        this.name = "UnNamed";
    }

    public Dossier(int id, String name){
        this.id = id;
        this.name = name;
    }

    private Dossier(Parcel source) {
        this.setId(source.readInt());
        this.setName(source.readString());
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setId (int i) {
        this.id = i;
    }

    @Override
    public void setName(String s) {
        this.name = s;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    @Override
    public String toString(){
        return this.getName();
    }
}
