package com.example.baseandroidproject

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.forEach
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
        saveButtonClick()
        searchButtonClick()

    }

    private fun saveButtonClick() {
        binding.btnSave.setOnClickListener {
            val user = initializeUser()
            if (validateUser() && user !in usersList) {
                usersList.add(user)

                Snackbar.make(
                    binding.root,
                    getString(R.string.creating_user), Toast.LENGTH_SHORT
                ).setAnchorView(binding.btnGetUserInfo)
                    .setTextColor(Color.WHITE)
                    .setBackgroundTint(Color.GREEN)
                    .setActionTextColor(Color.BLACK)
                    .setAction("Close") {}
                    .show()

                binding.inputContainer.forEach {
                    if (it is EditText){
                        it.text.clear()
                    }
                }
            }else{
                Snackbar.make(
                    binding.root,
                    getString(R.string.user_save_failed_snackbar), Toast.LENGTH_SHORT
                ).setAnchorView(binding.btnGetUserInfo)
                    .setTextColor(Color.BLACK)
                    .setBackgroundTint(Color.RED)
                    .setActionTextColor(Color.BLACK)
                    .setAction("Close") {}
                    .show()
            }
            binding.tvUserCount.text = usersList.size.toString()
        }


    }

    private fun searchButtonClick(){
        binding.btnGetUserInfo.setOnClickListener {

            binding.inputContainer.visibility = View.GONE

            binding.displayInfoContainer.visibility = View.VISIBLE

            val emailToSearch = binding.searchView.text.toString()

            val user = usersList.find {it?.email == emailToSearch}

            if(user == null)
            {
                binding.tvUserNotFound.visibility = View.VISIBLE
            }else
            {
                binding.tvEmailDisplay.text = user.email
                binding.tvFullNameDisplay.text = user.fullName
                binding.tvAgeDisplay.text = user.age
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
            age = binding.etAgeInput.text.toString(),
        )
        return user
    }


}