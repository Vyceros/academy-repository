package com.example.baseandroidproject.fragments.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.databinding.FragmentProfileBinding
import com.example.baseandroidproject.fragments.login.ViewModelFactory
import com.example.baseandroidproject.sessions.ProtoDataStore
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels {
        ViewModelFactory {
            ProfileViewModel(ProtoDataStore(requireContext().applicationContext))
        }
    }

    override fun setup() {
    }

    override fun listeners() {
        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(ProfileFragmentDirections.actionHomeFragmentToLoginFragment())
        }

        binding.btnSave.setOnClickListener {
            saveDetails()
        }
        binding.btnLoad.setOnClickListener {
            observeDetailsChange()
        }
    }

    private fun observeDetailsChange() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userFlow.collectLatest { user ->
                    with(binding) {
                        tvFirstname.text = user?.firstName
                        tvLastname.text = user?.lastName
                        tvEmail.text = user?.email
                    }
                }
            }
        }
    }

    private fun saveDetails() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.updateUserDetails(
                binding.etFirstName.text.toString(),
                binding.etLastName.text.toString(),
                binding.etEmail.text.toString()
            ).collect { isSuccess ->
                if (isSuccess) {
                    statusMessage("Success")
                } else {
                    statusMessage("Error")
                }
            }
        }
    }

    private fun validateFields() {
        binding.btnSave.isEnabled = viewModel.run {
            validateEmail(binding.etEmail.text.toString()) &&
                    validateName(binding.etFirstName.text.toString()) &&
                    validateName(binding.etLastName.text.toString())
        }
    }

    private fun statusMessage(status: String) {
        Snackbar.make(binding.root, status, Snackbar.LENGTH_SHORT).show()
    }
}