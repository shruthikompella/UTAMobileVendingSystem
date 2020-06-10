package com.example.vendingmachineapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckoutItems extends AppCompatActivity {
    private Button backBtn;
    DatabaseHelper myDb;
    String vname,uname,type,loc_inter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkoutitems);
        backBtn = findViewById(R.id.upBackBtn);
        myDb = new DatabaseHelper(CheckoutItems.this);
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        vname = bundle.getString("V_Name");
        uname = bundle.getString("U_Name");
        Log.d("U_Name",uname);
        type = bundle.getString("Type");
        loc_inter = bundle.getString("Location");
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Okay!!!",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogue,int id) {
                        Toast.makeText(CheckoutItems.this,"Going back to Home page",Toast.LENGTH_LONG).show();
                        Intent myIntent = new Intent(getBaseContext(), UserHome.class);
                        myIntent.putExtra("Name",uname);
                        startActivity(myIntent);
                    }
                });

        builder.show();


    }
    public void onBack(View view){
        onBackPressed();
    }
    public void onPayClick(View view){

        Cursor res = myDb.getCart();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String s1 = formatter.format(new Date());
        SimpleDateFormat formatter_date = new SimpleDateFormat("MM/dd/yyyy");
        String s2 = formatter_date.format(new Date());
        while (res.moveToNext()) {
            double dtax = 0.0825 * res.getDouble(3);
            double dtotal = dtax + res.getDouble(3);
            boolean isInserted = myDb.insertOrder(uname,s2, s1, vname,type, loc_inter, res.getInt(0), res.getInt(1), res.getInt(2), res.getDouble(3), dtotal);
            Log.d("VM", Boolean.toString(isInserted));
            if (isInserted == true) {
                Cursor result_update = myDb.updateInventory(vname, res.getInt(0),res.getInt(1),res.getInt(2)) ;
                Cursor add_revenue = myDb.updateRevenue(s2,vname,dtotal);
                Log.d("UPDATE_CHECKOUT", ""+add_revenue);
                Log.d("UPDATE_CHECKOUT", ""+result_update);
                //Toast.makeText(CheckoutItems.this, "Order Successful", Toast.LENGTH_LONG).show();
                Cursor result = myDb.fetchOrder(uname);
                while (result.moveToNext()) {
                    showMessage("Success", "Your Transaction successful \nThe confirmation # is " + result.getString(0));
                }

            }
        }


    }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(CheckoutItems.this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }
    public void onHome(View view)
    {
        myDb = new DatabaseHelper(this);
        HomeRedirect hm = new HomeRedirect();
        int temp = hm.homeredirect(uname,myDb);
        if(temp == 1) {

            Intent myIntent = new Intent(getBaseContext(), ManagerHome.class);
            myIntent.putExtra("Name",uname);
            startActivity(myIntent);
        }
        else if(temp == 2){
            Intent myIntent = new Intent(getBaseContext(), OperatorHome.class);
            myIntent.putExtra("Name",uname);
            startActivity(myIntent);
        }
        else if(temp == 3){
            Intent myIntent = new Intent(getBaseContext(), UserHome.class);
            myIntent.putExtra("Name",uname);
            startActivity(myIntent);
        }


    }

}
