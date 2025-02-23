package com.example.baseandroidproject.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.databinding.FragmentHomeBinding
import com.example.baseandroidproject.presentation.base.BaseFragment
import com.example.baseandroidproject.presentation.home.adapters.StoryAdapter
import com.example.baseandroidproject.presentation.mappers.toPresentation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var storyAdapter: StoryAdapter
    override fun setup() {
        setupRecycler()
        observe()
    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.stories.collect{
                    storyAdapter.submitList(it?.data?.map { story -> story.toPresentation() })
                }
            }
        }
    }

    private fun setupRecycler() {
        val postRecycler = binding.postRecycler
        val storyRecycler = binding.storyRecycler
        storyAdapter = StoryAdapter()
        storyRecycler.apply {
            adapter = storyAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
}