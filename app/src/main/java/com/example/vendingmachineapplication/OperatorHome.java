package com.example.vendingmachineapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OperatorHome extends AppCompatActivity {

    private TextView displayName;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operatorhome);


        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        name = bundle.getString("Name");
        displayName = (TextView)findViewById(R.id.displayName);
        displayName.setText("Hello Operator, "+name.toUpperCase());

    }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(OperatorHome.this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }

    public void onViewProfile(View view){
        Intent myIntent = new Intent(getBaseContext(), ViewProfile.class);
        myIntent.putExtra("Name",name);
        startActivity(myIntent);
    }


    public void OnviewSpecificScheduleClick(View view){
        Intent myIntent = new Intent(getBaseContext(), ViewOperatorSchedule.class);
        myIntent.putExtra("Name",name);
        startActivity(myIntent);
    }
    public void onViewMyInventory(View view){
        Intent myIntent = new Intent(getBaseContext(), ViewMyInventory.class);
        myIntent.putExtra("Name",name);
        startActivity(myIntent);
    }



    public void ViewPastOrdersClick(View view){
        Intent myIntent = new Intent(getBaseContext(), ViewPastOrders.class);
        myIntent.putExtra("Name",name);
        startActivity(myIntent);
    }
}
