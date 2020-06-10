package com.example.vendingmachineapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AssignOperator extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner1;
    List<String> lables1;
    private Button saveBtn, backBtn;
    String label1;
    TextView nameText, dateText, unameText;
    String name, date, uname1, homeUserName;
    DatabaseHelper myDb;
    LinearLayout mainlayout;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignoperator);
        myDb = new DatabaseHelper(AssignOperator.this);

        Bundle bundle = getIntent().getExtras();

        nameText = findViewById(R.id.nameText);

        dateText = findViewById(R.id.dateText);
        unameText = findViewById(R.id.unameText);

        //Extract the data…
        name = bundle.getString("Name");
        date = bundle.getString("Date");
        homeUserName = bundle.getString("U_name");
        backBtn = findViewById(R.id.aoBackBtn);
        saveBtn = (Button) this.findViewById(R.id.saveLocationBtn);
        mainlayout = (LinearLayout) this.findViewById(R.id.mainLayout);
        mainlayout.setVisibility(LinearLayout.GONE);
        // Spinner element

        saveBtn = (Button) this.findViewById(R.id.saveOperatorBtn);
        mainlayout = (LinearLayout) this.findViewById(R.id.mainLayout);
        mainlayout.setVisibility(LinearLayout.GONE);
        // Spinner element
        spinner1 = (Spinner) findViewById(R.id.operatorSpinner);

        loadSpinnerData();
        saveAssignOperator();


    }


    public void loadSpinnerData() {

        lables1 = new ArrayList<String>();
        lables1.add("Select Operator");



        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) {
            return;
        }
        while (res.moveToNext()) {

            if (res.getString(10).equals("operator")) {
                lables1.add(res.getString(2));
            }
        }

        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables1);
        Log.d("data", lables1.toString());
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter1.notifyDataSetChanged();
        spinner1.setAdapter(dataAdapter1);
        spinner1.setOnItemSelectedListener(this);


    }

    private void saveAssignOperator() {
        saveBtn.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        mainlayout.setVisibility(LinearLayout.VISIBLE);
//                        boolean boool = myDb.searchDummy(uname);
                        Log.d("Dummy", "value is " + myDb.searchDummy(uname1));
                        Boolean check = myDb.searchDummy(uname1);
                        if (check == false) {
                            // Cursor operator_name = myDb.fetchOperator(name,date);
                            //while(operator_name.moveToNext()) {
                            //if (operator_name.getString(0).equals(null)) {
                            boolean result = myDb.updateScheduleData(name, date, uname1);
                            Log.d("Result", "value is " + result);

                            if (result) {
                                nameText.setText(name);
                                dateText.setText(date);
                                unameText.setText(uname1);
                                Toast.makeText(AssignOperator.this, "Operator assigned", Toast.LENGTH_SHORT).show();
                                boolean ins_res = myDb.insertInDummy(uname1);
                            }
                        }
                        // }
                        // }
                        else {

                            //boolean result = myDb.updateScheduleData(name,date,uname1);

                            Toast.makeText(AssignOperator.this, "Operator not available", Toast.LENGTH_SHORT).show();


                        }


                    }
                }
        );
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        uname1 = parent.getItemAtPosition(position).toString();
        //uname1 = spinner1.getSelectedItem().toString();
        //Toast.makeText(AssignOperator.this, item, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(AssignOperator.this, "Nothing selected " + uname1, Toast.LENGTH_SHORT).show();
    }

    public void onLogout(View view) {
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(AssignOperator.this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }

    public void onBack(View view) {
        onBackPressed();
    }

    public void onHome(View view) {
        myDb = new DatabaseHelper(this);
        HomeRedirect hm = new HomeRedirect();
        int temp = hm.homeredirect(homeUserName, myDb);
        if (temp == 1) {

            Intent myIntent = new Intent(getBaseContext(), ManagerHome.class);
            myIntent.putExtra("Name", homeUserName);
            startActivity(myIntent);
        } else if (temp == 2) {
            Intent myIntent = new Intent(getBaseContext(), OperatorHome.class);
            myIntent.putExtra("Name", homeUserName);
            startActivity(myIntent);
        } else if (temp == 3) {
            Intent myIntent = new Intent(getBaseContext(), UserHome.class);
            myIntent.putExtra("Name", homeUserName);
            startActivity(myIntent);
        }


    }
}