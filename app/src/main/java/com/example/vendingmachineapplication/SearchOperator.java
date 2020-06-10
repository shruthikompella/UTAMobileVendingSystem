package com.example.vendingmachineapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SearchOperator extends AppCompatActivity {

    ArrayList<Operator> operatorArrayList = new ArrayList<>();
    ListView simpleList;
    EditText elname;
    Button searchOpBtn, viewOpDetailsBtn,backBtn;
    LinearLayout mainlayout;
    DatabaseHelper myDb;
    String homeUserName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.searchoperator);
        mainlayout = (LinearLayout) this.findViewById(R.id.headingLayout);
        mainlayout.setVisibility(LinearLayout.GONE);
        elname = findViewById(R.id.lastNameEdit);
        backBtn = findViewById(R.id.soBackBtn);
        myDb = new DatabaseHelper(SearchOperator.this);
        searchOpBtn = (Button) findViewById(R.id.searchOperatorBtn);
        simpleList = (ListView) findViewById(R.id.operatorList);
        Bundle bundle = getIntent().getExtras();
        homeUserName = bundle.getString("Name");


        searchOpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainlayout.setVisibility(LinearLayout.VISIBLE);
                String lname = elname.getText().toString();
                Cursor res =  myDb.getAllData();
                if(res.getCount() == 0) {
                    Toast.makeText(SearchOperator.this, "Nothing Found", Toast.LENGTH_SHORT).show();
                    return;
                }
                while (res.moveToNext()) {

                    if (res.getString(1).equalsIgnoreCase(lname) && res.getString(10).equals("operator")) {
                        operatorArrayList.add(new Operator(res.getString(2), res.getString(0), res.getString(1)));

                    }
                }


                //operatorArrayList.add(new Operator("sam990", "Sam", "Smith"));
               /* operatorArrayList.add(new Operator("manrey564", "Manrey", "Thomas"));
                operatorArrayList.add(new Operator("michael938", "Michael", "Stark"));*/
                SearchOperatorAdapter myAdapter = new SearchOperatorAdapter(getApplicationContext(), R.layout.operatorlist, operatorArrayList);
                simpleList.setAdapter(myAdapter);
                //Toast.makeText(getApplicationContext(), "Total number of Items are:" + simpleList.getAdapter().getCount() , Toast.LENGTH_LONG).show();
                simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    //Toast.makeText(SearchOperator.this, val, Toast.LENGTH_SHORT).show();
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        int itemPos = position;
                        Operator val = (Operator) simpleList.getItemAtPosition(position);
                        Intent myIntent = new Intent(getBaseContext(), ViewOperatorDetails.class);
                        myIntent.putExtra("Name",val.getUname());
                        myIntent.putExtra("U_name",homeUserName);

                        startActivity(myIntent);
                        //Toast.makeText(SearchOperator.this, val.getFirstname(), Toast.LENGTH_SHORT).show();

                        //Log.d("Pos",simpleList.getItemAtPosition(position).toString());
                    }
                });




                //tvw.setText("Selected Date: "+ eText.getText());
            }

        });

    }
/*    public void onSearchOperator(View view){
        String lname = elname.getText().toString();
        Cursor res =  myDb.getAllData();

        while (res.moveToNext()) {

            if (res.getString(1).equals(lname)) {
                Toast.makeText(this, lname, Toast.LENGTH_SHORT).show();
                Log.d("OPERATOR",res.getString(1) );

            }
        }
    }*/
        public void onBack(View view){
            onBackPressed();


    }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(SearchOperator.this, "Logged out", Toast.LENGTH_SHORT).show();
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
