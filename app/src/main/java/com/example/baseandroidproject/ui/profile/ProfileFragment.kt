package com.example.baseandroidproject.ui.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.databinding.FragmentProfileBinding
import com.example.baseandroidproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private val viewModel: ProfileViewModel by viewModels()

    override fun setup() {
        observeData()
    }

    override fun listeners() {
        with(binding) {
            btnSave.setOnClickListener {
                saveDetails()
            }

            btnLoad.setOnClickListener {
                viewModel.loadUserDetails()
            }

            btnLogout.setOnClickListener {
                logout()
            }
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userDetails.collect { user ->
                user?.let {
                    with(binding) {
                        etEmail.setText(it.email)
                        etFirstName.setText(it.firstName)
                        etLastName.setText(it.lastName)
                    }
                }
            }
        }
    }

    private fun saveDetails() {
        with(binding) {
            val email = etEmail.text.toString()
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()

            viewModel.updateUserDetails(email, firstName, lastName)
        }
    }

    private fun logout(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.logOut()
            delay(100)
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
        }
    }
}
