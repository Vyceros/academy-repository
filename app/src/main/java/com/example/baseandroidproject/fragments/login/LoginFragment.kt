package com.example.baseandroidproject.fragments.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.data.response.ApiResponse
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
                if (response is ApiResponse.Success) {
                    Snackbar.make(binding.root, "${response.message}", Snackbar.LENGTH_LONG).show()
                }
                if (response is ApiResponse.Error) {
                    Snackbar.make(binding.root, "${response.message}", Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }
}