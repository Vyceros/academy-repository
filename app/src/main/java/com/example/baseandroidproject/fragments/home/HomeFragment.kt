package com.example.baseandroidproject.fragments.home

import android.content.Intent
import android.net.Uri
import androidx.navigation.fragment.navArgs
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val args : HomeFragmentArgs by navArgs()

    override fun setup() {
        val token = args.token
    }

    override fun listeners() {
        binding.btnMystery.setOnClickListener {
            mysteryClick()
        }

        binding.btnLogout.setOnClickListener {
            //logout
        }
    }


    private fun mysteryClick(){
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ")))
    }

    private fun saveToken(){

    }

    private fun logout(){

    }

}