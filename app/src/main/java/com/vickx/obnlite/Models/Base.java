package com.vickx.obnlite.Models;

import android.os.Parcelable;

public interface Base extends Parcelable {

    int getId();
    String getName();
    void setId(int i);
    void setName(String s);

}
