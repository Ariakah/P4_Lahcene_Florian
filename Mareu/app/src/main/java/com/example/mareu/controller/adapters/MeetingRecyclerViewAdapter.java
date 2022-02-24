package com.example.mareu.controller.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.model.Meeting;

import java.util.List;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.MeetingViewHolder> {

    private List<Meeting> mMeetingList;

    public MeetingRecyclerViewAdapter(List<Meeting> items){
        mMeetingList = items;
    }


    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meeting_item, parent, false);
        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting meeting = mMeetingList.get(position);
        holder.date.setText(meeting.getDate());
        holder.time.setText(meeting.getTime());
        holder.location.setText(meeting.getLocation());
        holder.subject.setText(meeting.getSubject());

    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }

    public class MeetingViewHolder extends RecyclerView.ViewHolder{

        public TextView date, time, location, subject;

        public MeetingViewHolder(View view){
            super(view);
            date = view.findViewById(R.id.date);
            time = view.findViewById(R.id.time);
            location = view.findViewById(R.id.location);
            subject = view.findViewById(R.id.subject);
        }

    }

}
