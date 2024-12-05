package com.example.fourthassignment

import android.os.Bundle
import android.renderscript.ScriptGroup.Input
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import com.example.fourthassignment.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val validations = InputValidations()

    }

}








