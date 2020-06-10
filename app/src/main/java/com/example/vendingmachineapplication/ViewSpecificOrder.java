package com.example.vendingmachineapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewSpecificOrder extends AppCompatActivity {
    Button btnback;
    DatabaseHelper myDb;
    String conf,homeUserName;
    TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_specific_order);
        btnback = findViewById(R.id.btnback);
        tv1 = findViewById(R.id.viewConfNum);
        tv2 = findViewById(R.id.viewDate);
        tv3 = findViewById(R.id.viewTime);
        tv4 = findViewById(R.id.viewName);
        tv5 = findViewById(R.id.viewType);
        tv6 = findViewById(R.id.viewLocInter);
        tv7 = findViewById(R.id.viewNoDrinks);
        tv8 = findViewById(R.id.viewNoSandwiches);
        tv9 = findViewById(R.id.viewNoSnacks);
        tv10 = findViewById(R.id.viewTotal);

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        conf = bundle.getString("Number");
        homeUserName = bundle.getString("U_name");
        myDb = new DatabaseHelper(this);
        Cursor res = myDb.fetchSpecificOrder(Integer.parseInt(conf));

        while (res.moveToNext()){
            tv1.setText(res.getString(0));
            tv2.setText(res.getString(2));
            tv3.setText(res.getString(3));
            tv4.setText(res.getString(4));
            tv5.setText(res.getString(6));
            tv6.setText(res.getString(5));
            tv7.setText(res.getString(7));
            tv8.setText(res.getString(8));
            tv9.setText(res.getString(9));
            tv10.setText( "$ "+res.getString(11));

        }


    }
    public void onBack(View view){
        onBackPressed();
    }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(ViewSpecificOrder.this, "Logged out", Toast.LENGTH_SHORT).show();
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
