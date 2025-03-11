package com.example.baseandroidproject.presentation.auth.login

import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.databinding.FragmentLoginBinding
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.domain.models.auth.AuthResponse
import com.example.baseandroidproject.presentation.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

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
                    rememberMe = cbRememberMe.isChecked
                )
            }
            btnRegister.setOnClickListener {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                )
            }

        }
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginState.collectLatest { response ->
                if (response != null) {
                    handleLoginResponse(response)
                }
            }
        }
    }

    private fun handleLoginResponse(response: Resource<AuthResponse>) {
        binding.loadingBar.isVisible = response is Resource.Loading

        when (response) {
            is Resource.Success -> {
                onSuccessfulLogin()
            }

            is Resource.Error -> {
                Snackbar.make(binding.root, response.message, Snackbar.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }

    private fun onSuccessfulLogin() {
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        )
    }


    private fun receiveFragmentResult() {
        setFragmentResultListener("registration") { _, bundle ->
            binding.etEmail.setText(bundle.getString("email"))
            binding.etPassword.setText(bundle.getString("password"))
        }
    }
}
