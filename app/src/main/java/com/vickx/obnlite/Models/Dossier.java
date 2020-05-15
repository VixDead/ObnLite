package com.vickx.obnlite.Models;


import android.os.Parcel;
import android.os.Parcelable;

import com.vickx.obnlite.Models.DAO.DossierDAO;

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

    public static Dossier createNew() {
        LocalDate localDate = LocalDate.now();
        Dossier dossier = new Dossier(0,"Sans nom du " + localDate.getDayOfMonth() + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear());
        return new DossierDAO().Insert(dossier);
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
