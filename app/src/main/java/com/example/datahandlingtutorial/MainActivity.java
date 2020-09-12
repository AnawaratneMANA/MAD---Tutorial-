package com.example.datahandlingtutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.datahandlingtutorial.Database.DBHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Create object for UI elements
    EditText eidUsername, eidPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Register elements
        eidUsername = findViewById(R.id.editTextUserName);
        eidPassword = findViewById(R.id.editTextPassword);


    }
    //Add onclick method for Add button
    public void addData(View view){
        //Get Information from the men
        String string_name = eidUsername.getText().toString(); //If i take String variable it will come as null values.
        String string_password = eidPassword.getText().toString();

        //Creating a object from the DBHelper class
        DBHelper dbhelper = new DBHelper(this);

        //calling the method
        long val = dbhelper.addInfo(eidUsername.getText().toString(), eidPassword.getText().toString());

        if(val> 0){
            Toast.makeText(this, "Data successfully inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Insertion Unsuccessful", Toast.LENGTH_SHORT).show();
        }
    }

    //View button for the button onclick action
    public void viewAll(View view){
        DBHelper dbhelper = new DBHelper(this);

        List unames = dbhelper.readAllInfo("user");

        Toast.makeText(this, unames.toString(), Toast.LENGTH_SHORT).show();
    }

    //Creating the Deleting method
    public void deleteDate(View view){
        DBHelper dbhelper = new DBHelper(this);

        dbhelper.deleteInfo(eidUsername.getText().toString());

        //Create a Toast
        Toast.makeText(this, eidUsername.getText().toString() +" Delete Successfully" , Toast.LENGTH_SHORT).show();
    }

    //Creating a method to update data
    public void updateData(View view){
        //Creating an object from the DBHelper class
        DBHelper dbhelper = new DBHelper(this);

        int val = dbhelper.UpdateInfor(eidUsername.getText().toString(),eidPassword.getText().toString());

        //Confirmation
        if(val > 0){
            Toast.makeText(this, "Data updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error in Date Update", Toast.LENGTH_SHORT).show();
        }
    }

    //Implementing the sign in method
    public void signIn(View view){

        //DB Object
        DBHelper dbhelper = new DBHelper(this);

        //Getting Lists
        List usernames = dbhelper.readAllInfo("user");
        List passwords = dbhelper.readAllInfo("password");

        String user = eidUsername.getText().toString();
        String password = eidPassword.getText().toString();

        //Login validation
        if(usernames.indexOf(user)>=0){
            if(passwords.get(usernames.indexOf(user)).equals(password)){
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}