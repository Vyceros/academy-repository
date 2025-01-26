package com.example.baseandroidproject.fragments.profile

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.databinding.FragmentProfileBinding
import com.example.baseandroidproject.fragments.login.ViewModelFactory
import com.example.baseandroidproject.sessions.DataStore
import kotlinx.coroutines.launch

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels{
        ViewModelFactory {
            ProfileViewModel(DataStore(requireContext().applicationContext))
        }
    }
    override fun setup() {
        observer()
    }

    override fun listeners() {
        binding.btnMystery.setOnClickListener {
            mysteryClick()
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(ProfileFragmentDirections.actionHomeFragmentToLoginFragment())
        }
    }

    private fun observer(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.retrieveUserEmail().collect{ email ->
                    binding.tvUserEmail.text = email
                }
            }
        }
    }

    private fun mysteryClick() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ")
            )
        )
    }



}