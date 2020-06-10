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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewMyPastOrders extends AppCompatActivity {
    Calendar oneDayLater;
    private EditText eText;
    DatePickerDialog picker;
    Button btnsubmit, btnback;
    String day_append,month_append;
    DatabaseHelper myDb;
String name;
    ListView simpleList;
    ArrayList<ViewMyPastOrdersPojo> pastOrderList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_my_past_orders);

        Bundle bundle = getIntent().getExtras();

        //Extract the data…
        name = bundle.getString("Name");
        btnback = findViewById(R.id.btnback);
        myDb = new DatabaseHelper(this);
        btnsubmit = findViewById(R.id.search_past_orders);
        eText=(EditText) findViewById(R.id.edittextDate);
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

                cldr.set(Calendar.MONTH, month);
                cldr.set(Calendar.DAY_OF_MONTH, day);
                cldr.set(Calendar.YEAR, year - 2);
                // date picker dialog
                picker = new DatePickerDialog(ViewMyPastOrders.this,
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
                picker.getDatePicker().setMinDate(cldr.getTimeInMillis());
                Calendar calen = Calendar.getInstance();
                cldr.set(Calendar.MONTH, month);
                cldr.set(Calendar.DAY_OF_MONTH, day);
                cldr.set(Calendar.YEAR, year);

                picker.getDatePicker().setMaxDate(cldr.getTimeInMillis());
                picker.show();
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleList = (ListView) findViewById(R.id.Showpastorders);

                Cursor res= myDb.fetchPastOrders(eText.getText().toString(),name);
                while (res.moveToNext()){
                    Log.d("Past",res.getString(0)+" "+ res.getString(1)+" "+ res.getString(2));
                    pastOrderList.add(new ViewMyPastOrdersPojo(res.getString(0),res.getString(1) , res.getString(2)));

                }

                ViewMyPastOrdersAdapter myAdapter=new ViewMyPastOrdersAdapter(getApplicationContext(),R.layout.viewmypastorderslist,pastOrderList);
                simpleList.setAdapter(myAdapter);





                simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    //Toast.makeText(SearchOperator.this, val, Toast.LENGTH_SHORT).show();
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        int itemPos = position;
                        ViewMyPastOrdersPojo val = (ViewMyPastOrdersPojo) simpleList.getItemAtPosition(position);
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
        Toast.makeText(ViewMyPastOrders.this, "Logged out", Toast.LENGTH_SHORT).show();
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
