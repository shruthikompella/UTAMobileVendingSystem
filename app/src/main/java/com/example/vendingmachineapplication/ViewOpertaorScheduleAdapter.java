package com.example.vendingmachineapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewOpertaorScheduleAdapter extends ArrayAdapter {
    ArrayList<ViewOperatorSchedulePojo>  scheduleArrayList =  new ArrayList<>();

    public ViewOpertaorScheduleAdapter(Context context, int textViewResourceId, ArrayList<ViewOperatorSchedulePojo> objects) {
        super(context, textViewResourceId, objects);
        scheduleArrayList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.operatorschedulelist, null);
        TextView startTime = (TextView) v.findViewById(R.id.startTimeText);
        TextView endTime = (TextView) v.findViewById(R.id.endTimeText);
        TextView locationId = (TextView) v.findViewById(R.id.locationIdText);
        TextView locationIntersection = (TextView) v.findViewById(R.id.locationIntersectionText);
        startTime.setText(scheduleArrayList.get(position).getStartTime());
        endTime.setText(scheduleArrayList.get(position).getEndTime());
        locationId.setText(String.valueOf(scheduleArrayList.get(position).getLocationId()));
        locationIntersection.setText(scheduleArrayList.get(position).getLocationIntersection());

        return v;

    }
}
