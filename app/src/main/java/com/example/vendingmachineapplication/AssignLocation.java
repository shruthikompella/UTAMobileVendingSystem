package com.example.vendingmachineapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AssignLocation extends AppCompatActivity implements OnItemSelectedListener {

    // Spinner element
    Spinner spinner1,spinner2,spinner3;
    List<String> lables1,lables2,lables3;
    private Button saveBtn,backBtn;
    //String label1,label2,label3;
    TextView v_name, loc_id,starttime,endtime,date_text;
    LinearLayout mainlayout;
    String name,date,op_name,homeUserName;
    String s_time,e_time,location;
    DatabaseHelper myDb;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignlocation);


        myDb = new DatabaseHelper(AssignLocation.this);
        v_name = findViewById(R.id.nameText);
        loc_id = findViewById(R.id.locationIdText);
        starttime = findViewById(R.id.startTimeText);
        endtime = findViewById(R.id.endTimeText);
        date_text = findViewById(R.id.dateText);

        saveBtn = (Button)this.findViewById(R.id.saveLocationBtn);
        backBtn = findViewById(R.id.alBackBtn);
        mainlayout=(LinearLayout)this.findViewById(R.id.mainLayout);
        mainlayout.setVisibility(LinearLayout.GONE);
        // Spinner element
        spinner1 = (Spinner) findViewById(R.id.locationSpinner);
        spinner2 = (Spinner) findViewById(R.id.startTimeSpinner);
        spinner3 = (Spinner) findViewById(R.id.endTimeSpinner);

        // Spinner click listener
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);

        // Loading spinner data from database
        loadSpinnerData();
        saveAssignLocation();



        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        name = bundle.getString("Name");
        date = bundle.getString("Date");
        homeUserName =  bundle.getString("U_name");
    }
    public void loadSpinnerData() {
        // database handler
       // DatabaseHandler db = new DatabaseHandler(getApplicationContext());

        // Spinner Drop down elements

        lables1 = new ArrayList<String>();
        lables2 = new ArrayList<String>();
        lables3 = new ArrayList<String>();

        lables1.add("Select Location");
        lables1.add("Location 1");
        lables1.add("Location 2");
        lables1.add("Location 3");
        lables1.add("Location 4");
        lables1.add("Location 5");
        lables1.add("Location 6");
        lables1.add("Location 7");
        lables1.add("Location 8");

        lables2.add("Select Start Time");
        lables2.add("08:00");
        lables2.add("09:00");
        lables2.add("10:00");
        lables2.add("11:00");
        lables2.add("12:00");
        lables2.add("13:00");
        lables2.add("14:00");
        lables2.add("15:00");
        lables2.add("16:00");

        lables3.add("Select End Time");
        lables3.add("09:00");
        lables3.add("10:00");
        lables3.add("11:00");
        lables3.add("12:00");
        lables3.add("13:00");
        lables3.add("14:00");
        lables3.add("15:00");
        lables3.add("16:00");
        lables3.add("17:00");


        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables1);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables2);
        ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables3);

        // Drop down layout style - list view with radio button
        dataAdapter1
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter3
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter1);
        spinner2.setAdapter(dataAdapter2);
        spinner3.setAdapter(dataAdapter3);
    }
    public void onClickAssignOp(View view){
 int flag = 0;

  Cursor boolcheck = myDb.getOperatorNullCheck(name);
        while (boolcheck.moveToNext()) {
            if(boolcheck.getString(0).equals("")){
                flag = 0;
                Intent myIntent = new Intent(getBaseContext(), AssignOperator.class);
                myIntent.putExtra("Name", name);
                myIntent.putExtra("Date", date);
                myIntent.putExtra("U_name", homeUserName);

                startActivity(myIntent);
            }
            else {
                flag = 1;
            }
        }
        if(flag == 1)
        {
            Toast.makeText(AssignLocation.this, "Operator Assigned Already", Toast.LENGTH_SHORT).show();

        }

    }
    public void saveAssignLocation(){
            saveBtn.setOnClickListener(
                    new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            mainlayout.setVisibility(LinearLayout.VISIBLE);
                            boolean isInserted = myDb.insertSchedule(name,location,s_time,e_time,date);
                            Cursor operator_name = myDb.fetchOperator(name,date);
                            if(operator_name.getCount() != 0) {
                                while (operator_name.moveToNext()) {
                                    op_name = operator_name.getString(0);
                                }
                                Log.d("Operator", op_name);
                                boolean result = myDb.updateScheduleData(name, date, op_name);
                                Log.d("Update", "Update result" + result);


                            }


                            Log.d("VM",Boolean.toString(isInserted));
                            if (isInserted == true) {

                                Toast.makeText(AssignLocation.this, location +" Assigned to "+name, Toast.LENGTH_SHORT).show();
                                v_name.setText(name);
                                loc_id.setText(location);
                                starttime.setText(s_time);
                                endtime.setText(e_time);
                                date_text.setText(date);


                            }
                            else{
                                Toast.makeText(AssignLocation.this, "Slot not available", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }
            );

    }


        @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                location = spinner1.getSelectedItem().toString();
                s_time = spinner2.getSelectedItem().toString();
                e_time = spinner3.getSelectedItem().toString();


            // Showing selected spinner item


    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(AssignLocation.this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }
    public void onBack(View view){
        onBackPressed();
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
