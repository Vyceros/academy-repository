package com.example.baseandroidproject.fragments.login

import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.data.response.isErrorMessage
import com.example.baseandroidproject.data.response.isExceptionMessage
import com.example.baseandroidproject.data.response.isLoadingMessage
import com.example.baseandroidproject.data.response.isSuccessMessage
import com.example.baseandroidproject.databinding.FragmentLoginBinding
import com.example.baseandroidproject.sessions.UserSessions
import com.example.baseandroidproject.viewModels.login.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var navController: NavController

    override fun setup() {
        navController = findNavController()
        observers()
        receiveFragmentResult()
    }

    override fun listeners() {
        binding.btnLogin.setOnClickListener {
            loginUser()
        }

        binding.btnRegister.setOnClickListener {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        //button disabled by default, we validate fields and enable button if $validateFields() conditions are met
        binding.etEmail.addTextChangedListener { validateFields() }
        binding.etPassword.addTextChangedListener { validateFields() }

    }

    private fun loginUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        viewModel.loginUser(email, password)
    }

    private fun observers() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {
            viewModel.loginCall.collect { response ->
                if (response != null) {
                    when {
                        response.isSuccessMessage() -> {
                            response.data?.let {
                                onSuccessResponse(
                                    it.token,
                                    binding.etEmail.text.toString()
                                )
                            }
                            binding.loadingBar.isVisible = false
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

    private fun onSuccessResponse(token: String, email: String) {
        if (binding.cbRememberMe.isChecked) {
            val sessionManager = UserSessions(requireContext().applicationContext)
            sessionManager.addToSession(token)
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment(token,email))
        } else {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment(email))
        }
    }

    private fun onErrorResponse(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun onLoadingResponse() {
        binding.loadingBar.isVisible = true
    }

    private fun onExceptionResponse(message: String) {
        Snackbar.make(binding.root, "Error $message", Snackbar.LENGTH_LONG).show()
    }

    private fun validateFields() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        with(binding) {
            btnLogin.isEnabled =
                viewModel.validatePassword(password) && viewModel.validateEmail(email)
        }
    }

    private fun receiveFragmentResult() {
        setFragmentResultListener("registration") { _, bundle ->
            val email = bundle.getString("email")
            val password = bundle.getString("password")
            binding.etEmail.setText(email)
            binding.etPassword.setText(password)
        }
    }
}