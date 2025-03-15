package com.example.baseandroidproject.presentation.profile

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.databinding.FragmentProfileBinding
import com.example.baseandroidproject.presentation.base.BaseFragment
import com.example.baseandroidproject.presentation.utils.launchRepeatLifecycleScope
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private val viewModel: ProfileViewModel by viewModels()

    override fun setup() {
        observeData()
    }


    override fun listeners() {
        with(binding) {

            btnLoad.setOnClickListener {
                viewModel.loadUserDetails()
            }

            btnLogout.setOnClickListener {
                viewModel.logout()
            }
        }
    }

    private fun observeData() {
        launchRepeatLifecycleScope {
            viewModel.userDetails.collect { user ->
                user.let {
                    with(binding) {
                        etEmail.setText(it)
                    }
                }
            }
        }

        launchRepeatLifecycleScope {
            viewModel.logoutEvent.collect{ state ->
                when(state){
                    ProfileEvent.Logout -> {
                        logout()
                    }
                }

            }
        }
    }

    private fun logout(){
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginFragment())
    }
}
