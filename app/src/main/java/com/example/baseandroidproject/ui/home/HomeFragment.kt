package com.example.baseandroidproject.ui.home

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.R
import com.example.baseandroidproject.ui.base.BaseFragment
import com.example.baseandroidproject.data.remote.retrofit.RetrofitClient
import com.example.baseandroidproject.databinding.FragmentHomeBinding
import com.example.baseandroidproject.data.local.storage.ApplicationDatabase
import com.example.baseandroidproject.ui.home.recycler.UserListAdapter
import com.example.baseandroidproject.ui.login.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory {
            HomeViewModel(
                apiSource = RetrofitClient.apiService,
                ApplicationDatabase.getInstance(requireContext().applicationContext)
            )
        }
    }
    private val adapter by lazy {
        UserListAdapter(
            toRefreshList = { onHold() }
        )
    }

    override fun setup() {
        setupRecycler()
        observer()
    }

    override fun listeners() {
        binding.ivToProfile.setOnClickListener {
            navigateToProfile()
        }
    }


    private fun observer() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                adapter.loadStateFlow.collectLatest { loadState ->
                    binding.progressBar.isVisible =
                        loadState.source.refresh is LoadState.Loading
                                || loadState.source.append is LoadState.Loading

                    if (loadState.source.append is LoadState.Error) {
                        snackbar(getString(R.string.an_error_occurred))
                        adapter.refresh()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userFlow.collectLatest { response ->
                    adapter.submitData(response)
                }
            }
        }
    }

    private fun setupRecycler() {
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter

    }

    private fun navigateToProfile() {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
    }

    private fun snackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun onHold() {
        adapter.refresh()
    }

}