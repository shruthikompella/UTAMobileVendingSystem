package com.example.vendingmachineapplication;

import android.content.Intent;
import android.database.Cursor;
import android.media.MediaRouter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ViewOperatorSchedule  extends AppCompatActivity {

    ArrayList<ViewOperatorSchedulePojo> scheduleArrayList = new ArrayList<>();
    ListView simpleList;
    Button btnback;
    String name;
    DatabaseHelper myDb;
    String vname, type,date;
    TextView vehicleNameId,FoodVendingTypeId, dateId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewoperatorschedule);
        myDb = new DatabaseHelper(ViewOperatorSchedule.this);

        btnback = findViewById(R.id.btnBack);
        vehicleNameId = findViewById(R.id.vehicleNameId);
        FoodVendingTypeId = findViewById(R.id.FoodVendingTypeId);
        dateId = findViewById(R.id.dateId);

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        name = bundle.getString("Name");


        //scheduleArrayList.add(new ViewOperatorSchedulePojo("9:00AM", "11:00AM", "Location 1", "Cooper & UTA Blvd"));
        //scheduleArrayList.add(new ViewOperatorSchedulePojo("10:00AM", "01:00PM", "Location 4", "Cooper & W Mitchell"));
        //scheduleArrayList.add(new ViewOperatorSchedulePojo("03:00PM", "04:00PM", "Location 8", "S Center & W Mitchell"));


        String currentDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        Log.d("Time","TIME : "+currentDate);

        Cursor res = myDb.viewSpecificSchedule(name,currentDate);
        while (res.moveToNext()) {
            scheduleArrayList.add(new ViewOperatorSchedulePojo(res.getString(3), res.getString(4), res.getString(5), res.getString(6)));
            Log.d("Data",res.getString(3)+" "+res.getString(4)+" "+res.getString(5));
            vname = res.getString(0);
            type = res.getString(1);
            date = res.getString(2);

        }


        simpleList = (ListView) findViewById(R.id.scheduleList);
        ViewOpertaorScheduleAdapter myAdapter = new ViewOpertaorScheduleAdapter(getApplicationContext(), R.layout.operatorschedulelist, scheduleArrayList);
        simpleList.setAdapter(myAdapter);
        FoodVendingTypeId.setText(type);
        dateId.setText(date);
        vehicleNameId.setText(vname);

    }


    public void onBtnBack(View view){
        onBackPressed();
    }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(ViewOperatorSchedule.this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }
    public void onHome(View view)
    {
        myDb = new DatabaseHelper(this);
        HomeRedirect hm = new HomeRedirect();
        int temp = hm.homeredirect(name,myDb);
        if(temp == 1) {

            Intent myIntent = new Intent(getBaseContext(), ManagerHome.class);
            myIntent.putExtra("Name",name);
            startActivity(myIntent);
        }
        else if(temp == 2){
            Intent myIntent = new Intent(getBaseContext(), OperatorHome.class);
            myIntent.putExtra("Name",name);
            startActivity(myIntent);
        }
        else if(temp == 3){
            Intent myIntent = new Intent(getBaseContext(), UserHome.class);
            myIntent.putExtra("Name",name);
            startActivity(myIntent);
        }


    }
}

