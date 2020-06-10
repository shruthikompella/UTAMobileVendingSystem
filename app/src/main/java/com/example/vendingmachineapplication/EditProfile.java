package com.example.vendingmachineapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfile extends AppCompatActivity {
        EditText erole,efname,elname,ephone,eemail,estreet,ecity,estate,ezip,euname;
        String name;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("Name");
        myDb = new DatabaseHelper(this);
        efname = findViewById(R.id.editFirstName);
        elname = findViewById(R.id.editLastName);
        ephone = findViewById(R.id.editPhone);
        eemail = findViewById(R.id.editEmail);
        estreet = findViewById(R.id.editStreet);
        ecity = findViewById(R.id.editCity);
        estate = findViewById(R.id.editState);
        ezip = findViewById(R.id.editZip);

        erole= (EditText)findViewById(R.id.editRole);

        euname= (EditText)findViewById(R.id.editUserName);


        Cursor res = myDb.getAllData();
        while (res.moveToNext()) {

            if (res.getString(2).equalsIgnoreCase(name)) {
                Log.d("Profile", res.getString(7));
                euname.setText(res.getString(2));
                efname.setText(res.getString(0));
                elname.setText(res.getString(1));
                ephone.setText(res.getString(4));
                eemail.setText(res.getString(5));
                estreet.setText(res.getString(6));
                ecity.setText(res.getString(7));
                estate.setText(res.getString(8));
                ezip.setText(String.valueOf((res.getInt(9))));
                erole.setText(res.getString(10));
            }
        }
        erole.setEnabled(false);
        erole.setCursorVisible(false);
        euname.setEnabled(false);
        euname.setCursorVisible(false);


    }


    public void onEditSave(View view) {

        //Log.d("VM",Boolean.toString(isInserted));

        showMessage("Confirmation", "Are you sure?");


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






    public void onBack(View view){
        onBackPressed();
    }
    public void onLogout(View view){
        Intent myIntent = new Intent(getBaseContext(), Welcome.class);
        Toast.makeText(EditProfile.this, "Logged out", Toast.LENGTH_SHORT).show();
        startActivity(myIntent);
    }



    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogue,int id) {
                        boolean res = myDb.updateData(name, efname.getText().toString(), elname.getText().toString(), ephone.getText().toString(), eemail.getText().toString(), estreet.getText().toString(), ecity.getText().toString(), estate.getText().toString(), Integer.parseInt(ezip.getText().toString()));
                        if(res == true){
                            Toast.makeText(EditProfile.this, "Profile Updated", Toast.LENGTH_LONG).show();
                            Intent myIntent = new Intent(getBaseContext(), ViewProfile.class);
                            myIntent.putExtra("Name",name);
                            startActivity(myIntent);
                        }
                        else{
                            Toast.makeText(EditProfile.this, "Profile not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent myIntent = new Intent(getBaseContext(), ViewProfile.class);
                myIntent.putExtra("Name",name);
                startActivity(myIntent);
            }
        });

        builder.show();


    }
}
