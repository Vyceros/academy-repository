package com.example.baseandroidproject.fragments.home

import android.content.Intent
import android.net.Uri
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun setup() {

    }

    override fun listeners() {
        binding.btnMystery.setOnClickListener {
            mysteryClick()
        }

        binding.btnLogout.setOnClickListener {

        }
    }


    private fun mysteryClick(){
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ")))
    }

    private fun logout(){

    }

}