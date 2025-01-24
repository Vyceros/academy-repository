package com.example.baseandroidproject.fragments.home

import android.content.Intent
import android.net.Uri
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.databinding.FragmentHomeBinding
import com.example.baseandroidproject.sessions.UserSessions

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val safeArgs : HomeFragmentArgs by navArgs()

    override fun setup() {
        binding.tvUserEmail.text = safeArgs.email
    }

    override fun listeners() {
        binding.btnMystery.setOnClickListener {
            mysteryClick()
        }

        binding.btnLogout.setOnClickListener {
            logout()
        }
    }


    private fun mysteryClick(){
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ")))
    }

    private fun logout(){
        val sessionManager = UserSessions(requireContext().applicationContext)
        sessionManager.clearSession()
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
    }

}