package com.example.vendingmachineapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchVehicleAdapater extends ArrayAdapter<SearchVehiclePojo> {


    ArrayList<SearchVehiclePojo> InventoryArrayList = new ArrayList<>();

    public SearchVehicleAdapater(Context context, int textViewResourceId, ArrayList<SearchVehiclePojo> objects) {
        super(context, textViewResourceId,objects);
        InventoryArrayList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.searchvehiclelist, null);
        TextView name = (TextView) v.findViewById(R.id.search_name);
        TextView type = (TextView) v.findViewById(R.id.search_type);
        TextView id = (TextView) v.findViewById(R.id.search_loc_id);
        TextView intersection = (TextView) v.findViewById(R.id.search_loc_inter);

        name.setText(InventoryArrayList.get(position).getName());
        type.setText(InventoryArrayList.get(position).getType());
        id.setText(InventoryArrayList.get(position).getId());
        intersection.setText(InventoryArrayList.get(position).getIntersection());


        return v;

    }
}
