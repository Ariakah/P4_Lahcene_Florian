package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements ApiService{

    private List<Meeting> mMeetings = DummyMeetingGenerator.generateMeeting();
    private List<Meeting> filterMeetingRoom = new ArrayList<Meeting>();
    private List<Meeting> filterMeetingDate = new ArrayList<Meeting>();


    @Override
    public List<Meeting> getMeeting() {
        return mMeetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
        filterMeetingRoom.remove(meeting);
        filterMeetingDate.remove(meeting);
    }

    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    @Override
    public List<Meeting> sortMeetingByDate(String date) {
        filterMeetingDate = new ArrayList<Meeting>();
        for (int i = 0; i < mMeetings.size(); i++){
            if (mMeetings.get(i).getDate().equals(date))
                filterMeetingDate.add(mMeetings.get(i));
        }
        return filterMeetingDate;
    }

    @Override
    public List<Meeting> sortMeetingByRoom(String room) {
        filterMeetingRoom = new ArrayList<Meeting>();
        for (int i = 0; i < mMeetings.size(); i++){
            if (mMeetings.get(i).getLocation().equals(room))
                filterMeetingRoom.add(mMeetings.get(i));
        }
        return filterMeetingRoom;
    }
}
