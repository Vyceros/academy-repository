package com.example.baseandroidproject.ui.register

import android.os.Bundle
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.databinding.FragmentRegisterBinding
import com.example.baseandroidproject.ui.base.BaseFragment

class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {


    override fun setup() {
    }

    override fun listeners() {
    }

    private fun fragmentResult() {
        setFragmentResult("registration", Bundle().apply {
            putString("email", binding.etEmail.text.toString())
            putString("password", binding.etPassword.text.toString())
        })
        findNavController().navigateUp()
    }
}