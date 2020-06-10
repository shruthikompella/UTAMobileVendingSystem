package com.example.vendingmachineapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class Welcome extends AppCompatActivity{
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        myDb = new DatabaseHelper(this);

    }

    public void onWelcomeLogin(View view){
            Intent myIntent = new Intent(getBaseContext(), Login.class);
            startActivity(myIntent);
    }
    public void onWelcomeRegister(View view){
        Intent myIntent = new Intent(getBaseContext(),Register.class);
        startActivity(myIntent);
    }

}

