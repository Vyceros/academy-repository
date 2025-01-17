package com.example.baseandroidproject.fragments

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.R
import com.example.baseandroidproject.adapters.InputItemGroupAdapter
import com.example.baseandroidproject.databinding.FragmentMainBinding
import com.example.baseandroidproject.viewModels.ProfileViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: ProfileViewModel by viewModels()
    private val groupAdapter by lazy {
        InputItemGroupAdapter(viewModel.parser(), onInputChanged = { fieldId, value ->
            viewModel.inputMap[fieldId] = value
        })
    }

    override fun setup() {
        binding.recycler.adapter = groupAdapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        binding.btnRegister.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        viewModel.saveData()
        Snackbar.make(binding.root, getString(R.string.data_saved), Snackbar.LENGTH_SHORT).show()
    }


}