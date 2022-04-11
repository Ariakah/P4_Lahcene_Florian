package com.example.mareu.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class AddMeetingActivity extends AppCompatActivity implements Serializable, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    EditText mDate, mTime, mSubject, mParticipant;
    Spinner mLocation;
    Button mButton;
    ImageView mImageView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        mDate = findViewById(R.id.add_date);
        mTime = findViewById(R.id.add_time);
        mLocation = findViewById(R.id.add_location);
        mSubject = findViewById(R.id.add_subject);
        mParticipant = findViewById(R.id.add_participant);
        mButton = findViewById(R.id.button_meeting);
        mImageView = findViewById(R.id.image_circle);


        mTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    TimePickerFragment timePicker = new TimePickerFragment();
                    timePicker.show(getSupportFragmentManager(),"tag");
                }
                else {
                }
            }
        });

        mDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    DatePickerFragment datePicker = new DatePickerFragment();
                    datePicker.setContext(AddMeetingActivity.this);
                    datePicker.show(getSupportFragmentManager(),"tag");
                }
                else {
                }
            }
        });

        mButton.setOnClickListener(v -> {
            addMeeting.setDate(mDate.getText().toString());
            addMeeting.setTime(mTime.getText().toString());
            addMeeting.setLocation(mLocation.getSelectedItem().toString());
            addMeeting.setSubject(mSubject.getText().toString());
            addMeeting.setParticipants(mParticipant.getText().toString());
            addMeeting.setColor(Color.rgb(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255)));
            this.finish();
        });

        mLocation.setAdapter(ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.defaultValue, android.R.layout.simple_spinner_item));
    }

    private Meeting addMeeting = new Meeting();

    @Override
    public void finish() {
        Intent i = new Intent();
        i.putExtra("addMeeting", addMeeting);
        this.setResult(RESULT_OK, i);
        super.finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // Create a Calendar instance
        Calendar mCalendar = Calendar.getInstance();
        // Set static variables of Calendar instance
        mCalendar.set(Calendar.YEAR,year);
        mCalendar.set(Calendar.MONTH,month);
        mCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        // Get the date in form of string
        //String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
        String selectedDate = new SimpleDateFormat("dd/MM/yyyy").format(mCalendar.getTime());
        // Set the textview to the selectedDate String
        mDate.setText(selectedDate);
    }

    @Override
    public void onTimeSet(TimePicker view, int hour, int minute) {
        mTime.setText(hour + "h" +minute);
    }
}