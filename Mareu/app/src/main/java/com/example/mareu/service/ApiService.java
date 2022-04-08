package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

public interface ApiService {

    List<Meeting> getMeeting();

    void deleteMeeting(Meeting meeting);

    void createMeeting (Meeting meeting);

    List<Meeting> sortMeetingByDate (String date);

    List<Meeting> sortMeetingByRoom (String room);
}
