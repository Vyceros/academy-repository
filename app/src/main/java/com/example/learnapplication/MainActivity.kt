package com.example.learnapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText: EditText = findViewById(R.id.etInputField)
        val btnConfirmation: Button = findViewById(R.id.btnConverter)
        val textResultView: TextView = findViewById(R.id.tvResultText)
        val btnToggle: Button = findViewById(R.id.btnLanguageToggle)

         btnToggle.setOnClickListener {
            btnToggle.isActivated = !btnToggle.isActivated
        }



        btnConfirmation.setOnClickListener {
            val numberInput = editText.text.toString()
            val numberRepresentation = numberInput.toIntOrNull()

            if (btnToggle.isActivated) {
                val converter = ConvertToGeorgian()
                textResultView.text = converter.convertToGeorgian(numberRepresentation)
            } else {
                val converter = ConvertToEnglish()
                textResultView.text = converter.convertToEnglish(numberRepresentation)
            }
        }


    }
    //nullable shemdeg leqciaze, collections, let, ||apply, run, also
}






