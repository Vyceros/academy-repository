package com.example.baseandroidproject.presentation.home

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.databinding.FragmentHomeBinding
import com.example.baseandroidproject.presentation.base.BaseFragment
import com.example.baseandroidproject.presentation.home.adapters.PostAdapter
import com.example.baseandroidproject.presentation.home.adapters.StoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var storyAdapter: StoryAdapter
    private lateinit var postAdapter: PostAdapter
    override fun setup() {
        setupRecycler()
        observe()
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.screenState.collect {
                    with(binding){
                        progressBar.isVisible = it.isLoading
                    }
                    storyAdapter.submitList(it.stories)
                    postAdapter.submitList(it.posts)
                }
            }
        }
    }

    private fun setupRecycler() {
        storyAdapter = StoryAdapter()
        postAdapter = PostAdapter()

        binding.storyRecycler.apply {
            adapter = storyAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.postRecycler.apply {
            adapter = postAdapter
            layoutManager =
                LinearLayoutManager(requireContext())
        }
    }
}