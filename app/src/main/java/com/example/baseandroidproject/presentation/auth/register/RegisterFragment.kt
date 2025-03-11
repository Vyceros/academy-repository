package com.example.baseandroidproject.presentation.auth.register

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.databinding.FragmentRegisterBinding
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import com.example.baseandroidproject.presentation.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

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

        viewModel.registerUser(email, password)
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
