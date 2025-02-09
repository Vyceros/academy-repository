package com.example.baseandroidproject.ui.login

import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.data.local.repos.UserDetailsRepository
import com.example.baseandroidproject.data.local.storage.ApplicationDatabase
import com.example.baseandroidproject.data.remote.response.ApiResponse
import com.example.baseandroidproject.data.remote.response.isErrorMessage
import com.example.baseandroidproject.data.remote.response.isExceptionMessage
import com.example.baseandroidproject.data.remote.response.isLoadingMessage
import com.example.baseandroidproject.data.remote.response.isSuccessMessage
import com.example.baseandroidproject.data.remote.services.login.LoginResponse
import com.example.baseandroidproject.databinding.FragmentLoginBinding
import com.example.baseandroidproject.ui.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels {
        ViewModelFactory {
            LoginViewModel(
                UserDetailsRepository(
                    ApplicationDatabase.getInstance(requireContext().applicationContext)
                        .userDetailsDao()
                )
            )
        }
    }

    override fun setup() {
        setupObservers()
        receiveFragmentResult()
    }

    override fun listeners() {
        with(binding) {
            btnLogin.setOnClickListener {
                viewModel.loginUser(
                    email = etEmail.text.toString(),
                    password = etPassword.text.toString(),
                    rememberMe = cbRememberMe.isChecked,
                    firstName = "",
                    lastName = ""
                )
            }
            btnRegister.setOnClickListener {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                )
            }
            etEmail.addTextChangedListener { validateLoginFields() }
            etPassword.addTextChangedListener { validateLoginFields() }
        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginCall.collect { response ->
                response?.let { onLoginResponse(it) }
            }
        }
    }

    private fun onLoginResponse(response: ApiResponse<LoginResponse>) {
        binding.loadingBar.isVisible = response.isLoadingMessage()

        when {
            response.isSuccessMessage() -> {
                onSuccessfulLogin()
            }

            response.isErrorMessage() -> {
                Snackbar.make(
                    binding.root,
                    response.message.toString(),
                    Snackbar.LENGTH_SHORT
                ).show()
            }

            response.isExceptionMessage() -> {
                Snackbar.make(
                    binding.root,
                    "Error: ${response.message}",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun onSuccessfulLogin() {

        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        )
    }


    private fun validateLoginFields() {
        binding.btnLogin.isEnabled = viewModel.run {
            validateEmail(binding.etEmail.text.toString()) && validatePassword(binding.etPassword.text.toString())
        }
    }

    private fun receiveFragmentResult() {
        setFragmentResultListener("registration") { _, bundle ->
            bundle.getString("email")?.let { binding.etEmail.setText(it) }
            bundle.getString("password")?.let { binding.etPassword.setText(it) }
        }
    }
}