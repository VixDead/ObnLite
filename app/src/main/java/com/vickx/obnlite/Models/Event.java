package com.vickx.obnlite.Models;


import java.time.LocalDateTime;

public class Event {

    private LocalDateTime time = null;
    private Observation observation = null;
    private String string = "";
    private String subject = null;
    private String verb = null;
    private String complement = null;

    public Event(String subject, String verb, String complement){
        this.subject = subject;
        this.verb = verb;
        this.complement = complement;
        this.time = LocalDateTime.now();
    }
    public Event(String subject, String verb){
        this(subject,verb,"");
    }

    public Event(String eventString){
        this(eventString,"","");
    }

    public LocalDateTime getDateTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Observation getObservation() {
        return observation;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
    }

    public String toString(){
        String string = this.subject;
        if(!this.verb.equals(""))
            string += " " + verb;
        if(!this.complement.equals(""))
            string += " " + complement;

        return string;
    }
}
