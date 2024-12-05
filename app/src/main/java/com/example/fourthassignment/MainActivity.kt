package com.example.fourthassignment
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity



class MainActivity : AppCompatActivity() {
    //Buttons
    private lateinit var btnSave : Button
    private lateinit var btnClear : Button
    private lateinit var btnAgain : Button

    //Inputs
    private lateinit var etUsername : EditText
    private lateinit var etEmail : EditText
    private lateinit var etFirstName : EditText
    private lateinit var etLastName : EditText
    private lateinit var etAge : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}








