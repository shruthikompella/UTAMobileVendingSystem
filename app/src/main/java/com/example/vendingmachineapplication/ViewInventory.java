    package com.example.vendingmachineapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

    public class ViewInventory extends AppCompatActivity {
    DatabaseHelper myDb;
    ListView simpleList;
    Date d1,d2,d3,d4,endtime;
    ArrayList<Integer> arrlist;
    String y;
    String homeUserName;
    String tname,ttype,tdrinks,tsnacks,tsandwiches,tstarttime,tendtime,trevenue,tcurrloc,tnextloc,topname;
    ArrayList<ViewInventoryPojo> InventoryArrayList=new ArrayList<>();
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inventory);
        myDb = new DatabaseHelper(this);
        btnBack = findViewById(R.id.btnback);
        simpleList = (ListView) findViewById(R.id.ShowInventoryList);
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String date= format.format(new Date());
        Cursor cursor = myDb.getDistinctVehiclesFromSchedule(date);
        arrlist = new ArrayList<Integer>();
        while(cursor.moveToNext()) {
            Cursor cursor1 = myDb.getInventory(cursor.getString(0),date);
            while(cursor1.moveToNext()) {
                tname = cursor1.getString(0);
                ttype = cursor1.getString(1);
                tdrinks= cursor1.getString(2);
                tsnacks = cursor1.getString(3);
                tsandwiches = cursor1.getString(4);

                trevenue = cursor1.getString(7);
                topname = cursor1.getString(9);
                String startTime_todate = cursor1.getString(5);
                Log.d("VMI",startTime_todate);
                String endTime_todate = cursor1.getString(6);
                Log.d("VMI",endTime_todate);
                DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                try {
                    d2 = dateFormat.parse(startTime_todate);
                    d3 = dateFormat.parse(endTime_todate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                String s1 = formatter.format(new Date());
                try {
                    d1 = dateFormat.parse(s1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(d1.getHours() >= d2.getHours() && d1.getHours() < d3.getHours()){
                    Log.d("VMI","CHECK");
                    tendtime = cursor1.getString(6);
                    tcurrloc = cursor1.getString(8);
                    arrlist = new ArrayList<Integer>();

                    Cursor cursor2 = myDb.getInventory(cursor.getString(0),date);
                    while(cursor2.moveToNext())
                    {

                        try {
                            endtime = dateFormat.parse(tendtime);
                            d4 = dateFormat.parse(cursor2.getString(5));


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if(endtime.getHours() <= d4.getHours())
                        {
                            Log.d("Array","hello");
                            arrlist.add(d4.getHours());

                        }

                    }
                    break;
                }
                else{
                    tendtime = "-";
                    tcurrloc = "Currently Not Operating";
                    arrlist = new ArrayList<Integer>();
                    Cursor cursor2 = myDb.getInventory(cursor.getString(0),date);
                    while(cursor2.moveToNext())
                    {

                        SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm");
                        String s2 = formatter1.format(new Date());
                        try {
                            endtime = dateFormat.parse(s2);
                            d4 = dateFormat.parse(cursor2.getString(5));


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        if(endtime.getHours() <= d4.getHours())
                        {
                            Log.d("Array","hello");
                            arrlist.add(d4.getHours());

                        }

                    }
                }
            }
            if(arrlist.size() != 0) {
            Collections.sort(arrlist);

                Integer x = arrlist.get(0);
                Log.d("Int", arrlist.get(0).toString());

                if (x < 10) {
                    y = "0" + x + ":00";
                } else {
                    y = x + ":00";
                }

                Cursor cursor3 = myDb.getInventory(cursor.getString(0),date);
                while (cursor3.moveToNext()) {
                    if (cursor3.getString(5).equals(y)) {
                        tnextloc = cursor3.getString(8);
                    }
                }
            }
            else {
                tnextloc = "No Next Location";
            }


            InventoryArrayList.add(new ViewInventoryPojo(tname, ttype, tdrinks, tsnacks, tsandwiches, tendtime, tcurrloc, tnextloc, trevenue, topname));
        }
        ViewInventoryAdapter myAdapter=new ViewInventoryAdapter(getApplicationContext(),R.layout.viewinventorylist,InventoryArrayList);
        simpleList.setAdapter(myAdapter);

    }

    public void onBack(View view){
        onBackPressed();
    }
        public void onLogout(View view){
            Intent myIntent = new Intent(getBaseContext(), Welcome.class);
            Toast.makeText(ViewInventory.this, "Logged out", Toast.LENGTH_SHORT).show();
            startActivity(myIntent);
        }
        public void onHome(View view)
        {
            myDb = new DatabaseHelper(this);
            HomeRedirect hm = new HomeRedirect();
            int temp = hm.homeredirect(homeUserName,myDb);
            if(temp == 1) {

                Intent myIntent = new Intent(getBaseContext(), ManagerHome.class);
                myIntent.putExtra("Name",homeUserName);
                startActivity(myIntent);
            }
            else if(temp == 2){
                Intent myIntent = new Intent(getBaseContext(), OperatorHome.class);
                myIntent.putExtra("Name",homeUserName);
                startActivity(myIntent);
            }
            else if(temp == 3){
                Intent myIntent = new Intent(getBaseContext(), UserHome.class);
                myIntent.putExtra("Name",homeUserName);
                startActivity(myIntent);
            }


        }



    }
