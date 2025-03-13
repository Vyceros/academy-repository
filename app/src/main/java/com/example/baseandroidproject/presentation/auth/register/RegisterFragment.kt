package com.example.baseandroidproject.presentation.auth.register

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.databinding.FragmentRegisterBinding
import com.example.baseandroidproject.domain.common.Resource
import com.example.baseandroidproject.presentation.base.BaseFragment
import com.example.baseandroidproject.presentation.utils.launchRepeatLifecycleScope
import com.example.baseandroidproject.presentation.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: RegisterViewModel by viewModels()

    override fun setup() {

    }

    override fun listeners() {
        binding.btnRegister.setOnClickListener {
            registerUser()
        }

        binding.btnLogin.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setupObservers() {
        launchRepeatLifecycleScope {
            viewModel.registerEvent.collect { event ->
                when (event) {
                    is RegisterEvent.NavigateToLogin -> {
                        onSuccessfulRegistration()
                    }

                    is RegisterEvent.ShowError -> {
                        binding.root.showSnackBar(
                            requireContext(), event.message
                        )
                    }
                }

            }
        }

        launchRepeatLifecycleScope {
            viewModel.registerState.collect { state ->
                binding.loadingBar.isVisible = state is Resource.Loading
            }
        }
    }


    private fun registerUser() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        viewModel.validateAndRegister(email, password)
    }


    private fun onSuccessfulRegistration() {
        setFragmentResult(
            "registration",
            Bundle().apply {
                putString("email", binding.etEmail.text.toString())
                putString("password", binding.etPassword.text.toString())
            }
        )
        findNavController().navigateUp()
    }

}
