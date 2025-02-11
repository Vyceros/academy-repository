package com.example.baseandroidproject.ui.home

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.data.local.AppDatabase
import com.example.baseandroidproject.data.remote.RetrofitImpl
import com.example.baseandroidproject.data.repositories.user_repository.UserRepository
import com.example.baseandroidproject.databinding.FragmentHomeBinding
import com.example.baseandroidproject.ui.base.BaseFragment
import com.example.baseandroidproject.ui.home.recycler.UserListAdapter
import com.example.baseandroidproject.ui.view_model_factory.ViewModelFactory
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels {
        ViewModelFactory {
            HomeViewModel(
                UserRepository(
                    userService = RetrofitImpl.usersService, database =
                    AppDatabase.getInstance(requireContext().applicationContext)
                )
            )
        }
    }

    private lateinit var adapter : UserListAdapter

    override fun setup() {
        adapter = UserListAdapter()
        setupRecycler()
        observe()

    }

    private fun observe(){
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.items.collect {
                    adapter.submitData(it)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                adapter.addLoadStateListener { state ->
                    when(state.refresh){
                        is LoadState.Error -> binding.progressBar.isVisible = false
                        is LoadState.Loading -> binding.progressBar.isVisible = true
                        is LoadState.NotLoading -> binding.progressBar.isVisible = false
                    }
                }
            }
        }

    }
    override fun listeners() {
    }

    private fun setupRecycler(){
        val recycler = binding.recycler
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

}