package com.example.baseandroidproject.ui.login

import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.data.local.AppDatabase
import com.example.baseandroidproject.data.remote.RetrofitImpl
import com.example.baseandroidproject.data.remote.models.auth.AuthResponse
import com.example.baseandroidproject.data.repositories.auth_repository.AuthRepository
import com.example.baseandroidproject.data.repositories.user_repository.UserRepository
import com.example.baseandroidproject.data.resource.Resource
import com.example.baseandroidproject.data.sessions.DataStore
import com.example.baseandroidproject.databinding.FragmentLoginBinding
import com.example.baseandroidproject.ui.base.BaseFragment
import com.example.baseandroidproject.ui.view_model_factory.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels() {
        ViewModelFactory {
            LoginViewModel(
                userRepo = UserRepository(
                    userService = RetrofitImpl.usersService,
                    database = AppDatabase.getInstance(requireContext().applicationContext)
                ),
                authRepository = AuthRepository(RetrofitImpl.authorizationService),
                dataStore = DataStore(requireContext().applicationContext)
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

    private fun validateLoginFields() {
        binding.btnLogin.isEnabled = viewModel.run {
            validateEmail(binding.etEmail.text.toString()) && validatePassword(binding.etPassword.text.toString())
        }
    }

    private fun receiveFragmentResult() {
        setFragmentResultListener("registration") { _, bundle ->
            binding.etEmail.setText(bundle.getString("email"))
            binding.etPassword.setText(bundle.getString("password"))
        }
    }
}
