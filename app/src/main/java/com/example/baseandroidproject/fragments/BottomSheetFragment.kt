package com.example.baseandroidproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.baseandroidproject.data.CardViewModel
import com.example.baseandroidproject.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private val args: BottomSheetFragmentArgs by navArgs()

    private val viewModel: CardViewModel by activityViewModels()

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun listeners() {
        val id = args.id

        binding.btnYes.setOnClickListener {
            viewModel.deleteCard(id)
            parentFragmentManager.setFragmentResult("deleted",Bundle.EMPTY)
            dismiss()
        }

        binding.btnNo.setOnClickListener {
           dismiss()
        }
    }
}