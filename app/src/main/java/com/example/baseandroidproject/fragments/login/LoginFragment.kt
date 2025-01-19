package com.example.baseandroidproject.fragments.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.data.responses.ApiResponse
import com.example.baseandroidproject.databinding.FragmentLoginBinding
import com.example.baseandroidproject.viewModels.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {
    private val viewModel : LoginViewModel by viewModels()
    override fun setup() {
        observer()
    }

    override fun listeners() {
        binding.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser(){
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        viewModel.login(email,password)
    }

    private fun observer(){
        lifecycleScope.launch {
            viewModel.loginCall.collect{ response ->
                if (response is ApiResponse.Success) {
                    Snackbar.make(binding.root,"Login completed", Snackbar.LENGTH_SHORT).show()
                }
                if (response is ApiResponse.Error) {
                    Snackbar.make(binding.root,"${response.error}", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

}