package com.example.baseandroidproject.presentation.screen.dialog

import android.os.Bundle
import androidx.fragment.app.setFragmentResult
import com.example.baseandroidproject.databinding.FragmentPhotoPickerBinding
import com.example.baseandroidproject.presentation.base.BaseBottomFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoPickerFragment : BaseBottomFragment<FragmentPhotoPickerBinding>(FragmentPhotoPickerBinding::inflate){
    override fun setup() {
    }

    override fun listeners() {
        binding.btnTakePhoto.setOnClickListener {
            setCamera()
        }
        binding.btnChoosePhoto.setOnClickListener {
            setGallery()
        }
    }

    private fun setCamera(){
        val data = Bundle().apply {
            putString(RESULT_DATA_KEY,"CAMERA")
        }
        setFragmentResult(FRAGMENT_REQUEST_KEY,data)
        dismiss()
    }

    private fun setGallery(){
        val data = Bundle().apply {
            putString(RESULT_DATA_KEY,"GALLERY")
        }
        setFragmentResult(FRAGMENT_REQUEST_KEY,data)
        dismiss()
    }

    companion object{
        const val FRAGMENT_REQUEST_KEY = "fragment_request_key"
        const val RESULT_DATA_KEY = "result_data-key"
    }
}