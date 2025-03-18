package com.example.baseandroidproject.presentation.category

import androidx.fragment.app.viewModels
import com.example.baseandroidproject.databinding.FragmentHomeBinding
import com.example.baseandroidproject.presentation.base.BaseFragment
import com.example.baseandroidproject.presentation.utils.launchRepeatLifecycleScope
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel : CategoryViewModel by viewModels()
    override fun setup() {

    }

    override fun listeners() {
    }


    private fun observe(){
        launchRepeatLifecycleScope(viewModel.uiState){ state ->

        }
    }
}