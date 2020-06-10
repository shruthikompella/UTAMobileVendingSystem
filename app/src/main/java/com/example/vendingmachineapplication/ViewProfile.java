package com.example.vendingmachineapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewProfile extends AppCompatActivity {

    DatabaseHelper myDb;
    String name;
    private TextView uname,fname,lname,phone,email,street,city,state,zip,role;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.viewprofile);
         uname = findViewById(R.id.viewUserName);
         fname = findViewById(R.id.viewFirstName);
         lname = findViewById(R.id.viewLastName);
         phone = findViewById(R.id.viewPhone);
         email = findViewById(R.id.viewEmail);
         street = findViewById(R.id.viewStreet);
         city = findViewById(R.id.viewCity);
         state = findViewById(R.id.viewState);
         zip = findViewById(R.id.viewZip);
         role = findViewById(R.id.viewRole);


         myDb = new DatabaseHelper(this);


         Bundle bundle = getIntent().getExtras();
         name = bundle.getString("Name");
         Log.d("Profile", name);

         Cursor res = myDb.getAllData();
         while (res.moveToNext()) {

             if (res.getString(2).equalsIgnoreCase(name)) {
                 Log.d("Profile", res.getString(7));
                 uname.setText(res.getString(2));
                 fname.setText(res.getString(0));
                 lname.setText(res.getString(1));
                 phone.setText(res.getString(4));
                 email.setText(res.getString(5));
                 street.setText(res.getString(6));
                 city.setText(res.getString(7));
                 state.setText(res.getString(8));
                 zip.setText(String.valueOf((res.getInt(9))));
                 role.setText(res.getString(10));
             }
         }
     }
         public void onBack(View view){
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
        public void onUpdateClick(View view){
            Intent myIntent = new Intent(getBaseContext(), EditProfile.class);
            myIntent.putExtra("Name",name);
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
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(ViewProfile.this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }
}
