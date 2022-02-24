package com.example.mareu.di;

import com.example.mareu.service.ApiService;
import com.example.mareu.service.DummyMeetingApiService;

public class DI {

    private static ApiService service = new DummyMeetingApiService();

    public static ApiService getApiService() {
        return service;
    }

    public static ApiService getNewInstanceApiService() {
        return new DummyMeetingApiService() ;
    }

}
