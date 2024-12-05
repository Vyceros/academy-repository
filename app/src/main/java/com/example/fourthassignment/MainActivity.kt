package com.example.fourthassignment
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    //containers/layouts
    private lateinit var inputContainer : LinearLayout
    private lateinit var resultContainer : LinearLayout

    //for errors
    private lateinit var tvErrorScreen : TextView

    //Buttons
    private lateinit var btnClear : Button
    private lateinit var btnAgain : Button
    private lateinit var btnSave : Button

    //Inputs
    private lateinit var etUsername : EditText
    private lateinit var etEmail : EditText
    private lateinit var etFirstName : EditText
    private lateinit var etLastName : EditText
    private lateinit var etAge : EditText

    //iews to show information
    private lateinit var tvUsername : TextView
    private lateinit var tvEmail : TextView
    private lateinit var tvFirstName : TextView
    private lateinit var tvLastName : TextView
    private lateinit var tvAge : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeComponents()


    }



    private fun initializeComponents(){
        btnSave = findViewById(R.id.btnSave)
        btnAgain = findViewById(R.id.btnAgain)
        btnClear = findViewById(R.id.btnClear)

        inputContainer = findViewById(R.id.inputContainer)
        resultContainer = findViewById(R.id.resultContainer)

        tvErrorScreen = findViewById(R.id.tvErrorScreen)

        etEmail = findViewById(R.id.etEmailInput)
        etUsername = findViewById(R.id.etUsernameInput)
        etFirstName = findViewById(R.id.etFirstNameInput)
        etLastName = findViewById(R.id.etLastNameInput)
        etAge = findViewById(R.id.etAgeInput)

        tvEmail = findViewById(R.id.tvEmailInput)
        tvUsername = findViewById(R.id.tvUsernameInput)
        tvFirstName = findViewById(R.id.tvFirstNameInput)
        tvLastName = findViewById(R.id.tvLastNameInput)
        tvAge = findViewById(R.id.tvAgeInput)

    }

    fun validationChecks()
    {
        val email = etEmail.text.toString()

        val userName = etUsername.text.toString()
        val firstName = etFirstName.text.toString()

        val lastName = etLastName.text.toString()

        val age = etAge.text.toString()


    }
}








