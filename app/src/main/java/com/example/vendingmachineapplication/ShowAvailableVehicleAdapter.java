package com.example.vendingmachineapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ShowAvailableVehicleAdapter extends ArrayAdapter<AvailableVehicle> {

    ArrayList<AvailableVehicle> vehicleArrayList = new ArrayList<>();

    public ShowAvailableVehicleAdapter(Context context, int textViewResourceId, ArrayList<AvailableVehicle> objects) {
        super(context, textViewResourceId, objects);
        vehicleArrayList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.availablevehiclelist, null);
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView type = (TextView) v.findViewById(R.id.type);
        TextView date = (TextView) v.findViewById(R.id.date);
        name.setText(vehicleArrayList.get(position).getName());
        type.setText(vehicleArrayList.get(position).getType());
        date.setText(vehicleArrayList.get(position).getDate());
        //SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
        //date.setText(sdf.format(vehicleArrayList.get(position).getDate()));

        return v;

    }
}
