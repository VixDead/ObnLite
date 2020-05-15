package com.vickx.obnlite.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Base {

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>(){

        @Override
        public Person createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setId(int i) {

    }

    @Override
    public void setName(String s) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
