package com.example.baseandroidproject.fragments.home

import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.databinding.FragmentHomeBinding
import com.example.baseandroidproject.fragments.BaseFragment
import com.example.baseandroidproject.persistence.local.AppDatabase
import com.example.baseandroidproject.persistence.remote.Resource
import com.example.baseandroidproject.persistence.remote.RetrofitClient
import com.example.baseandroidproject.recycler.RecyclerAdapter
import com.example.baseandroidproject.remote_mediator.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val adapter by lazy { RecyclerAdapter() }
    private lateinit var viewModel : HomeViewModel


    override fun setup() {
        viewModelFactory()
        setupRecycler()
        observe()
    }
    private fun viewModelFactory(){
        val database = AppDatabase.getInstance(requireContext().applicationContext)
        val dao = database.userDao()
        val userService = RetrofitClient.apiService

        viewModel = ViewModelProvider(
            this, ViewModelFactory { HomeViewModel(UserRepository(userService, dao)) }
        )[HomeViewModel::class.java]
    }
    private fun observe(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.resourceState.collectLatest { response ->
                when(response){
                    is Resource.Error -> {
                        binding.progressBar.isVisible = false
                        Snackbar.make(binding.root, response.message, Snackbar.LENGTH_LONG).show()
                    }
                    is Resource.Loading -> binding.progressBar.isVisible = true
                    is Resource.Success -> {
                        binding.progressBar.isVisible = false
                        adapter.submitList(response.data)
                    }
                }
            }
        }
    }

    private fun setupRecycler() {
            val recycler = binding.recycler
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = adapter
        }
}