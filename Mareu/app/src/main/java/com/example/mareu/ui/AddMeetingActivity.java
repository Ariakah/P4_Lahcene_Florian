package com.example.mareu.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;

import java.io.Serializable;

public class AddMeetingActivity extends AppCompatActivity implements Serializable {

    EditText mDate, mTime, mLocation, mSubject, mParticipant;
    TimePicker mTimePicker;
    DatePicker mDatePicker;
    Button mButton;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        getIncomingIntent();

        mDate = findViewById(R.id.add_date);
        mTime = findViewById(R.id.add_time);
        mLocation = findViewById(R.id.add_location);
        mSubject = findViewById(R.id.add_subject);
        mParticipant = findViewById(R.id.add_participant);
        mTimePicker = findViewById(R.id.time_picker);
        mDatePicker = findViewById(R.id.date_picker);
        mButton = findViewById(R.id.button_meeting);

        mTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    mTimePicker.setVisibility(View.GONE);
                }
                else {
                    mTimePicker.setVisibility(View.VISIBLE);
                }
            }
        });

        mDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){
                    mDatePicker.setVisibility(View.GONE);
                }
                else {
                    mDatePicker.setVisibility(View.VISIBLE);
                }
            }
        });

        mTimePicker.setIs24HourView(true);

        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                mTime.setText(hourOfDay + "h" + minute);
                addMeeting.setDate(mTime.getText().toString());
            }
        });

        mDatePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                addMeeting.setDate(mDate.getText().toString());
            }
        });

        mLocation.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                addMeeting.setLocation(mLocation.getText().toString());
                return false;
            }
        });

        mButton.setOnClickListener(v -> this.finish());
    }

    private Meeting addMeeting = new Meeting();

    @Override
    public void finish() {

        Log.d("tagii", "finish");
        Intent i = new Intent();
        i.putExtra("addMeeting", addMeeting);
        this.setResult(RESULT_OK, i);
        super.finish();
    }

    private void getIncomingIntent() {

        if (getIntent().hasExtra("meeting")) {
            Meeting intentMeeting = (Meeting) getIntent().getSerializableExtra("meeting");;
            displayData(intentMeeting);
        }
    }

    private void displayData(Meeting meeting) {
        mDate.setText(meeting.getDate());
        mTime.setText(meeting.getTime());
        mLocation.setText(meeting.getLocation());
        mSubject.setText(meeting.getSubject());
        mParticipant.setText(meeting.getParticipants());
    }


}