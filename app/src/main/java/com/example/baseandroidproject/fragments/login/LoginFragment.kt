package com.example.baseandroidproject.fragments.login

import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.data.login.LoginResponse
import com.example.baseandroidproject.data.response.ApiResponse
import com.example.baseandroidproject.data.response.isErrorMessage
import com.example.baseandroidproject.data.response.isExceptionMessage
import com.example.baseandroidproject.data.response.isLoadingMessage
import com.example.baseandroidproject.data.response.isSuccessMessage
import com.example.baseandroidproject.databinding.FragmentLoginBinding
import com.example.baseandroidproject.sessions.DataStore
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels{
        ViewModelFactory {
            LoginViewModel(DataStore(requireContext().applicationContext))
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
                    etEmail.text.toString(),
                    etPassword.text.toString()
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
                response.data?.let { loginData ->
                    onSuccessfulLogin(loginData.token)
                }
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

    private fun onSuccessfulLogin(token: String) {
        if (binding.cbRememberMe.isChecked) {
            viewModel.saveToken(token, binding.etEmail.text.toString())
        }else{
            viewModel.saveEmail(binding.etEmail.text.toString())
        }

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