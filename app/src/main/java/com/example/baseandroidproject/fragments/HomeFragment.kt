package com.example.baseandroidproject.fragments

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.baseandroidproject.adapters.CardViewAdapter
import com.example.baseandroidproject.data.CardViewModel
import com.example.baseandroidproject.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewAdapter: CardViewAdapter by lazy {
        CardViewAdapter()
    }

    private val viewModel: CardViewModel by viewModels()


    override fun setup() {
        viewPagerSetup()
        setupCards()
        listeners()
    }

    private fun listeners() {
        binding.btnGoBack.setOnClickListener {
            findNavController().popBackStack(this, true)
        }

        binding.btnAddNew.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToNewCardFragment())
        }
    }

    private fun viewPagerSetup() {
        binding.viewPager.adapter = viewAdapter

    }

    private fun setupCards() {
        val cards = viewModel.getCards()
        viewAdapter.submitList(cards)
    }


}