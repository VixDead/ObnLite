package com.vickx.obnlite.Models;


import com.vickx.obnlite.VFactory;

import java.time.LocalDateTime;

public class Event {

    private LocalDateTime time = null;
    private int id;
    private String subject;
    private String verb;
    private String complement;

    public Event(){
        this("John doe", "did", "that");
    }

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

    public String getDateTime() {
        String timestring;
        timestring = VFactory.addZero(this.time.getDayOfMonth()) + "/" + VFactory.addZero(this.time.getMonthValue()) + "/" + this.time.getYear() + " " + VFactory.addZero(this.time.getHour()) + ":" + VFactory.addZero(this.time.getMinute()) + ":" + VFactory.addZero(this.time.getSecond());
        return timestring;
    }

    public LocalDateTime getTime(){
        return this.time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
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

    public String toString(){
        String string = "Le ";
        string += this.getDateTime() + " " + this.subject;
        if(!this.verb.equals(""))
            string += " " + verb;
        if(!this.complement.equals(""))
            string += " " + complement;

        return string;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage(){
        return this.subject + " " + verb + " " + complement;
    }

    public String getTimeString(){
        return VFactory.addZero(this.time.getHour()) + ":" + VFactory.addZero(this.time.getMinute()) + ":" + VFactory.addZero(this.time.getSecond());
    }

}
