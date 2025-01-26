package com.example.baseandroidproject.fragments.register

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.R
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.data.response.isErrorMessage
import com.example.baseandroidproject.data.response.isExceptionMessage
import com.example.baseandroidproject.data.response.isLoadingMessage
import com.example.baseandroidproject.data.response.isSuccessMessage
import com.example.baseandroidproject.databinding.FragmentRegisterBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun setup() {
        observers()
    }

    override fun listeners() {
        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.registerCall.collect { response ->
                if (response != null) {
                    when {
                        response.isSuccessMessage() -> {
                            fragmentResult()
                        }

                        response.isErrorMessage() -> {
                            onErrorResponse(response.message.toString())
                            binding.loadingBar.isVisible = false
                        }

                        response.isLoadingMessage() -> {
                            onLoadingResponse()
                        }

                        response.isExceptionMessage() -> {
                            onExceptionResponse(response.message.toString())
                            binding.loadingBar.isVisible = false
                        }

                    }
                }
            }
        }
    }

    private fun onLoadingResponse() {
        binding.loadingBar.isVisible = true
    }

    private fun validateFields(): Boolean {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val repeatPassword = binding.etRepeatPassword.text.toString()
        with(viewModel) {
            return validateEmail(email) && validatePassword(password) && validateRepeatPassword(
                password,
                repeatPassword
            )
        }
    }

    private fun onErrorResponse(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun onExceptionResponse(message: String) {
        Snackbar.make(binding.root, "Error $message", Snackbar.LENGTH_LONG).show()
    }

    private fun registerUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        if (validateFields()) {
            viewModel.registerUser(email, password)
        } else {
            onErrorSnackbar(getString(R.string.some_fields_are_not_valid))
        }
    }

    private fun onErrorSnackbar(errorMessage: String) {
        Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_LONG).show()

    }

    private fun fragmentResult(){
            setFragmentResult("registration", Bundle().apply{
                putString("email", binding.etEmail.text.toString())
                putString("password", binding.etPassword.text.toString())
            })
            findNavController().navigateUp()
    }
}