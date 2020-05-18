package com.vickx.obnlite.Models.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.vickx.obnlite.Models.Dossier;
import com.vickx.obnlite.Models.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class EventDAO extends DAOBase<Event> {

    //region Membres statiques

    private static final String key = "id";
    private static final String sujet ="sujet";
    private static final String verbe = "verbe";
    private static final String complement = "complement";
    private static final String time = "time";
    private static final String tale_name = "ObnLite";

    //endregion

    //region requêtes pré-enregistrées

    public static final String table_create =
            "CREATE TABLE " + tale_name + " (" +
                    key + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    sujet + " TEXT, "+
                    verbe + " TEXT, " +
                    complement + " TEXT," +
                    time + " TEXT" +
                    ");";
    public static final String table_drop = "DROP TABLE IF EXISTS " + tale_name + ";";

    public static final String content_erase = "DELETE FROM " + tale_name + " WHERE 1";

    //endregion

    //region Membres d'instance

    private Context context = null;

    //endregion

    //region Constructeurs

    public EventDAO(Context context) {
        super(context);
        this.context = context;
        this.open();
    }

    //endregion

    public void insert(Event event){
        ContentValues values = new ContentValues();
        values.put(sujet,event.getSubject());
        values.put(verbe,event.getVerb());
        values.put(complement, event.getComplement());
        LocalDateTime dateTime = event.getTime();
        values.put(time, dateTime.getDayOfMonth() + "/" + dateTime.getMonthValue() + "/" + dateTime.getYear() + "-" + dateTime.getHour() + ":" + dateTime.getMinute() + ":" + dateTime.getSecond());
        this.database.insert(tale_name,null ,values);
        //System.out.println("Logger: " + event.getDateTime() + ": " + event.toString());
        Toast.makeText(this.context,event.getTimeString() + ": " + event.getMessage(), Toast.LENGTH_SHORT).show();
    }

    public int erase(long id){
        return this.database.delete(tale_name,key  + "=?", new String[]{String.valueOf(id)});
    }

    public int update(Event event){
        ContentValues values = new ContentValues();
        values.put(sujet,event.getSubject());
        values.put(verbe,event.getVerb());
        values.put(complement, event.getComplement());
        LocalDateTime dateTime = event.getTime();
        values.put(time, dateTime.getDayOfMonth() + "/" + dateTime.getMonthValue() + "/" + dateTime.getYear() + "-" + dateTime.getHour() + ":" + dateTime.getMinute() + ":" + dateTime.getSecond());
        return this.database.update(tale_name, values,key + "=?",new String[]{String.valueOf(event.getId())});
    }

    public Event getFormId(long id){
        return null;
    }

    public ArrayList<Event> getFromDossier(Dossier dossier){
        return null;
    }

    public ArrayList<Event> getFromPeriod(LocalDateTime startTime, LocalDateTime endTime){
        return null;
    }


    public ArrayList<Event> getAll(){
        return this.getSome("",null);
    }

    public ArrayList<Event> getSome(String condition, String[] conditionArgs){

        ArrayList<Event> events = new ArrayList<>();

        Cursor cursor;
        if(conditionArgs != null)
            cursor = this.database.rawQuery("SELECT * FROM " + tale_name + " " + condition, conditionArgs);
        else
            cursor = this.database.rawQuery("SELECT * FROM " + tale_name + " WHERE ?", new String[]{"1"});
        if(cursor.getCount() == 0)
            return null;

        while(cursor.moveToNext()) {
            Event event = new Event();
            event.setId(cursor.getInt(0));
            event.setSubject(cursor.getString(1));
            event.setVerb(cursor.getString(2));
            event.setComplement(cursor.getString(3));
            String dateTime = cursor.getString(4);
            String[] parts = dateTime.split("-");
            String[] dateParts = parts[0].split("/");
            String[] timeParts = parts[1].split(":");
            int day = Integer.parseInt(dateParts[0]),
                    month = Integer.parseInt(dateParts[1]),
                    year = Integer.parseInt(dateParts[2]),
                    hour = Integer.parseInt(timeParts[0]),
                    minute = Integer.parseInt(timeParts[1]),
                    second = Integer.parseInt(timeParts[2]);

            event.setTime(LocalDateTime.of(year, month, day, hour, minute, second));
            events.add(event);
        }
        return events;
    }

    public void eraseAll(){
        this.database.execSQL(content_erase);
    }

}
