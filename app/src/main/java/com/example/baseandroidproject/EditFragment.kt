package com.example.baseandroidproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseandroidproject.data.AddressStorage
import com.example.baseandroidproject.databinding.FragmentEditBinding

class EditFragment : Fragment() {
    private var _binding: FragmentEditBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        populateEditTextOnMove()
        onGoBackButton()
        onEditClick()
    }

    private fun populateEditTextOnMove() {
        arguments?.let {
            binding.etAddress.setText(it.getString("address",""))
            binding.etTitle.setText(it.getString("title",""))
        }
    }

    private fun onGoBackButton() {
        binding.btnGoBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun onEditClick() {
        binding.btnEdit.setOnClickListener {
            val id = arguments?.getInt("id") ?: return@setOnClickListener
            AddressStorage.list.find {
                it.id == id
            }.apply {
                binding.etAddress.text.toString()
                binding.etTitle.text.toString()
            }


            parentFragmentManager.popBackStack()
        }
    }


}