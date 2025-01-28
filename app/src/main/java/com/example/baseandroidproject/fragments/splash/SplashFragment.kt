package com.example.baseandroidproject.fragments.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.databinding.FragmentSplashBinding
import com.example.baseandroidproject.fragments.login.ViewModelFactory
import com.example.baseandroidproject.sessions.DataStore
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel: SplashViewModel by viewModels {
        ViewModelFactory {
            SplashViewModel(DataStore(requireContext().applicationContext))
        }
    }

    override fun setup() {
        checkForToken()
    }

    override fun listeners() {
    }

    private fun checkForToken() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tokenCall.collect { token ->
                if (!token.isNullOrEmpty()) {
                    findNavController().navigate(
                        SplashFragmentDirections.actionSplashFragmentToHomeFragment(
                            token
                        )
                    )
                } else {
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                }
            }
        }
    }

}

