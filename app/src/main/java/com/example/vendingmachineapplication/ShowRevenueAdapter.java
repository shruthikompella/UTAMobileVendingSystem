package com.example.vendingmachineapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ShowRevenueAdapter extends ArrayAdapter<ShowRevenuePojo> {


    ArrayList<ShowRevenuePojo> revenueArrayList = new ArrayList<>();

    public ShowRevenueAdapter(Context context, int textViewResourceId, ArrayList<ShowRevenuePojo> objects) {
        super(context, textViewResourceId, objects);
        revenueArrayList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.revenuelist, null);
        TextView name = (TextView) v.findViewById(R.id.vName);
        TextView revenue = (TextView) v.findViewById(R.id.vRevenue);

        name.setText(revenueArrayList.get(position).getVehicleName());
        revenue.setText("$" + String.valueOf(revenueArrayList.get(position).getRevenue()));


        return v;

    }
}
