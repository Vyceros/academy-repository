package com.example.learnapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


val singleDigits = NumberArraysGeorgian().singleDigit
val twoDigits = NumberArraysGeorgian().twoDigits
val tenMultiples = NumberArraysGeorgian().tenMultiples
val tenCompounds = NumberArraysGeorgian().tensPlaceCompound
val threeDigits = NumberArraysGeorgian().threeDigits


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.etInputField)
        val btnConfirmation: Button = findViewById(R.id.btnConverter)
        val textResultView: TextView = findViewById(R.id.tvResultText)

        btnConfirmation.setOnClickListener {
            val converter = ConvertToGeorgian()
            val stringRepresent = editText.text.toString()
            textResultView.text = converter.convertToGeorgian(stringRepresent.toInt())
        }

    }
    //nullable shemdeg leqciaze, collections
}






