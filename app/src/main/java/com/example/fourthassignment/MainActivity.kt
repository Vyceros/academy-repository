package com.example.fourthassignment

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    //containers/layouts
    private lateinit var inputContainer: LinearLayout
    private lateinit var resultContainer: LinearLayout

    //Buttons
    private lateinit var btnClear: Button
    private lateinit var btnAgain: Button
    private lateinit var btnSave: Button

    //Inputs
    private lateinit var etUsername: EditText
    private lateinit var etEmail: EditText
    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var etAge: EditText

    //iews to show information
    private lateinit var tvUsername: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvFullName: TextView
    private lateinit var tvAge: TextView

    private val inputValidatorClass = ValidateInputs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeComponents()

        btnSave.setOnClickListener {
            saveButtonClick()
        }

        btnClear.setOnLongClickListener {
            clearLongButtonClick()
            true
        }

        btnAgain.setOnClickListener {
            againButtonClick()
        }
    }


    private fun initializeComponents() {
        btnSave = findViewById(R.id.btnSave)
        btnAgain = findViewById(R.id.btnAgain)
        btnClear = findViewById(R.id.btnClear)

        inputContainer = findViewById(R.id.inputContainer)
        resultContainer = findViewById(R.id.resultContainer)

        etEmail = findViewById(R.id.etEmailInput)
        etUsername = findViewById(R.id.etUsernameInput)
        etFirstName = findViewById(R.id.etFirstNameInput)
        etLastName = findViewById(R.id.etLastNameInput)
        etAge = findViewById(R.id.etAgeInput)

        tvEmail = findViewById(R.id.tvEmailInput)
        tvUsername = findViewById(R.id.tvUsernameInput)
        tvFullName = findViewById(R.id.tvFullNameInput)
        tvAge = findViewById(R.id.tvAgeInput)

    }

    private fun againButtonClick() {
        resultContainer.visibility = View.GONE
        inputContainer.visibility = View.VISIBLE

        btnSave.visibility = View.VISIBLE
        btnClear.visibility = View.VISIBLE

    }

    private fun saveButtonClick() {

        if (validationChecks()) {
            inputContainer.visibility = View.GONE
            resultContainer.visibility = View.VISIBLE

            btnSave.visibility = View.GONE
            btnClear.visibility = View.GONE

            tvEmail.text = etEmail.text
            tvUsername.text = etUsername.text
            tvFullName.text = getString(R.string.full_name, etFirstName.text, etLastName.text)
            tvAge.text = etAge.text
        }

    }

    private fun clearLongButtonClick() {
        val listOfEditTexts = listOf(etEmail, etLastName, etAge, etFirstName, etUsername)
        Snackbar.make(
            findViewById(R.id.main),
            getString(R.string.clearing_text), Toast.LENGTH_SHORT
        ).setAnchorView(btnSave)
            .setBackgroundTint(Color.WHITE)
            .setTextColor(Color.BLACK)
            .setActionTextColor(Color.RED)
            .setAction("Close")
            {
            }
            .show()
        listOfEditTexts.forEach { it.clearText() }
    }

    //should return true if all validations pass
    private fun validationChecks(): Boolean {
        val email = etEmail.text.toString()

        val userName = etUsername.text.toString()
        val firstName = etFirstName.text.toString()

        val lastName = etLastName.text.toString()

        val age = etAge.text.toString()

        val listOfInputs = listOf(email, userName, firstName, lastName, age)

        var isValid = true

        if (!inputValidatorClass.validateAge(age)) {
            etAge.error = getString(R.string.age_is_not_in_valid_format)
            isValid = false
        }
        if (!inputValidatorClass.validateEmail(email)) {
            etEmail.error = getString(R.string.email_not_valid_error)
            isValid = false
        }

        if (!inputValidatorClass.validateUserName(userName)) {
            etUsername.error = getString(R.string.username_not_long_enough_error)
            isValid = false
        }
        if (!inputValidatorClass.validateEmptyInputs(listOfInputs)) {
            Snackbar.make(
                findViewById(R.id.main),
                getString(R.string.some_fields_are_empty_error), Toast.LENGTH_SHORT
            ).setAnchorView(btnSave)
                .setBackgroundTint(Color.RED)
                .setTextColor(Color.WHITE)
                .setActionTextColor(Color.BLACK)
                .setAction("Close")
                {
                }


                .show()

            isValid = false
        }

        return isValid

    }

    private fun EditText.clearText() {
        this.text.clear()
    }
}








