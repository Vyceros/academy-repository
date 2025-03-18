package com.example.baseandroidproject.presentation.category

import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.databinding.FragmentHomeBinding
import com.example.baseandroidproject.presentation.base.BaseFragment
import com.example.baseandroidproject.presentation.category.adapter.CategoryAdapter
import com.example.baseandroidproject.presentation.utils.launchRepeatLifecycleScope
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: CategoryViewModel by viewModels()
    private val categoryAdapter by lazy {
        CategoryAdapter()
    }

    override fun setup() {
        setUpRecycler()
        observe()
    }

    override fun listeners() {
        setUpListeners()
    }

    private fun setUpListeners() {
        with(binding) {
            etSearch.doOnTextChanged { text, _, _, _ ->
                viewModel.onEvent(CategoryEvents.onSearch(text.toString()))
            }
        }
    }

    private fun setUpRecycler() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = categoryAdapter
        }
    }

    private fun observe() {
        launchRepeatLifecycleScope(viewModel.uiState) { state ->
            categoryAdapter.submitList(state.data)
            binding.progressBar.isVisible = state.isLoading
        }
    }
}