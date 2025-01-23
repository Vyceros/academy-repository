package com.example.baseandroidproject.fragments.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.baseandroidproject.R
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.data.response.isErrorMessage
import com.example.baseandroidproject.data.response.isExceptionMessage
import com.example.baseandroidproject.data.response.isLoadingMessage
import com.example.baseandroidproject.data.response.isSuccessMessage
import com.example.baseandroidproject.databinding.FragmentLoginBinding
import com.example.baseandroidproject.viewModels.login.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var navController: NavController

    override fun setup() {
        observers()
        listeners()

    }

    override fun listeners() {
        binding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        viewModel.loginUser(email, password)
    }

    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            viewModel.loginCall.collect { response ->
                if (response != null) {
                    when {
                        response.isSuccessMessage() -> {
                            Snackbar.make(
                                binding.root,
                                getString(R.string.login_successful_message),
                                Snackbar.LENGTH_LONG
                            ).show()
                        }

                        response.isErrorMessage() -> {
                            Snackbar.make(
                                binding.root,
                                getString(R.string.error_message, response.message),
                                Snackbar.LENGTH_LONG
                            ).show()
                        }

                        response.isLoadingMessage() -> {
                            Snackbar.make(binding.root, "Loading...", Snackbar.LENGTH_LONG).show()
                        }

                        response.isExceptionMessage() -> {
                            Snackbar.make(
                                binding.root,
                                getString(R.string.login_exception_message, response.message),
                                Snackbar.LENGTH_LONG
                            ).show()
                        }

                    }
                }
            }
        }
    }
}