package com.example.mareu.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mareu.R;
import com.example.mareu.controller.adapters.MeetingRecyclerViewAdapter;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.ApiService;
import com.example.mareu.service.DummyMeetingApiService;
import com.example.mareu.service.DummyMeetingGenerator;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private ApiService mApiService;
    private List<Meeting> meetings = new ArrayList<>();
    private MeetingRecyclerViewAdapter mRecyclerViewAdapter;

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mApiService = DI.getApiService();

        meetings = mApiService.getMeeting();

        mRecyclerView = findViewById(R.id.rv_meeting);

        FloatingActionButton add = findViewById(R.id.add_meeting);

        initList(meetings);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddMeetingActivity.class);
                startActivityForResult(i, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            Meeting addMeeting = (Meeting) data.getSerializableExtra("addMeeting");
            meetings.add(addMeeting);
            mRecyclerViewAdapter.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initList(List<Meeting> meetingList) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewAdapter = new MeetingRecyclerViewAdapter(meetingList);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_date) {
            Calendar mCalendar = Calendar.getInstance();
            int year = mCalendar.get(Calendar.YEAR);
            int month = mCalendar.get(Calendar.MONTH);
            int dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);
            new DatePickerDialog(this, (DatePickerDialog.OnDateSetListener)
                    this, year, month, dayOfMonth).show();
        }
        if (menuItem.getItemId() == R.id.menu_room){
            AlertDialog.Builder b = new AlertDialog.Builder(this);
            b.setTitle("Choose the room");
            String[] types = {"Room 01", "Room 02", "Room 03", "Room 04", "Room 05", "Room 06", "Room 07",
            "Room 08", "Room 09", "Room 10"};
            b.setItems(types, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    String selectedRoom = types[which];
                    List<Meeting> filterMeeting = mApiService.sortMeetingByRoom(selectedRoom);
                    initList(filterMeeting);
                }

            });
            b.show();
        }
        if (menuItem.getItemId() == R.id.menu_all) {
            initList(meetings);
        }
        return true;
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
        String selectedDate = new SimpleDateFormat("dd/MM/yyyy").format(mCalendar.getTime());
        List<Meeting> filterMeeting = mApiService.sortMeetingByDate(selectedDate);
        // Set the textview to the selectedDate String
        initList(filterMeeting);
    }
}