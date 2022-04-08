package com.example.mareu;

import org.junit.Test;
import static org.junit.Assert.*;

import com.example.mareu.model.Meeting;
import com.example.mareu.service.ApiService;
import com.example.mareu.service.DummyMeetingApiService;
import com.example.mareu.service.DummyMeetingGenerator;

import java.util.ArrayList;
import java.util.List;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class MeetingTest {

    private ApiService mService;
    private List<Meeting> mMeetingList;

    @Test
    public void createMeetingWithSuccess() {
        Meeting meeting = new Meeting("20/01/2022", "07h30", "Room 03", "Test", "reg@efrz.com");
        DummyMeetingApiService dummyMeetingApiService = new DummyMeetingApiService();
        dummyMeetingApiService.createMeeting(meeting);

        assertEquals(dummyMeetingApiService.getMeeting().contains((Object)meeting),true);
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meeting = new Meeting("20/01/2022", "07h30", "Room 03", "Test", "reg@efrz.com");
        DummyMeetingApiService dummyMeetingApiService = new DummyMeetingApiService();
        dummyMeetingApiService.createMeeting(meeting);
        dummyMeetingApiService.deleteMeeting(meeting);
        assertEquals(dummyMeetingApiService.getMeeting().contains((Object)meeting),false);
    }

    @Test
    public void filterMeetingWithSuccess() {
        DummyMeetingApiService dummyMeetingApiService = new DummyMeetingApiService();
        List<Meeting> meetings = new ArrayList<>();

        Meeting meeting = new Meeting("01/01/2022", "9h00", "Room 01", "Urgent !", "date@msn.com ");
        dummyMeetingApiService.createMeeting(meeting);

        meeting = new Meeting("22/01/2022", "15h00", "Room 02", "Test", "test2@test.com");
        dummyMeetingApiService.createMeeting(meeting);

        meeting = new Meeting("22/01/2022", "15h00", "Room 03", "Test", "test2@test.com");
        dummyMeetingApiService.createMeeting(meeting);

        meeting = new Meeting("22/01/2022", "15h00", "Room 04", "Test", "test2@test.com");
        dummyMeetingApiService.createMeeting(meeting);

        meeting = new Meeting("22/01/2022", "15h00", "Room 05", "Test", "test2@test.com");
        dummyMeetingApiService.createMeeting(meeting);

        meeting = new Meeting("12/01/2022", "15h00", "Room 06", "Test", "test2@test.com");
        dummyMeetingApiService.createMeeting(meeting);

        meeting = new Meeting("22/01/2022", "15h00", "Room 10", "Test", "test2@test.com");
        dummyMeetingApiService.createMeeting(meeting);

        meeting = new Meeting("25/01/2022", "15h00", "Room 08", "Test", "test2@test.com");
        dummyMeetingApiService.createMeeting(meeting);

        meeting = new Meeting("22/01/2022", "15h00", "Room 09", "Test", "test2@test.com");
        dummyMeetingApiService.createMeeting(meeting);

        meeting = new Meeting("25/01/2022", "15h00", "Room 10", "Test", "test2@test.com");
        dummyMeetingApiService.createMeeting(meeting);

        meetings = dummyMeetingApiService.sortMeetingByRoom("Room 10");
        for (Meeting meeting1 : meetings) {
            assertEquals(meeting1.getLocation(), "Room 10");
        }

        meetings = dummyMeetingApiService.sortMeetingByDate("25/01/2022");
        for (Meeting meeting1 : meetings) {
            assertEquals(meeting1.getDate(), "25/01/2022");
        }
    }
}