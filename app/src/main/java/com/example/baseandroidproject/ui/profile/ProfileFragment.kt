package com.example.baseandroidproject.ui.profile

import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.databinding.FragmentProfileBinding
import com.google.android.material.snackbar.Snackbar

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {



    override fun setup() {
    }

    override fun listeners() {

    }


    private fun statusMessage(status: String) {
        Snackbar.make(binding.root, status, Snackbar.LENGTH_SHORT).show()
    }
}