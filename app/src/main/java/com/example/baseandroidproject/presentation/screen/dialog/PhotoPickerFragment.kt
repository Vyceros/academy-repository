package com.example.baseandroidproject.presentation.screen.dialog

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import com.example.baseandroidproject.databinding.FragmentPhotoPickerBinding
import com.example.baseandroidproject.presentation.base.BaseBottomFragment

class PhotoPickerFragment : BaseBottomFragment<FragmentPhotoPickerBinding>(FragmentPhotoPickerBinding::inflate){

    @RequiresApi(Build.VERSION_CODES.P)
    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val bitmap = getBitmapFromUri(it)
        }
    }

    override fun setup() {

    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun listeners() {
        binding.btnChoosePhoto.setOnClickListener {
            getContent.launch("image/*")
        }

    }
    @RequiresApi(Build.VERSION_CODES.P)
    private fun getBitmapFromUri(uri: Uri): Bitmap? {
        return try {
            val source = ImageDecoder.createSource(requireActivity().contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


}