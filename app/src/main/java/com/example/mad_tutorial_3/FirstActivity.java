package com.example.mad_tutorial_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FirstActivity extends AppCompatActivity {

    //Element declaring
    private Button send;
    private EditText number_1;
    private EditText number_2;

    //Final Values
    public static final String name1 = "Number1Name";
    public static final String name2 = "Number2Name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        send = findViewById(R.id.SendBtn);
        number_1 = findViewById(R.id.number1);
        number_2 = findViewById(R.id.number2);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendData(view);
            }
        });
    }

    //Creating a new method to send data
    public void SendData(View view){

        Intent intent = new Intent(this, SecondActivity.class);
        try {
            String Number1 = (number_1.getText().toString());
            String Number2 = (number_2.getText().toString());

            //Creating the layoutInflater instance
            LayoutInflater LI = getLayoutInflater();

            //Getting the view object as defined in the customtoast.xml
            View layout = LI.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_layout));

            //Creating the custom toast
            Toast toast = new Toast(getApplicationContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL,0,0);
            toast.setView(layout);
            toast.show();

            //Sending data to the next Activity
            intent.putExtra(name1,Number1);
            intent.putExtra(name2,Number2);
            startActivity(intent);
        } catch (java.lang.NumberFormatException e) {
            e.printStackTrace();
        }
    }
}