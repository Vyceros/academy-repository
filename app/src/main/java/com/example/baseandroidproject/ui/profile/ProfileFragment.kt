package com.example.baseandroidproject.ui.profile

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.data.local.AppDatabase
import com.example.baseandroidproject.data.remote.RetrofitImpl
import com.example.baseandroidproject.data.repositories.user_repository.UserRepository
import com.example.baseandroidproject.data.sessions.DataStore
import com.example.baseandroidproject.databinding.FragmentProfileBinding
import com.example.baseandroidproject.ui.base.BaseFragment
import com.example.baseandroidproject.ui.view_model_factory.ViewModelFactory
import kotlinx.coroutines.launch

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private val viewModel: ProfileViewModel by viewModels {
        ViewModelFactory {
            ProfileViewModel(
                dataStore = DataStore(requireContext().applicationContext),
                userRepository = UserRepository(
                    userService = RetrofitImpl.usersService,
                    database = AppDatabase.getInstance(requireContext().applicationContext)
                )
            )
        }
    }

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
                viewModel.logOut()
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
            }
        }

    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.userDetails.collect { user ->
                Log.d("ProfileFragment", "User details: $user")
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
}
