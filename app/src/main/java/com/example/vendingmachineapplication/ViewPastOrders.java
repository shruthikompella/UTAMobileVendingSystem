package com.example.vendingmachineapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ViewPastOrders extends AppCompatActivity {
    Calendar oneDayLater;
    private EditText eText,eLname;
    DatePickerDialog picker;
    Button btnsubmit,btnback;
    ListView simpleList;
    String name;
    String day_append,month_append;
    DatabaseHelper myDb;
    ArrayList<ViewPastOrdersPojo> pastOrderList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        name = bundle.getString("Name");
        setContentView(R.layout.view_past_orders);
        btnsubmit = findViewById(R.id.search_past_orders);
        myDb = new DatabaseHelper(this);
        btnback = findViewById(R.id.btnback);
        eText=(EditText) findViewById(R.id.edittextDate);
        eLname = findViewById(R.id.et_lname);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("Hello");
                final Calendar cldr = Calendar.getInstance();
                oneDayLater = (Calendar) cldr.clone();
                // oneDayLater.add(Calendar.DATE, 1);
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ViewPastOrders.this,
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

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleList = (ListView) findViewById(R.id.Showpastorders);
                Cursor res = myDb.ViewPastOrders(eText.getText().toString(), eLname.getText().toString(), name);
                while (res.moveToNext()) {
                    pastOrderList.add(new ViewPastOrdersPojo(res.getString(0), res.getString(1), res.getString(4), res.getString(3), res.getString(2)));
                }

                ViewPastOrdersAdapter myAdapter=new ViewPastOrdersAdapter(getApplicationContext(),R.layout.viewpastorderslist,pastOrderList);
                simpleList.setAdapter(myAdapter);

                simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    //Toast.makeText(SearchOperator.this, val, Toast.LENGTH_SHORT).show();
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        int itemPos = position;
                        ViewPastOrdersPojo val = (ViewPastOrdersPojo) simpleList.getItemAtPosition(position);
                        Intent myIntent = new Intent(getBaseContext(), ViewSpecificOrder.class);
                        myIntent.putExtra("U_name",name);
                        myIntent.putExtra("Number",val.getConfirmation().toString());

                        startActivity(myIntent);
                        //Toast.makeText(SearchOperator.this, val.getFirstname(), Toast.LENGTH_SHORT).show();

                        //Log.d("Pos",simpleList.getItemAtPosition(position).toString());
                    }
                });
            }
        });
    }


    public void onBack(View view){
        onBackPressed();
    }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(ViewPastOrders.this, "Logged out", Toast.LENGTH_SHORT).show();
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



}
