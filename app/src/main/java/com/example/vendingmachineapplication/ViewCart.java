package com.example.vendingmachineapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ViewCart extends AppCompatActivity {
    int minteger = 0;
    ListView simpleList;
    Button btnback;
    DatabaseHelper myDb;
    TextView tvdrinks,tvsnacks,tvsandwiches,subtotal,tax, total;
    double dtax,dtotal;
    String vname, username,type,loc_inter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcart);
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        vname = bundle.getString("V_Name");
        username = bundle.getString("U_Name");
        type = bundle.getString("Type");
        loc_inter = bundle.getString("Location");
        Log.d("U_Name",username);

        myDb = new DatabaseHelper(ViewCart.this);
        tvdrinks = findViewById(R.id.viewDrinks);
        tvsandwiches = findViewById(R.id.viewSandwiches);
        tvsnacks = findViewById(R.id.viewSnacks);
        subtotal = findViewById(R.id.viewSubTotal);
        tax = findViewById(R.id.viewTax);
        total = findViewById(R.id.viewTotal);

        Cursor res = myDb.getCart();
        while(res.moveToNext()) {


            //Toast.makeText(ViewCart.this, res.getString(0), Toast.LENGTH_SHORT).show();
            tvdrinks.setText(res.getString(0));
            tvsandwiches.setText(res.getString(1));
            tvsnacks.setText(res.getString(2));
            subtotal.setText("$ " + res.getString(3));
            dtax = 0.0825 * res.getDouble(3);
            dtotal = dtax + res.getDouble(3);
            tax.setText("$ " + String.format("%.2f", dtax));
            total.setText("$ " + String.format("%.2f", dtotal));

        }



    }
    public void onBack(View view){
        onBackPressed();
    }

    public void onCheckOutClick(View view) {
        Intent myIntent = new Intent(getBaseContext(), CheckoutItems.class);
        myIntent.putExtra("V_Name",vname);
        myIntent.putExtra("U_Name",username);
        myIntent.putExtra("Type",type);
        myIntent.putExtra("Location",loc_inter);
        Log.d("U_Name",username);

        startActivity(myIntent);



    }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(ViewCart.this, "Logged out", Toast.LENGTH_SHORT).show();
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
