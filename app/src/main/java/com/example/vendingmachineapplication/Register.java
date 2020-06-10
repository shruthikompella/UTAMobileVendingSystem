package com.example.vendingmachineapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    DatabaseHelper myDb;
    private EditText fname;
    private EditText lname;
    private EditText username;
    private EditText password;
    private EditText phone;
    private EditText email;
    private EditText street;
    private EditText city;
    private EditText state;
    private EditText zip;
    private RadioGroup rolegroup;
    private RadioButton role;
    private Button registerBtn;
    private int selectedId;
    private String systemuserrole;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
       myDb = new DatabaseHelper(Register.this);
       fname = (EditText)findViewById(R.id.firstName);
       lname = (EditText)findViewById(R.id.lastName);
       username = (EditText)findViewById(R.id.registerUserName);
       password = (EditText)findViewById(R.id.registerPassword);
       phone = (EditText)findViewById(R.id.phoneNumber);
       email = (EditText)findViewById(R.id.emailAddress);
       street = (EditText)findViewById(R.id.streetAddress);
       city = (EditText)findViewById(R.id.city);
       state = (EditText)findViewById(R.id.state);
       zip = (EditText)findViewById(R.id.zipcode);
       /*rolegroup = (RadioGroup) findViewById(R.id.rolegroup);
       selectedId = rolegroup.getCheckedRadioButtonId();
       role = (RadioButton)findViewById(selectedId);*/
       registerBtn = (Button)findViewById(R.id.registerButton);

       onRegister();

    }
    public void onRadioButtonClicked(View view){

       boolean checked = ((RadioButton) view).isChecked();

       switch(view.getId())
       {
           case R.id.userrole:
               if(checked)
                   systemuserrole = "user";
               break;
           case R.id.managerrole:
               if(checked)
                   systemuserrole = "manager";
               break;
           case R.id.operatorrole:
               if(checked)
                   systemuserrole = "operator";
               break;
       }
    }

    public void onBack(View view){
        super.onBackPressed();
    }
   public void onRegister() {
        registerBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                     /*   Log.d("VM","hello in register");
                        Log.d("VM", "onClick: " + fname.getText().toString());
                        Log.d("VM", "onClick: " + lname.getText().toString());
                        Log.d("VM", "onClick: " + username.getText().toString());
                        Log.d("VM", "onClick: " + password.getText().toString());
                        Log.d("VM", "onClick: " + phone.getText().toString());
                        Log.d("VM", "onClick: " + email.getText().toString());
                        Log.d("VM", "onClick: " + street.getText().toString());
                        Log.d("VM", "onClick: " + city.getText().toString());
                        Log.d("VM", "onClick: " + state.getText().toString());
                        Log.d("VM", "onClick: " + zip.getText().toString());
                        Log.d("VM", "onClick: " + systemuserrole);*/

                       /* Log.d("VM", "onClick: " + fname.getText().toString())+ "" + lname.getText().toString()+ "" + username.getText().toString()+ "" +password.getText().toString()+ "" +phone.getText().toString()+
                                "" +email.getText().toString()+""+street.getText().toString()+""+city.getText().toString()+""+state.getText().toString()+""+Integer.parseInt(zip.getText().toString())+""+systemuserrole);
                        */
                        boolean isInserted = myDb.insertData(fname.getText().toString(),lname.getText().toString(),username.getText().toString(), password.getText().toString(),phone.getText().toString(),
                                email.getText().toString(),street.getText().toString(),city.getText().toString(),state.getText().toString(),Integer.parseInt(zip.getText().toString()),systemuserrole);
                        Log.d("VM",Boolean.toString(isInserted));
                        if (isInserted == true) {

                            Toast.makeText(Register.this, "Registered", Toast.LENGTH_LONG).show();
                            Intent myIntent = new Intent(getBaseContext(), Welcome.class);
                            startActivity(myIntent);
                        }
                        else
                            Toast.makeText(Register.this, "ERROR", Toast.LENGTH_LONG).show();
                    }


                }
        );
    }
}
