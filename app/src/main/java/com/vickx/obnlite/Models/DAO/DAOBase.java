package com.vickx.obnlite.Models.DAO;

import java.util.ArrayList;

public abstract class DAOBase<T> {
    public abstract T getItem(int id);
    public abstract ArrayList<T> getAll();
    public abstract ArrayList<T> getSome(String condition);
    public abstract T Insert(T object);
}
