package com.example.mad_tutorial_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText num1;
    private EditText num2;
    private TextView out;

    private Button Addbtn1, Subbtn2, Mulbtn3, Devbtn4;

    private String number_1;
    private String number_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        number_1 = intent.getStringExtra(FirstActivity.name1);
        number_2 = intent.getStringExtra(FirstActivity.name2);

        //Registering the elements
        num1 = findViewById(R.id.number1);
        num2 = findViewById(R.id.number2);
        Addbtn1 = findViewById(R.id.plusbtn);
        Subbtn2 = findViewById(R.id.minebtn);
        Mulbtn3 = findViewById(R.id.mulbtn);
        Devbtn4 = findViewById(R.id.devbtn);
        out = findViewById(R.id.output);

        //Setting the text
        num1.setText(number_1);
        num2.setText(number_2);

        //do the calculations according to the action
        Addbtn1.setOnClickListener(this);
        Subbtn2.setOnClickListener(this);
        Mulbtn3.setOnClickListener(this);
        Devbtn4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        //Getting values from the new fields
        String number1 = num1.getText().toString();
        String number2 = num2.getText().toString();

        try{
            if(view.getId() == R.id.plusbtn){
                int sum = (Integer.parseInt(number1) + Integer.parseInt(number2));
                out.setText("Answer - " + Integer.toString(sum));
            }else if(view.getId() == R.id.minebtn) {
                int def = (Integer.parseInt(number1) - Integer.parseInt(number2));
                out.setText("Answer - " + Integer.toString(def));
            }else if (view.getId() == R.id.mulbtn) {
                int mul = (Integer.parseInt(number1) * Integer.parseInt(number2));
                out.setText("Answer - " + Integer.toString(mul));
            }else if (view.getId() == R.id.devbtn) {
                int dev = (Integer.parseInt(number1) / Integer.parseInt(number2));
                out.setText("Answer - " + Integer.toString(dev));
            }
        } catch (java.lang.NumberFormatException e){
            e.printStackTrace();
            Log.i("Error", "Enter a Number!");
            Toast toast = Toast.makeText(this, "Enter a Number!..", Toast.LENGTH_LONG);
            toast.show();
        }

    }
}