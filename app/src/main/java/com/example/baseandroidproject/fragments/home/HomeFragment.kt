package com.example.baseandroidproject.fragments.home

import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.databinding.FragmentHomeBinding
import com.example.baseandroidproject.base.BaseFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private lateinit var navController : NavController

    override fun setup() {
        navController = findNavController()
    }

    override fun listeners() {
        binding.btnLogin.setOnClickListener {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }

        binding.btnRegister.setOnClickListener {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToRegisterFragment())
        }
    }

}