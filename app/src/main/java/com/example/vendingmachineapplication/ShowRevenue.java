package com.example.vendingmachineapplication;

import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class ShowRevenue extends AppCompatActivity {

    DatePickerDialog picker;
    EditText eText;
    Button btnGet,btnBack;
    TextView tvw;
    DatabaseHelper myDb;
    ListView simpleList;
    Calendar today;
    LinearLayout L1;
    String day_append,month_append,homeUserName;

    float sum = 0;
    ArrayList<ShowRevenuePojo> revenueArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showrevenue);
        myDb = new DatabaseHelper(this);
        L1 = (LinearLayout)findViewById(R.id.headingLayout);

        L1.setVisibility(LinearLayout.GONE);
        Bundle bundle = getIntent().getExtras();
        homeUserName = bundle.getString("Name");
        //tvw=(TextView)findViewById(R.id.textView1);
        eText=(EditText) findViewById(R.id.selectDateEdit);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("Hello");
                final Calendar cldr = Calendar.getInstance();
                today = (Calendar) cldr.clone();
                today.add(Calendar.DATE, 0);
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(ShowRevenue.this,
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

                picker.getDatePicker().setMaxDate(today.getTimeInMillis());
                picker.show();
            }
        });
        btnGet=(Button)findViewById(R.id.showRevenueBtn);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L1.setVisibility(LinearLayout.VISIBLE);

                simpleList = (ListView) findViewById(R.id.revenueList);

                Cursor res =  myDb.getRevenue(eText.getText().toString());

                while (res.moveToNext()) {
                    revenueArrayList.add(new ShowRevenuePojo(res.getString(0),res.getFloat(3)));
                    sum = sum + res.getFloat(3);

                    }
                revenueArrayList.add(new ShowRevenuePojo("Total Revenue",sum));
                ShowRevenueAdapter myAdapter=new ShowRevenueAdapter(getApplicationContext(),R.layout.revenuelist,revenueArrayList);
                simpleList.setAdapter(myAdapter);


                //tvw.setText("Selected Date: "+ eText.getText());
            }
        });

    }
        public void onBack(View view){
         onBackPressed();
        }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(ShowRevenue.this, "Logged out", Toast.LENGTH_SHORT).show();
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
