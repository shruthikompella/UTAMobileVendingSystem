package com.example.vendingmachineapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UserHome extends AppCompatActivity {
    private TextView displayName;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userhome);

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        name = bundle.getString("Name");
        displayName = (TextView)findViewById(R.id.displayName);
        displayName.setText("Hello User, "+name.toUpperCase());

    }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(UserHome.this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }

    public void onViewProfile(View view){
        Intent myIntent = new Intent(getBaseContext(), ViewProfile.class);
        myIntent.putExtra("Name",name);
        startActivity(myIntent);
    }

    public void onSearchVehicle(View view)
    {
        Intent myIntent = new Intent(getBaseContext(), SearchVehicle.class);
        myIntent.putExtra("Name",name);
        startActivity(myIntent);
    }

    public void onMyPastOrderClick(View view){
        Intent myIntent = new Intent(getBaseContext(), ViewMyPastOrders.class);
        myIntent.putExtra("Name",name);
        startActivity(myIntent);

    }
}