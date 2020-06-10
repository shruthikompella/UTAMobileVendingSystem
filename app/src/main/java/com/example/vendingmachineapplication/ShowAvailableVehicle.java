package com.example.vendingmachineapplication;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ShowAvailableVehicle extends AppCompatActivity {

    DatePickerDialog picker;
    EditText eText;
    Button btnGet,btnBack;
    TextView tvw;
    String day_append,month_append;
    DatabaseHelper myDb;
    ListView simpleList;
    String homeUserName;
    Calendar oneDayLater;
    ArrayList<AvailableVehicle> vehicleArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showavailablevehicle);
        Bundle bundle = getIntent().getExtras();
        homeUserName = bundle.getString("Name");
        myDb = new DatabaseHelper(this);
        //tvw=(TextView)findViewById(R.id.textView1);
        eText=(EditText) findViewById(R.id.savdate);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("Hello");
                final Calendar cldr = Calendar.getInstance();
                oneDayLater = (Calendar) cldr.clone();
                oneDayLater.add(Calendar.DATE, 1);
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                final int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ShowAvailableVehicle.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                if(monthOfYear+1 <10){
                                     month_append = "0" + (monthOfYear + 1);
                                }
                                if(dayOfMonth < 10)
                                {
                                   day_append = "0" + dayOfMonth;
                                }
                                eText.setText((month_append) + "/" + (day_append) + "/" + year);
                            }

                        }, year, month, day);
                picker.getDatePicker().setMinDate(oneDayLater.getTimeInMillis());
                picker.getDatePicker().setMaxDate(oneDayLater.getTimeInMillis());
                picker.show();
            }
        });
        btnGet=(Button)findViewById(R.id.showAvailableVehicleBtn);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                calendar.add(Calendar.DAY_OF_YEAR, 1);
                Date tomorrow = calendar.getTime();
                simpleList = (ListView) findViewById(R.id.showVehicleList);

                Cursor res =  myDb.getAvailableVehicle(eText.getText().toString());

                while (res.moveToNext()) {
                    vehicleArrayList.add(new AvailableVehicle(res.getString(0),res.getString(1),res.getString(2)));

                }

                ShowAvailableVehicleAdapter myAdapter=new ShowAvailableVehicleAdapter(getApplicationContext(),R.layout.availablevehiclelist,vehicleArrayList);
                simpleList.setAdapter(myAdapter);

                simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    //Toast.makeText(SearchOperator.this, val, Toast.LENGTH_SHORT).show();
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        int itemPos = position;
                        AvailableVehicle val = (AvailableVehicle) simpleList.getItemAtPosition(position);
                        Toast.makeText(ShowAvailableVehicle.this, val.getName(), Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(getBaseContext(), AssignLocation.class);
                        myIntent.putExtra("Name",val.getName());
                        myIntent.putExtra("Date",val.getDate());
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

    public void onBack(View view){
        onBackPressed();
    }

    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(ShowAvailableVehicle.this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }

}
