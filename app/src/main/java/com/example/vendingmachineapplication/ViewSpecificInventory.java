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

public class ViewSpecificInventory extends AppCompatActivity {
    Button b1;
    DatabaseHelper myDb;
    String name,date,stime,etime,fvtype,locinter,username;
    TextView vname,vtype,drinks_r,snacks_r,sandwiches_r,e_time,curr_loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_specific_inventory);
        b1 = findViewById(R.id.btnbuy);
        vname = findViewById(R.id.vsiName);
        vtype = findViewById(R.id.vsiFVT);
        drinks_r = findViewById(R.id.vsiDrinksRem);
        snacks_r = findViewById(R.id.vsiSnacksRem);
        sandwiches_r = findViewById(R.id.vsiSandwichesRem);
        e_time = findViewById(R.id.vsiEndTime);
        curr_loc= findViewById(R.id.vsiCurrentLoc);

        myDb = new DatabaseHelper(this);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("Name");
        date = bundle.getString("Date");
        stime = bundle.getString("Start_time");
        etime = bundle.getString("End_time");
        fvtype= bundle.getString("FVType");
        locinter = bundle.getString("LocationInter");
        username = bundle.getString("USER_NAME");
        Cursor res = myDb.getAllData();
        while(res.moveToNext()){
            if(res.getString(2).equals(username) && res.getString(10).equals("manager")){
                b1.setVisibility(View.GONE);
                Log.d("Found",res.getString(2));
                break;
            }
        }
        Log.d("User_Name",username);
        Cursor cursor = myDb.getInventoryData(name);

        while(cursor.moveToNext())
        {
            vname.setText(name);
            vtype.setText(fvtype);
            drinks_r.setText(cursor.getString(0));
            snacks_r.setText(cursor.getString(1));
            sandwiches_r.setText(cursor.getString(2));
            e_time.setText(etime);
            curr_loc.setText(locinter);



        }




    }
   public void onBack(View view){
        onBackPressed();
   }


    public void onBuyItemsClick(View view){
        Intent myIntent = new Intent(getBaseContext(), BuyItems.class);
        myIntent.putExtra("V_Name",name);
        myIntent.putExtra("Type",fvtype);
        myIntent.putExtra("Location",locinter);
        myIntent.putExtra("U_Name",username);
        myIntent.putExtra("Drinks_R",drinks_r.getText().toString());
        myIntent.putExtra("Sandwiches_R",sandwiches_r.getText().toString());
        myIntent.putExtra("Snacks_R", snacks_r.getText().toString());

        Log.d("Testing",username);
        startActivity(myIntent);
    }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(ViewSpecificInventory.this, "Logged out", Toast.LENGTH_SHORT).show();
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

}
