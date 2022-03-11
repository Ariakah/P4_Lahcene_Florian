package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETING = Arrays.asList(

            new Meeting("01/01/2022", "9h00", "Salle 01", "Urgent !", "date@msn.com "),
            new Meeting("22/01/2022", "15h00", "Salle 02", "Test", "test2@test.com")

    );

    public static List<Meeting> generateMeeting() {
        return new ArrayList<>(DUMMY_MEETING);
    }
}
