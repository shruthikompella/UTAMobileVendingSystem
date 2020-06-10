package com.example.vendingmachineapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends AppCompatActivity {
    DatabaseHelper myDb;

    private EditText username;
    private EditText password;
    private Button loginBtn;
    private int temp = 0;
    private Boolean flag = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        myDb = new DatabaseHelper(Login.this);

        username = (EditText)findViewById(R.id.loginUserName);
        password = (EditText)findViewById(R.id.loginPassword);
        loginBtn = (Button)findViewById(R.id.loginButton);
        Login();
        //AddData();
    }

    /*public void AddData() {
        loginBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            boolean isInserted = myDb.insertData(username.getText().toString(), password.getText().toString(), "manger");
                            if (isInserted == true)
                                Toast.makeText(Login.this, "Logged In", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(Login.this, "ERROR", Toast.LENGTH_LONG).show();
                        }


                }
        );
    }*/


    public void Login(){
        loginBtn.setOnClickListener(
                new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                       Cursor res =  myDb.getAllData();
                       if(res.getCount() == 0) {
                           showMessage("Error","Nothing Found");
                           return;
                       }

                      // StringBuffer buffer = new StringBuffer();
                        String uname = username.getText().toString();
                        String pwd = password.getText().toString();

                       while (res.moveToNext()){

                         if(res.getString(2).equals(uname) && res.getString(3).equals(pwd) && res.getString(10).equals("manager")){
                             temp = 1;
                             flag = true;
                             Log.d("VM", " MANAGER");
                             break;
                           }
                          else if(res.getString(2).equals(uname) && res.getString(3).equals(pwd) && res.getString(10).equals("operator")){
                               temp = 2;
                               flag = true;
                             Log.d("VM", " OPERATOR");
                               break;
                           }
                         else if(res.getString(2).equals(uname) && res.getString(3).equals(pwd) && res.getString(10).equals("user")){
                             temp = 3;
                             flag = true;
                             Log.d("VM", " USER");
                             break;
                         }


                    }
                       if(flag)
                       {
                           Toast.makeText(Login.this, "Logged In", Toast.LENGTH_LONG).show();

                           if(temp == 1) {

                               Intent myIntent = new Intent(getBaseContext(), ManagerHome.class);
                               myIntent.putExtra("Name",username.getText().toString());
                               startActivity(myIntent);
                           }
                           else if(temp == 2){
                               Intent myIntent = new Intent(getBaseContext(), OperatorHome.class);
                               myIntent.putExtra("Name",username.getText().toString());
                               startActivity(myIntent);
                           }
                           else if(temp ==3){
                               Intent myIntent = new Intent(getBaseContext(), UserHome.class);
                               myIntent.putExtra("Name",username.getText().toString());
                               startActivity(myIntent);
                           }
                       }
                       else {
                           Toast.makeText(Login.this, "Invalid username/password", Toast.LENGTH_SHORT).show();
                       }
                }
                }
        );
    }

    public void onBack(View view){
        super.onBackPressed();
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
