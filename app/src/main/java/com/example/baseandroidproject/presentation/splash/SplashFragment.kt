package com.example.baseandroidproject.presentation.splash

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.databinding.FragmentSplashBinding
import com.example.baseandroidproject.presentation.base.BaseFragment
import com.example.baseandroidproject.presentation.utils.launchRepeatLifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel : SplashViewModel by viewModels()

    override fun setup() {
        observeToken()
    }

    override fun listeners() {
    }

    private fun observeToken(){
        launchRepeatLifecycleScope {
            viewModel.navigationFlow.collectLatest{ state ->
                when(state){
                    is Navigation.HomeScreen -> navigateToHome()
                    is Navigation.LoginScreen -> navigateToLogin()
                    Navigation.Idle -> {

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

