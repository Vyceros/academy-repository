package com.example.baseandroidproject.ui.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.databinding.FragmentSplashBinding
import com.example.baseandroidproject.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel : SplashViewModel by viewModels()

    override fun setup() {
        observeToken()
    }

    override fun listeners() {
    }

    private fun observeToken(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
               viewModel.checkForToken {
                   if(it){
                       navigateToHome()
                   }else{
                       navigateToLogin()
                   }
               }
            }
        }
    }

    private fun navigateToHome() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
    }

    private fun navigateToLogin() {
        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
    }

}

