package com.example.baseandroidproject.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.R
import com.example.baseandroidproject.databinding.FragmentHomeBinding
import com.example.baseandroidproject.presentation.base.BaseFragment
import com.example.baseandroidproject.presentation.home.recycler.UserListAdapter
import com.example.baseandroidproject.presentation.home.recycler.UserLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()

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
                viewModel.isConnected.collect{
                    if(it){
                        binding.tvWelcomes.text = getString(R.string.connected)
                    }else{
                        binding.tvWelcomes.text = getString(R.string.offline)
                    }
                }
            }
        }

    }
    override fun listeners() {
        binding.ivToProfile.setOnClickListener{
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
        }
    }

    private fun setupRecycler(){
        val recycler = binding.recycler
        recycler.adapter = adapter.withLoadStateHeaderAndFooter(
            footer = UserLoadStateAdapter(),
            header = UserLoadStateAdapter()
        )
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

}