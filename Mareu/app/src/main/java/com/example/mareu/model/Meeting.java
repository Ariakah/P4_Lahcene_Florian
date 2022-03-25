package com.example.mareu.model;

import android.graphics.Color;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class Meeting implements Serializable {

    String date;
    String time;
    String location;
    String subject;
    String participants;
    int color;

    public Meeting() {
    }

    public Meeting(String date, String time, String location, String subject, String participants) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.subject = subject;
        this.participants = participants;
        this.color = Color.rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
