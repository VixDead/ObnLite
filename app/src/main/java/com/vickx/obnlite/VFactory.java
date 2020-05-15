package com.vickx.obnlite;

import com.vickx.obnlite.Models.Event;
import com.vickx.obnlite.Models.Observation;
import com.vickx.obnlite.Models.Person;

public abstract class VFactory {
    private static Observation observation;
    private static Event currentEvent;
    private static Person currentPerson;

    public static Observation getObservation() {
        return observation;
    }

    public static void setObservation(Observation observation) {
        VFactory.observation = observation;
    }

    public static Event getCurrentEvent() {
        return currentEvent;
    }

    public static void setCurrentEvent(Event currentEvent) {
        VFactory.currentEvent = currentEvent;
    }
}
