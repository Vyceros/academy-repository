package com.example.baseandroidproject.ui.register

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.R
import com.example.baseandroidproject.data.remote.RetrofitImpl
import com.example.baseandroidproject.data.remote.models.auth.AuthResponse
import com.example.baseandroidproject.data.repositories.auth_repository.AuthRepository
import com.example.baseandroidproject.data.resource.Resource
import com.example.baseandroidproject.databinding.FragmentRegisterBinding
import com.example.baseandroidproject.ui.base.BaseFragment
import com.example.baseandroidproject.ui.view_model_factory.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels() {
        ViewModelFactory {
            RegisterViewModel(
                authRepository = AuthRepository(RetrofitImpl.authorizationService)
            )
        }
    }

    override fun setup() {
        setupObservers()
    }

    override fun listeners() {
        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.registerCall.collectLatest { response ->
                if (response != null) {
                    handleRegisterResponse(response)
                }
            }
        }
    }

    private fun handleRegisterResponse(response: Resource<AuthResponse>) {
        binding.loadingBar.isVisible = response is Resource.Loading

        when (response) {
            is Resource.Success -> {
                onSuccessfulRegistration()
            }

            is Resource.Error -> {
                showSnackbar(response.message)
            }

            else -> {}
        }
    }

    private fun registerUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val repeatPassword = binding.etRepeatPassword.text.toString()

        if (validateFields(email, password, repeatPassword)) {
            viewModel.registerUser(email, password)
        } else {
            showSnackbar(getString(R.string.some_fields_are_not_valid))
        }
    }

    private fun validateFields(email: String, password: String, repeatPassword: String): Boolean {
        return with(viewModel) {
            validateEmail(email) &&
                    validatePassword(password) &&
                    validateRepeatPassword(password, repeatPassword)
        }
    }

    private fun onSuccessfulRegistration() {
        setFragmentResult(
            "registration",
            Bundle().apply {
                putString("email", binding.etEmail.text.toString())
                putString("password", binding.etPassword.text.toString())
            }
        )
        findNavController().navigateUp()
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
}
