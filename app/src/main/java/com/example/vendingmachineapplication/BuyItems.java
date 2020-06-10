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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BuyItems extends AppCompatActivity {

    Spinner spinner1,spinner2;
    List<String> lables1,lables2;
    private Button saveBtn,backBtn;
    String label1,label2;
    DatabaseHelper myDb;
    int count;
    String name,fvtype,locinter,homeUserName,type;
    String quantity_drinks = "0",quantity_sandwiches="0",quantity_snacks="0";
    int drinks,snacks,sandwiches;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buyitems);
        myDb = new DatabaseHelper(BuyItems.this);
        saveBtn = (Button)this.findViewById(R.id.addBtn);
        backBtn = findViewById(R.id.vcBackBtn);
        // Spinner element
        spinner1 = findViewById(R.id.itemSpinner);
        spinner2 = findViewById(R.id.quantitySpinner);
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("V_Name");
        fvtype= bundle.getString("Type");
        locinter = bundle.getString("Location");
        homeUserName = bundle.getString("U_Name");
        drinks = Integer.parseInt(bundle.getString("Drinks_R"));
        sandwiches = Integer.parseInt(bundle.getString("Sandwiches_R"));
        snacks = Integer.parseInt(bundle.getString("Snacks_R"));
       // System.out.println("USERNAME :" + homeUserName);
        loadSpinnerData();

        // Spinner click listener
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = spinner1.getSelectedItem().toString();
                lables2 = new ArrayList<String>();
                lables2.add("Select Quantity");
                switch (type){
                    case "Snacks":
                        count = snacks;
                        for(int i=0;i<=count;i++) {
                            lables2.add(String.valueOf(i));
                        }
                        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(BuyItems.this,android.R.layout.simple_spinner_item, lables2);



                        dataAdapter2
                                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(dataAdapter2);
                        break;
                    case "Sandwiches":
                        count = sandwiches;
                        Log.d("Count","C: "+count);

                        for(int i=0;i<=count;i++) {

                            lables2.add(String.valueOf(i));
                        }
                        dataAdapter2 = new ArrayAdapter<String>(BuyItems.this,android.R.layout.simple_spinner_item, lables2);



                        dataAdapter2
                                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(dataAdapter2);
                        break;
                    case "Drinks":
                        count = drinks;
                        for(int i=0;i<=count;i++) {
                            lables2.add(String.valueOf(i));
                        }
                        dataAdapter2 = new ArrayAdapter<String>(BuyItems.this,android.R.layout.simple_spinner_item, lables2);



                        dataAdapter2
                                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner2.setAdapter(dataAdapter2);
                        break;

                }

                spinner2.setVisibility(View.VISIBLE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String quantity = spinner2.getSelectedItem().toString();
                if(type.equals("Drinks")){
                    quantity_drinks = quantity;

                }
                if(type.equals("Sandwiches")){
                    quantity_sandwiches = quantity;
                }
                if(type.equals("Snacks")){
                    quantity_snacks = quantity;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Loading spinner data from database

    }
    public void onBack(View view){
        onBackPressed();
    }
    public void loadSpinnerData() {

        lables1 = new ArrayList<String>();
        lables2 = new ArrayList<String>();

        lables1.add("Select Item");
        lables1.add("Drinks");
        lables1.add("Sandwiches");
        lables1.add("Snacks");




        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables1);
        dataAdapter1
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter1);


       /*lables2.add("Select Quantity");
        for(int i=0;i<=count;i++) {
            lables2.add(String.valueOf(i));
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables2);



        dataAdapter2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);*/

    }

    /*@Override

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = spinner1.getSelectedItem().toString();
        if(type.equals("Drinks")){
            count = drinks;

        }
        if(type.equals("Sandwiches")){
            count = sandwiches;
        }
        if(type.equals("Snacks")){
            count = snacks;
        }
        Log.d("Count","Count is "+count);
        for(int i=0;i<=count;i++) {
            lables2.add(String.valueOf(i));
        }

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables2);



        dataAdapter2
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter2);
        String quantity = spinner2.getSelectedItem().toString();
        if(type.equals("Drinks")){
            quantity_drinks = quantity;

        }
        if(type.equals("Sandwiches")){
            quantity_sandwiches = quantity;
        }
        if(type.equals("Snacks")){
            quantity_snacks = quantity;
        }




    }


*/




    /*@Override
    public void onNothingSelected(AdapterView<?> parent) {

    }*/

    public void onItemsAdd(View view)
    {
        //Toast.makeText(BuyItems.this, "Sandwiches "+quantity_sandwiches,Toast.LENGTH_SHORT).show();
        Log.d("items", "Drinks: "+Integer.parseInt(quantity_drinks));
        Log.d("items", "Sandwiches: "+Integer.parseInt(quantity_sandwiches));
        Log.d("items", "Snacks: "+Integer.parseInt(quantity_snacks));
    }

    public void onViewCartClick(View view){
        Cursor res = myDb.getCart();
        if(res.getCount() == 0){
            boolean isInserted = myDb.insertCart(Integer.parseInt(quantity_drinks),Integer.parseInt(quantity_sandwiches),Integer.parseInt(quantity_snacks));
            if (isInserted == true) {

                Toast.makeText(BuyItems.this, "Added", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(getBaseContext(), ViewCart.class);

                myIntent.putExtra("V_Name", name);
                myIntent.putExtra("Type", fvtype);
                myIntent.putExtra("Location", locinter);
                myIntent.putExtra("U_Name", homeUserName);
                Log.d("Testing3",homeUserName);

                startActivity(myIntent);
            }
        }

        else{
                boolean isUpdated = myDb.updateCart(Integer.parseInt(quantity_drinks),Integer.parseInt(quantity_sandwiches),Integer.parseInt(quantity_snacks));
                if(isUpdated){
                    Toast.makeText(BuyItems.this, "Updated", Toast.LENGTH_LONG).show();
                    Intent myIntent = new Intent(getBaseContext(), ViewCart.class);

                    myIntent.putExtra("V_Name", name);
                    myIntent.putExtra("Type", fvtype);
                    myIntent.putExtra("Location", locinter);
                    myIntent.putExtra("U_Name", homeUserName);
                    Log.d("Testing3",homeUserName);

                    startActivity(myIntent);
                }
            }


        //Log.d("VM",Boolean.toString(isInserted));

    }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(BuyItems.this, "Logged out", Toast.LENGTH_SHORT).show();
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
