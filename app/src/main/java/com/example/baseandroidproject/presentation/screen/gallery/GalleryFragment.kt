package com.example.baseandroidproject.presentation.screen.gallery

import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.databinding.FragmentGalleryBinding
import com.example.baseandroidproject.presentation.base.BaseFragment

class GalleryFragment : BaseFragment<FragmentGalleryBinding>(FragmentGalleryBinding::inflate){
    override fun setup() {

    }

    override fun listeners() {
        binding.btnAddPhoto.setOnClickListener{
            findNavController().navigate(GalleryFragmentDirections.actionGalleryFragmentToPhotoPickerFragment())
        }
    }

}