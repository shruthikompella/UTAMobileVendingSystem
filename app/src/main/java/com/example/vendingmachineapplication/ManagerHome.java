package com.example.vendingmachineapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManagerHome extends AppCompatActivity {

    private TextView displayName;
    Button searchOp,ShowAvailableVehicle;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managerhome);


        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("Name");
        //Extract the data…


        searchOp = findViewById(R.id.searchOperatorManager);
        displayName = (TextView)findViewById(R.id.displayName);
        displayName.setText("Hello Manager, "+name.toUpperCase());

    }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(ManagerHome.this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }
    public void onShowAvailableVehicleClick(View view){
        Intent myIntent = new Intent(getBaseContext(), ShowAvailableVehicle.class);
        myIntent.putExtra("Name",name);
        startActivity(myIntent);
    }

    public void onViewProfile(View view){
        Intent myIntent = new Intent(getBaseContext(), ViewProfile.class);
        myIntent.putExtra("Name",name);
        startActivity(myIntent);
    }
    public void onSerachOperator(View view){
        Intent myIntent = new Intent(getBaseContext(), SearchOperator.class);
        myIntent.putExtra("Name",name);
        startActivity(myIntent);
    }
    public void onViewInventory(View view){
        Intent myIntent = new Intent(getBaseContext(), ViewInventory.class);
        myIntent.putExtra("Name",name);
        startActivity(myIntent);
    }
    public void onSearchVehicle(View view)
    {
        Intent myIntent = new Intent(getBaseContext(), SearchVehicle.class);
        myIntent.putExtra("Name",name);
        startActivity(myIntent);
    }

    public void onShowRevenueClick(View view){
        Intent myIntent = new Intent(getBaseContext(), ShowRevenue.class);
        myIntent.putExtra("Name",name);
        startActivity(myIntent);
    }
}
