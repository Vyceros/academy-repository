package com.example.baseandroidproject

import android.graphics.Color
import android.os.Bundle
import android.util.Log.d
import android.widget.AutoCompleteTextView.Validator
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidproject.databinding.ActivityMainBinding
import com.example.baseandroidproject.validations.InputValidations
import com.example.baseandroidproject.validations.User
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var user: User

    private var usersList = mutableListOf<User?>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initializeListeners()

    }



    private fun initializeListeners() {

        binding.btnSave.setOnClickListener {
            val user = initializeUser()
            if (validateUser()) {
                usersList.add(user)

                Snackbar.make(
                    binding.root,
                    getString(R.string.creating_user), Toast.LENGTH_SHORT
                ).setAnchorView(binding.tvUserCount)
                    .setTextColor(Color.WHITE)
                    .setBackgroundTint(Color.GREEN)
                    .setActionTextColor(Color.BLACK)
                    .setAction("Close"){}
                    .show()
            }

        }
    }

    private fun validateUser(): Boolean {
        val validator = InputValidations()
        var validationStatus = true
        val user = initializeUser()

        if (!validator.validateUserName(user.fullName)) {

            binding.etFullNameInput.error = getString(R.string.full_name_validation_error)
            validationStatus = false
        }

        if (!validator.validateEmail(user.email)) {
            binding.etEmailInput.error = getString(R.string.email_validation_error)

            validationStatus = false
        }

        if (!validator.validateAge(user.age)) {
            binding.etAgeInput.error = getString(R.string.age_validation_error)
            validationStatus = false
        }
        return validationStatus
    }

    private fun initializeUser(): User {
        user = User(
            fullName = binding.etFullNameInput.text.toString(),
            email = binding.etEmailInput.text.toString(),
            age = "12"
        )
        return user
    }


}