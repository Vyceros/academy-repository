package com.example.baseandroidproject.fragments.register

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.data.responses.ApiResponse
import com.example.baseandroidproject.databinding.FragmentRegisterBinding
import com.example.baseandroidproject.viewModels.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel : RegisterViewModel by viewModels()

    override fun setup() {
        observer()
    }

    override fun listeners() {
        binding.btnRegister.setOnClickListener {
            registerUser1()
        }
    }
    private fun registerUser1(){
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        viewModel.register(email,password)
    }
    private fun observer() {
        lifecycleScope.launch {
            viewModel.registerCall.collect{ response ->
                if (response is ApiResponse.Success) {
                    Snackbar.make(binding.root,"User successfuly registered",Snackbar.LENGTH_SHORT).show()
                }
                if (response is ApiResponse.Error) {
                    Snackbar.make(binding.root,"${response.message}",Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

}