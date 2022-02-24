package com.example.mareu.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.example.mareu.R;
import com.example.mareu.controller.adapters.MeetingRecyclerViewAdapter;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.ApiService;
import com.example.mareu.service.DummyMeetingApiService;
import com.example.mareu.service.DummyMeetingGenerator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ApiService mApiService;
    private FloatingActionButton mAdd;
    private List<Meeting> meetings = new ArrayList<>();
    private MeetingRecyclerViewAdapter mRecyclerViewAdapter;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiService = DI.getApiService();

        meetings = mApiService.getMeeting();

        mRecyclerView = findViewById(R.id.recyclerViewMainActivity);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewAdapter = new MeetingRecyclerViewAdapter(meetings);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mAdd = findViewById(R.id.add_meeting);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddMeetingActivity.class);
                //i.putExtra("meeting",);
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK){
            Meeting addMeeting = (Meeting) data.getSerializableExtra("addMeeting");
            meetings.add(addMeeting);
            //mRecyclerViewAdapter.notifyDataSetChanged();
            mRecyclerViewAdapter = new MeetingRecyclerViewAdapter(meetings);
            mRecyclerView.setAdapter(mRecyclerViewAdapter);
            Log.d("tagii", "on activity result");
            Log.d("tagii", "meeting size" + meetings.size());

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}