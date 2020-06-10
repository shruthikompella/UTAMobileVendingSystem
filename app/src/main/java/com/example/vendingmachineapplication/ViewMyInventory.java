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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ViewMyInventory extends AppCompatActivity {
    Button btn1;
    DatabaseHelper myDb;
    String opname;
    TextView vname,vtype,drinks_r,snacks_r,sandwiches_r,e_time,curr_loc,next_loc,revenue;
    Date d1,d2,d3,d4,endtime;
    ArrayList<Integer> arrlist;
    String y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_inventory);
        btn1 = findViewById(R.id.btnback);
        vname = findViewById(R.id.vmiName);
        vtype = findViewById(R.id.vmiFVT);
        drinks_r = findViewById(R.id.vmiDrinksRem);
        snacks_r = findViewById(R.id.vmiSnacksRem);
        sandwiches_r = findViewById(R.id.vmiSandwichesRem);
        e_time = findViewById(R.id.vmiEndTime);
        curr_loc = findViewById(R.id.vmiCurrentLoc);
        next_loc = findViewById(R.id.vmiNextLoc);
        revenue = findViewById(R.id.vmiRevenue);
        myDb = new DatabaseHelper(this);
        arrlist = new ArrayList<Integer>();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String date= format.format(new Date());
        Bundle bundle = getIntent().getExtras();
        opname = bundle.getString("Name");
        Cursor cursor = myDb.getMyInventory(opname,date);
        while(cursor.moveToNext())
        {
            vname.setText(cursor.getString(0));
            vtype.setText(cursor.getString(1));
            drinks_r.setText(cursor.getString(2));
            snacks_r.setText(cursor.getString(3));
            sandwiches_r.setText(cursor.getString(4));

            revenue.setText("$ "+cursor.getString(7));
            String startTime_todate = cursor.getString(5);
            Log.d("VMI",startTime_todate);
            String endTime_todate = cursor.getString(6);
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
                Log.d("VMI","CHECK 1"+d1.getHours());

                Log.d("VMI","CHECK 2"+d2.getHours());
                Log.d("VMI","CHECK 3"+d3.getHours());
                e_time.setText(cursor.getString(6));
                curr_loc.setText(cursor.getString(8));
                arrlist = new ArrayList<Integer>();
                Cursor cursor1 = myDb.getMyInventory(opname,date);
                while(cursor1.moveToNext())
                {



                    try {
                        endtime = dateFormat.parse(e_time.getText().toString());
                        d4 = dateFormat.parse(cursor1.getString(5));


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(endtime.getHours() <= d4.getHours())
                   {
                        Log.d("ArrayIf","If "+d4.getHours());
                       arrlist.add(d4.getHours());

                   }

                }
                break;
            }
            else{
                e_time.setText("-");
                curr_loc.setText("Currently Not Operating");
                Cursor cursor3 = myDb.getMyInventory(opname,date);
                arrlist = new ArrayList<Integer>();
                while(cursor3.moveToNext())
                {

                    SimpleDateFormat formatter1 = new SimpleDateFormat("HH:mm");
                    String s2 = formatter1.format(new Date());
                    try {
                        endtime = dateFormat.parse(s2);
                        d4 = dateFormat.parse(cursor3.getString(5));


                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(endtime.getHours() <= d4.getHours())
                    {
                        Log.d("ArrayElse","Else "+d4.getHours());
                        arrlist.add(d4.getHours());

                    }

                }


            }
        }

        if(arrlist.size() != 0) {
            Log.d("List Size","List size is"+arrlist.size());
        Collections.sort(arrlist);

            Integer x = arrlist.get(0);
            Log.d("Int", arrlist.get(0).toString());

            if (x < 10) {
                y = "0" + x + ":00";
            } else {
                y = x + ":00";
            }


            Cursor cursor2 = myDb.getMyInventory(opname,date);
            while (cursor2.moveToNext()) {
                if (cursor2.getString(5).equals(y)) {
                    Log.d("Y","Y is" + y);
                    next_loc.setText(cursor2.getString(8));
                }
            }
        }
        else{
            next_loc.setText("No Next Location");
        }


        onclick();
    }

    public void onclick(){
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(ViewMyInventory.this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }
    /*public void onHome(View view)
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


    }*/
}
