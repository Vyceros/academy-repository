package com.example.baseandroidproject.ui.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.data.local.repos.UserDetailsRepository
import com.example.baseandroidproject.data.local.storage.ApplicationDatabase
import com.example.baseandroidproject.databinding.FragmentProfileBinding
import com.example.baseandroidproject.ui.base.BaseFragment
import com.example.baseandroidproject.ui.login.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private val viewModel: ProfileViewModel by viewModels {
        ViewModelFactory {
            ProfileViewModel(
                UserDetailsRepository(
                    ApplicationDatabase.getInstance(requireContext().applicationContext)
                        .userDetailsDao()
                )
            )
        }
    }

    override fun setup() {
        observer() // Set up UI observation
    }

    override fun listeners() {
        binding.btnLogout.setOnClickListener {
            logout()
        }

        binding.btnSave.setOnClickListener {
            saveDetails()
        }

        binding.btnLoad.setOnClickListener {
            loadDetails()
        }
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userDetails.collect { userDetails ->
                    userDetails?.let {
                        binding.etFirstName.setText(it.firstName)
                        binding.etLastName.setText(it.lastName)
                        binding.etEmail.setText(it.email)
                    }
                }
            }
        }
    }

    private fun loadDetails() {
        viewModel.loadUserDetails()
    }

    private fun saveDetails() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val email = binding.etEmail.text.toString()

        viewModel.saveProfileDetails(firstName, lastName, email)
    }

    private fun logout() {
        viewModel.logout()
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
    }

    private fun statusMessage(status: String) {
        Snackbar.make(binding.root, status, Snackbar.LENGTH_SHORT).show()
    }
}
