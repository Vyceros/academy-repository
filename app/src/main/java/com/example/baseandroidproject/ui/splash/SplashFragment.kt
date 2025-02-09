package com.example.baseandroidproject.ui.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.data.repositories.UserDetailsRepository
import com.example.baseandroidproject.databinding.FragmentSplashBinding
import com.example.baseandroidproject.storage.ApplicationDatabase
import com.example.baseandroidproject.ui.login.ViewModelFactory
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    private val viewModel : SplashViewModel by viewModels {
        ViewModelFactory{
            SplashViewModel(UserDetailsRepository(
                ApplicationDatabase.getInstance(requireContext().applicationContext)
                    .userDetailsDao()
            ))
        }
    }
    override fun setup() {
        checkIfExists()
    }

    override fun listeners() {
    }

    private fun checkIfExists(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.checkForToken{ exists ->
                if (exists){
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
                }else{
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                }
            }
        }
    }

}

