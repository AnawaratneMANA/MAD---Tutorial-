package com.example.kotlinproject

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            onSubmit();
        }
    }
    //Method.
    @SuppressLint("SetTextI18n")
    fun onSubmit(){
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        output.text = "Age:" + (currentYear - inputField.text.toString().toInt())
    }

}