package com.example.vendingmachineapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.ActionMenuView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class HomeRedirect extends AppCompatActivity{
    //DatabaseHelper myDb;
    int temp = 0;
    String x;
    //Boolean flag = false;





    public int homeredirect(String uname,DatabaseHelper myDb) {


        Cursor res = myDb.getAllData();
        while (res.moveToNext()) {
            if (res.getString(2).equals(uname) && res.getString(10).equals("manager")) {
                temp = 1;
                //flag = true;
                Log.d("VM", " MANAGER");
                break;
            } else if (res.getString(2).equals(uname) && res.getString(10).equals("operator")) {
                temp = 2;
               // flag = true;
                Log.d("VM", " OPERATOR");
                break;
            } else if (res.getString(2).equals(uname) && res.getString(10).equals("user")) {
                temp = 3;
                //flag = true;
                Log.d("VM", " USER");
                break;
            }

        }


return temp;



    }
}
