package com.example.baseandroidproject.presentation.screen.gallery

import android.net.Uri
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

@AndroidEntryPoint
class GalleryFragment : BaseFragment<FragmentGalleryBinding>(FragmentGalleryBinding::inflate) {
    private val viewModel: GalleryViewModel by viewModels()

    override fun setup() {
        receiveImage()
        observeViewModel()
    }

    override fun listeners() {
        binding.btnAddPhoto.setOnClickListener {
            findNavController().navigate(GalleryFragmentDirections.actionGalleryFragmentToPhotoPickerFragment())
        }
    }

    private fun receiveImage() {
        setFragmentResultListener("PickedImage") { _, bundle ->
            val uriString = bundle.getString("uri")
            uriString?.let {
                val uri = Uri.parse(it)
                viewModel.onEvent(GalleryEvent.GetUri(uri))
                viewModel.onEvent(GalleryEvent.CompressImage)
            }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    state.compressedImage?.let { compressedBitmap ->
                        binding.ivChosenPhoto.setImageBitmap(compressedBitmap)
                    }
                }
            }
        }
    }
}