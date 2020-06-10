package com.example.vendingmachineapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SearchVehicle extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    LinearLayout bytime_layout, byloc_layout;
    ArrayList<SearchVehiclePojo> operatorArrayList = new ArrayList<>();
    SchedulePojo pojo1;
    private RadioGroup radio_by;
    private EditText eText;
    String s_time,e_time,location_inter;
    String day_append,month_append;
    private EditText date, stime, etime, loc;
    DatePickerDialog picker;
    ListView simpleList;
    int temp = 0;
    private RadioButton by_time_loc;
    private Spinner spinner1, spinner2, spinner3;
    List<String> lables1,lables2,lables3;
    Calendar oneDayLater;
    Button btnsubmit,btnBack;
    ArrayList<SearchVehiclePojo> vehicleArrayList=new ArrayList<>();
    DatabaseHelper myDb;
    Date d1,d2,d3,d4;
    String homeUserName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_vehicle);
        Bundle bundle = getIntent().getExtras();
        homeUserName = bundle.getString("Name");
        bytime_layout=(LinearLayout)this.findViewById(R.id.byDateAndTime);
        bytime_layout.setVisibility(LinearLayout.GONE);
        byloc_layout=(LinearLayout)this.findViewById(R.id.byloc);
        byloc_layout.setVisibility(LinearLayout.GONE);
        spinner1 = (Spinner) findViewById(R.id.stime);
        spinner2 = (Spinner) findViewById(R.id.etime);
        spinner3 = (Spinner) findViewById(R.id.loc_inter);
        radio_by = (RadioGroup) findViewById(R.id.radio_group);
        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);
        myDb = new DatabaseHelper(SearchVehicle.this);
        btnBack = findViewById(R.id.btnback);
        btnsubmit = findViewById(R.id.search_vehicle);
        simpleList = (ListView) findViewById(R.id.showVehicleList);
        eText=(EditText) findViewById(R.id.edittextDate);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("Hello");
                final Calendar cldr = Calendar.getInstance();
                oneDayLater = (Calendar) cldr.clone();
                oneDayLater.add(Calendar.DATE, 0);
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(SearchVehicle.this,
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
                loadSpinnerData();
                radioClicks();

                btnsubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Cursor res =  myDb.getVehicleData();

                        if(temp == 1){
                            while (res.moveToNext()) {
                                String startTime_todate = res.getString(5);
                                String endTime_todate = res.getString(6);

                                DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                                try {
                                    d3 = dateFormat.parse(endTime_todate);
                                    d4 = dateFormat.parse(startTime_todate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                try {
                                    d1 = dateFormat.parse(s_time);
                                    d2 = dateFormat.parse(e_time);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                if (res.getString(4).equals(eText.getText().toString()) && (d1.getHours() <= d3.getHours() && d2.getHours() >= d4.getHours())) {
                                    //pojo1 = new SchedulePojo(res.getString(0),)
                                    vehicleArrayList.add(new SearchVehiclePojo(res.getString(0), res.getString(1), res.getString(2),res.getString(3),res.getString(4), res.getString(5),res.getString(6)));

                                }
                            }



                        }
                        else{
                            while (res.moveToNext()) {
                                String endTime_todate = res.getString(6);

                                DateFormat dateFormat = new SimpleDateFormat("HH:mm");
                                try {
                                    d2 = dateFormat.parse(endTime_todate);
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


                                if (res.getString(2).equals(location_inter) && res.getString(4).equals(eText.getText().toString()) && d1.getHours() < d2.getHours()) {

                                            vehicleArrayList.add(new SearchVehiclePojo(res.getString(0), res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6)));
                                    }



                            }
                        }

                         SearchVehicleAdapater myAdapter=new SearchVehicleAdapater(getApplicationContext(),R.layout.searchvehiclelist,vehicleArrayList);
                        simpleList.setAdapter(myAdapter);

                        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            //Toast.makeText(SearchOperator.this, val, Toast.LENGTH_SHORT).show();
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Log.d("U_Name",homeUserName);
                                int itemPos = position;
                                SearchVehiclePojo val = (SearchVehiclePojo) simpleList.getItemAtPosition(position);
                                Intent myIntent = new Intent(getBaseContext(), ViewSpecificVehicle.class);
                                myIntent.putExtra("Name",val.getName());
                                myIntent.putExtra("Date",val.getDate());
                                myIntent.putExtra("Start_time",val.getS_time());
                                myIntent.putExtra("End_time",val.getE_time());
                                myIntent.putExtra("USER_NAME",homeUserName);
                                startActivity(myIntent);
                                //Toast.makeText(SearchOperator.this, val.getFirstname(), Toast.LENGTH_SHORT).show();

                                //Log.d("Pos",simpleList.getItemAtPosition(position).toString());
                            }
                        });
                       /* simpleList = (ListView) findViewById(R.id.showVehicleList);
                        vehicleArrayList.add(new SearchVehiclePojo("T1","Food Truck","Location 6","Spaniolo & W 1st"));
                        SearchVehicleAdapater myAdapter=new SearchVehicleAdapater(getApplicationContext(),R.layout.searchvehiclelist,vehicleArrayList);
                        simpleList.setAdapter(myAdapter);*/
                    }
                });
        }

public void radioClicks(){
        radio_by.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                View radioButton = group.findViewById(group.getCheckedRadioButtonId());
                Log.d("App vending", "ID: "+group.indexOfChild(radioButton));

                if(group.indexOfChild(radioButton) == 0){
                    temp = 1;

                    byloc_layout.setVisibility(LinearLayout.GONE);
                    bytime_layout.setVisibility(LinearLayout.VISIBLE);

                }
                if(group.indexOfChild(radioButton) == 1){
                    temp = 2;

                    bytime_layout.setVisibility(LinearLayout.GONE);
                    byloc_layout.setVisibility(LinearLayout.VISIBLE);

                }
            }
        });

}
    public void onBack(View view){
       onBackPressed();
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
        spinner3.setAdapter(dataAdapter1);
        spinner1.setAdapter(dataAdapter2);
        spinner2.setAdapter(dataAdapter3);

    }






    public void onRadioButtonClicked(View view){
        Log.d("App vending", "inside click");
        boolean checked = ((RadioButton) view).isChecked();
        simpleList.setAdapter(null);
        switch(view.getId())
        {
            case R.id.bytime:
                if(checked) {
                    Log.d("App vending", "selected 1");
                    byloc_layout.setVisibility(LinearLayout.GONE);
                    bytime_layout.setVisibility(LinearLayout.VISIBLE);

                }
                break;
            case R.id.bylocation:
                if(checked){
                    bytime_layout.setVisibility(LinearLayout.GONE);
                    byloc_layout.setVisibility(LinearLayout.VISIBLE);
                    temp = 2;
                }
                break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        s_time = spinner1.getSelectedItem().toString();
        e_time = spinner2.getSelectedItem().toString();
        location_inter = spinner3.getSelectedItem().toString();
        Log.d("vehicle", s_time);
        Log.d("vehicle", e_time);
        Log.d("vehicle", location_inter);
        //Log.d("vehicle", eText.getText().toString());

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(SearchVehicle.this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }
}
