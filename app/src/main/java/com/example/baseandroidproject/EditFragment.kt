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
        val bundle = arguments
        val title = bundle?.getString("title") ?: ""
        val address = bundle?.getString("address") ?: ""

        with(binding) {
            etAddress.setText(address)
            etTitle.setText(title)
        }
    }

    private fun onGoBackButton() {
        binding.btnGoBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun onEditClick() {
        binding.btnEdit.setOnClickListener {
            val id = arguments?.getInt("id")
            val address = AddressStorage.list.find {
                it.id == id
            }
            address?.address = binding.etAddress.text.toString()
            address?.title = binding.etTitle.text.toString()

            parentFragmentManager.popBackStack()
        }
    }


}