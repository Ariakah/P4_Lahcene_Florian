package com.example.mareu.controller.adapters;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.ApiService;
import com.example.mareu.service.DummyMeetingApiService;

import java.util.List;
import java.util.Random;

public class MeetingRecyclerViewAdapter extends RecyclerView.Adapter<MeetingRecyclerViewAdapter.MeetingViewHolder> {

    private List<Meeting> mMeetingList;

    ApiService mApiService = DI.getApiService();

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
        String description = meeting.getSubject() + " - " + meeting.getTime() + " - " + meeting.getLocation();
        holder.descriptionText.setText(description);
        holder.participantText.setText(meeting.getParticipants());
        holder.imageButton.setTag(position);
        holder.imageCircle.setColorFilter(meeting.getColor(), android.graphics.PorterDuff.Mode.SRC_IN);
        }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }

    public class MeetingViewHolder extends RecyclerView.ViewHolder{

        public TextView descriptionText, participantText;
        public ImageView imageCircle;
        public ImageButton imageButton;

        public MeetingViewHolder(View view){
            super(view);
            descriptionText = view.findViewById(R.id.text_description);
            participantText = view.findViewById(R.id.text_participant);
            imageCircle = view.findViewById(R.id.image_circle);
            imageButton = view.findViewById(R.id.delete_button);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    mApiService.deleteMeeting(mMeetingList.get(position));
                    notifyDataSetChanged();
                }
            });
        }

    }

}
