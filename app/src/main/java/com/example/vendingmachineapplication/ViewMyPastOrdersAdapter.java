package com.example.vendingmachineapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewMyPastOrdersAdapter extends ArrayAdapter<ViewMyPastOrdersPojo> {
    ArrayList<ViewMyPastOrdersPojo> PastOrderList = new ArrayList<>();

    public ViewMyPastOrdersAdapter(Context context, int textViewResourceId, ArrayList<ViewMyPastOrdersPojo> objects) {
        super(context, textViewResourceId,objects);
        PastOrderList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.viewmypastorderslist, null);
        TextView date = (TextView) v.findViewById(R.id.tv_date);
        TextView time = (TextView) v.findViewById(R.id.tv_time);
        TextView confirmation_number = (TextView) v.findViewById(R.id.tv_confirmation);
        date.setText(PastOrderList.get(position).getDate());
        time.setText(PastOrderList.get(position).getTime());
        confirmation_number.setText(PastOrderList.get(position).getConfirmation());


        return v;

    }

}
