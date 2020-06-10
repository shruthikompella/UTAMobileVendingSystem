package com.example.vendingmachineapplication;

import android.content.Context;
import android.graphics.Path;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class SearchOperatorAdapter extends ArrayAdapter<Operator> {

    ArrayList<Operator> operatorArrayList = new ArrayList<>();

    public SearchOperatorAdapter(Context context, int textViewResourceId, ArrayList<Operator> objects) {
        super(context, textViewResourceId, objects);
        operatorArrayList = objects;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.operatorlist, null);
        TextView uname = (TextView) v.findViewById(R.id.uNameText);
        TextView fname = (TextView) v.findViewById(R.id.fNameText);
        TextView lname = (TextView) v.findViewById(R.id.lNameText);
        uname.setText(operatorArrayList.get(position).getUname());
        fname.setText(operatorArrayList.get(position).getFirstname());
        lname.setText(operatorArrayList.get(position).getLastName());

        return v;

    }
}
