package com.vickx.obnlite.Models.DAO;

import com.vickx.obnlite.Models.Event;
import com.vickx.obnlite.Models.Observation;


public class ObservationDAO {

    public static void log(Observation observation, Event event){

        System.out.println("Logger: " + event.getDateTime() + ": " + event.toString());
    }

}
