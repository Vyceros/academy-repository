package com.example.baseandroidproject.presentation.auth.login

import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.databinding.FragmentLoginBinding
import com.example.baseandroidproject.presentation.base.BaseFragment
import com.example.baseandroidproject.presentation.utils.launchRepeatLifecycleScope
import com.example.baseandroidproject.presentation.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

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
                login()
            }
            btnRegister.setOnClickListener {
                findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
                )
            }

        }
    }

    private fun login() {
        viewModel.loginUser(
            email = binding.etEmail.text.toString(),
            password = binding.etPassword.text.toString(),
            rememberMe = binding.cbRememberMe.isChecked
        )
    }

    private fun setupObservers() {
        launchRepeatLifecycleScope {
            viewModel.loginEvents.collect { event ->
                when (event) {

                    is LoginEvent.NavigateToHome -> {
                        onSuccessfulLogin()
                    }

                    is LoginEvent.ShowError -> {
                        binding.root.showSnackBar(
                            requireContext(), event.message
                        )
                    }
                }

            }
        }

        launchRepeatLifecycleScope {
            viewModel.loginState.collect { state ->
                binding.loadingBar.isVisible = state is LoginState.Loading

            }
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
