package com.example.vendingmachineapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewSpecificVehicle extends AppCompatActivity {
   // private Button btnBack;
    DatabaseHelper myDb;
    String name,date,stime,etime,endtime,type,loc,username;
    private TextView tvname,tvtype,tvdate,tvstime,tvetime,tvid,tvlocint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_specific_vehicle);
        //btnBack = findViewById(R.id.btnback);
        tvname = findViewById(R.id.vsvName);
        tvtype = findViewById(R.id.vsvType);
        tvdate = findViewById(R.id.vsvDate);
        tvstime = findViewById(R.id.vsvStime);
        tvetime = findViewById(R.id.vsvEtime);
        tvid = findViewById(R.id.vsvLid);
        tvlocint = findViewById(R.id.vsvLinter);

        myDb = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("Name");
        date = bundle.getString("Date");
        stime = bundle.getString("Start_time");
        etime = bundle.getString("End_time");
        username = bundle.getString("USER_NAME");
        Log.d("U_Name",username);


        Cursor res = myDb.getVehicleData();
        while (res.moveToNext()) {

            if (res.getString(0).equals(name)&&res.getString(4).equals(date)&&res.getString(5).equals(stime)&&res.getString(6).equals(etime)) {
                tvname.setText(res.getString(0));
                tvtype.setText(res.getString(1));
                tvdate.setText(res.getString(4));
                tvstime.setText(res.getString(5));
                tvetime.setText(res.getString(6));
                tvid.setText(res.getString(2));
                tvlocint.setText(res.getString(3));
                type = res.getString(1);
                loc = res.getString(3);
                endtime = res.getString(6);
            }
        }
        /*Log.d("Profile", name);
        Log.d("Profile", date);
        Log.d("Profile", stime);
        Log.d("Profile", etime);*/

    }
    public void onBack(View view){
        onBackPressed();
    }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(ViewSpecificVehicle.this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }

    public void onHome(View view)
    {
        myDb = new DatabaseHelper(this);
        HomeRedirect hm = new HomeRedirect();
        int temp = hm.homeredirect(username,myDb);
        if(temp == 1) {

            Intent myIntent = new Intent(getBaseContext(), ManagerHome.class);
            myIntent.putExtra("Name",username);
            startActivity(myIntent);
        }
        else if(temp == 2){
            Intent myIntent = new Intent(getBaseContext(), OperatorHome.class);
            myIntent.putExtra("Name",username);
            startActivity(myIntent);
        }
        else if(temp == 3){
            Intent myIntent = new Intent(getBaseContext(), UserHome.class);
            myIntent.putExtra("Name",username);
            startActivity(myIntent);
        }


    }

    public void onViewSpecificVehicle(View v)
    {
        Intent myIntent = new Intent(getBaseContext(), ViewSpecificInventory.class);
        myIntent.putExtra("Name",name);
        myIntent.putExtra("Date",date);
        myIntent.putExtra("Start_time",stime);
        myIntent.putExtra("End_time",endtime);
        myIntent.putExtra("FVType",type);
        myIntent.putExtra("LocationInter",loc);
        myIntent.putExtra("USER_NAME",username);
        Log.d("U_Name",username);

        startActivity(myIntent);
    }









































































































































}
