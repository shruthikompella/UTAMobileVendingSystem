package com.example.vendingmachineapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ViewCartAdapter extends ArrayAdapter {

    ArrayList<ViewCartPojo> cartArrayList = new ArrayList<>();

    public ViewCartAdapter(Context context, int textViewResourceId, ArrayList<ViewCartPojo> objects) {
        super(context, textViewResourceId, objects);
        cartArrayList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.cartlist, null);
        TextView itemType = (TextView) v.findViewById(R.id.iTypeText);
        TextView itemCost = (TextView) v.findViewById(R.id.iCostText);
        TextView itemQuantity = (TextView) v.findViewById(R.id.iQuantityText);
        TextView cost = (TextView) v.findViewById(R.id.costText);
        itemType.setText(cartArrayList.get(position).getItemType());
        itemCost.setText(cartArrayList.get(position).getItemcost());
        itemQuantity.setText(String.valueOf(cartArrayList.get(position).getItemQuantity()));
        cost.setText("$"+String.valueOf(cartArrayList.get(position).getCost()));

        return v;

    }
}
