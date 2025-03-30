package com.example.baseandroidproject.presentation.screen.gallery

import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.databinding.FragmentGalleryBinding
import com.example.baseandroidproject.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class GalleryFragment : BaseFragment<FragmentGalleryBinding>(FragmentGalleryBinding::inflate) {

    private val navController by lazy { findNavController() }
    private val viewModel: GalleryViewModel by viewModels()
    private var currentUri: Uri? = null

    private val imageSelectListener =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                try {
                    Log.d("GalleryFragment", "Selected URI: $it")

                    val contentResolver = requireContext().contentResolver
                    contentResolver.openInputStream(it)?.use { stream ->
                        binding.ivChosenPhoto.setImageURI(it)
                        currentUri = it
                        viewModel.onAction(GalleryEvents.CompressImage)
                    }
                } catch (e: Exception) {
                    Log.e("GalleryFragment", "Error setting image", e)
                }
            }
        }

    private val cameraResultListener =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                currentUri?.let {

                    try {
                        Log.d("GalleryFragment", "Selected URI: $it")

                        val contentResolver = requireContext().contentResolver
                        contentResolver.openInputStream(it)?.use { stream ->
                            binding.ivChosenPhoto.setImageURI(it)
                            currentUri = it
                            viewModel.onAction(GalleryEvents.CreateTempUri(it))
                            viewModel.onAction(GalleryEvents.CompressImage)
                        }
                    } catch (e: Exception) {
                        Log.e("GalleryFragment", "Error setting image", e)
                    }
                }

            }
        }

    override fun setup() {
        setupListeners()
        setupObservers()
        setupResultListener()
    }

    override fun listeners() {
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnAddPhoto.setOnClickListener {
            navController.navigate(GalleryFragmentDirections.actionGalleryFragmentToPhotoPickerFragment())
        }

        binding.btnUploadPhoto.setOnClickListener {
            viewModel.onAction(GalleryEvents.UploadImage)
        }
    }

    private fun setupResultListener() {
        setFragmentResultListener(FRAGMENT_REQUEST_KEY) { _, bundle ->
            val selection = bundle.getString("result_data-key")
            when (selection) {
                "CAMERA" -> openCamera()
                "GALLERY" -> selectFromGallery() //enums
            }
        }
    }

    private fun openCamera() {
        val photoFile = createImageFile()
        val photoUri = FileProvider.getUriForFile(
            requireContext(),
            "${requireContext().packageName}.fileprovider",
            photoFile
        )
        currentUri = photoUri
        cameraResultListener.launch(photoUri)
    }

    private fun selectFromGallery() {
        imageSelectListener.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun setupObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    state.tempUri?.let { uri ->
                        binding.ivChosenPhoto.setImageURI(uri)
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.events.collect { event ->

                }
            }
        }
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }

    companion object {
        const val FRAGMENT_REQUEST_KEY = "fragment_request_key"
    }
}





