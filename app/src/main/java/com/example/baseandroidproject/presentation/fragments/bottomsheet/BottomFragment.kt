package com.example.baseandroidproject.presentation.fragments.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.baseandroidproject.databinding.FragmentBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomFragment : BottomSheetDialogFragment() {
    private val args: BottomFragmentArgs by navArgs()

    private var _binding: FragmentBottomBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpArgs()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpArgs() {
        with(binding) {
            tvLocationTitle.text = args.title
            tvLocationAddress.text = args.address
            tvLocationCordinates.text = "Lat: ${args.lat}, Lng: ${args.lan}"
        }
    }

}