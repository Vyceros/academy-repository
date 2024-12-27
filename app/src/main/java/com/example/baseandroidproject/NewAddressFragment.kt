package com.example.baseandroidproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baseandroidproject.data.Address
import com.example.baseandroidproject.data.AddressStorage
import com.example.baseandroidproject.databinding.FragmentNewAddressBinding
import com.google.android.material.snackbar.Snackbar

class NewAddressFragment : Fragment() {
    private var _binding: FragmentNewAddressBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        goBackButton()
        addNewButton()
    }

    private fun goBackButton() {
        binding.btnGoBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun addNewButton() {
        binding.btnAddNew.setOnClickListener {
            with(binding) {
                if (
                    etAddress.text.isNullOrBlank() || etTitle.text.isNullOrBlank()
                ) {
                    Snackbar.make(binding.root,
                        getString(R.string.empty_input_warning), Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }
            val address = Address(
                id = AddressStorage.generateId(),
                title = binding.etTitle.text.toString(),
                address = binding.etAddress.text.toString(),
                icon = R.drawable.back_button

            )
            AddressStorage.list.add(address)
            parentFragmentManager.setFragmentResult(
                "submitted",
                Bundle().apply { putBoolean("submitted", true) })
            parentFragmentManager.popBackStack()
        }
    }
}