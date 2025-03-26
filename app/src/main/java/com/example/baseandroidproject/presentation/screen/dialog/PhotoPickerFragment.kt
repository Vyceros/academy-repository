package com.example.baseandroidproject.presentation.screen.dialog

import android.net.Uri
import android.os.Bundle
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.setFragmentResult
import com.example.baseandroidproject.databinding.FragmentPhotoPickerBinding
import com.example.baseandroidproject.presentation.base.BaseBottomFragment
import java.io.File

class PhotoPickerFragment : BaseBottomFragment<FragmentPhotoPickerBinding>(FragmentPhotoPickerBinding::inflate){
    private lateinit var imageUri : Uri
    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            setFragmentResult("PickedImage", Bundle().apply {
                putString("uri",uri.toString())
            })
        } else {
            dismiss()
        }
    }

    private val camera = registerForActivityResult(ActivityResultContracts.TakePicture()){
        if (it){
            setFragmentResult("PickedImage", Bundle().apply {
                putString("uri",imageUri.toString())
            })
        }


    }

    override fun setup() {
        imageUri  = createImageUri()
    }

    override fun listeners() {
        binding.btnChoosePhoto.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.btnTakePhoto.setOnClickListener {
            camera.launch(imageUri)
        }
    }

    private fun createImageUri():Uri {
        val image = File(context?.applicationContext?.filesDir,"camera_photos.png")
        return FileProvider.getUriForFile(requireContext().applicationContext,"FileProvider",image)
    }

}