package com.example.vendingmachineapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewInventoryAdapter extends ArrayAdapter<ViewInventoryPojo> {

    ArrayList<ViewInventoryPojo> InventoryArrayList = new ArrayList<>();

    public ViewInventoryAdapter(Context context, int textViewResourceId, ArrayList<ViewInventoryPojo> objects) {
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
        v = inflater.inflate(R.layout.viewinventorylist, null);
        TextView name = (TextView) v.findViewById(R.id.tvname_viewinven);
        TextView type = (TextView) v.findViewById(R.id.tvtype_viewinven);
        TextView op_name = (TextView) v.findViewById(R.id.tvoperator_viewinven);
        TextView end_time = (TextView) v.findViewById(R.id.tvend_viewinven);
        TextView curr_loc = (TextView) v.findViewById(R.id.tvcurrentloc_viewinven);
        TextView next_loc = (TextView) v.findViewById(R.id.tvnextloc_viewinven);
        TextView revenue = (TextView) v.findViewById(R.id.tvrevenue_viewinven);
        TextView sanwiches = (TextView) v.findViewById(R.id.num_sandwiches);
        TextView snacks = (TextView) v.findViewById(R.id.num_snacks);
        TextView drinks = (TextView) v.findViewById(R.id.num_drinks);
        name.setText(InventoryArrayList.get(position).getName());
        type.setText(InventoryArrayList.get(position).getType());
        op_name.setText(InventoryArrayList.get(position).getOp_name());
        end_time.setText(InventoryArrayList.get(position).getEndtime());
        curr_loc.setText(InventoryArrayList.get(position).getCurrent_loc());
        next_loc.setText(InventoryArrayList.get(position).getNext_loc());
        revenue.setText(InventoryArrayList.get(position).getRevenue());
        sanwiches.setText(InventoryArrayList.get(position).getSandwiches());
        snacks.setText(InventoryArrayList.get(position).getSnacks());
        drinks.setText(InventoryArrayList.get(position).getDrinks());

        return v;

    }
}
