package com.example.baseandroidproject.fragments.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.databinding.FragmentHomeBinding
import com.example.baseandroidproject.fragments.home.home_recycler.UserListAdapter
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel by viewModels<HomeViewModel>()
    private val adapter by lazy { UserListAdapter() }

    override fun setup() {
        viewModel.homeCall
        setupRecycler()
        observer()
    }

    override fun listeners() {
        binding.ivToProfile.setOnClickListener{
            navigateToProfile()
        }
    }

    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.homeCall.collect { response ->
                    val data = response.data
                    if (data != null) {
                        adapter.submitList(data.data)
                    }
                }
            }
        }
    }

    private fun setupRecycler(){
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

    }

    private fun navigateToProfile(){
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
    }

}