package com.example.baseandroidproject.fragments.splash

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.databinding.FragmentSplashBinding
import com.example.baseandroidproject.sessions.DataStore
import kotlinx.coroutines.launch

class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate){
    override fun setup() {
        checkForToken()
    }

    override fun listeners() {
    }

    private fun checkForToken(){
        val dataStore = DataStore(requireContext().applicationContext)
        viewLifecycleOwner.lifecycleScope.launch {
            dataStore.getToken().collect{token ->
                if (!token.isNullOrEmpty()){
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment(token))
                }else{
                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                }
            }

        }

    }

}