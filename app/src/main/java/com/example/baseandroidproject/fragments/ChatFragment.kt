package com.example.baseandroidproject.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.adapters.ChatMessageAdapter
import com.example.baseandroidproject.base.BaseFragment
import com.example.baseandroidproject.viewModels.ChatViewModel
import com.example.tbcacademyhomework.databinding.FragmentChatBinding
import kotlinx.coroutines.launch

class ChatFragment : BaseFragment<FragmentChatBinding>(FragmentChatBinding::inflate) {

    private val viewModel: ChatViewModel by viewModels()
    private val adapter = ChatMessageAdapter()

    override fun setup() {
        setUpRecycler()
        observer()
        searchQUery()
    }

    private fun setUpRecycler() {
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter
    }

    private fun observer() {
        lifecycleScope.launch {
            viewModel.messages.collect { messages ->
                adapter.submitList(messages)
            }
        }
    }

    private fun searchQUery(){
        binding.ivSearch.setOnClickListener {
            viewModel.search(binding.etQuery.text.toString())
        }
    }

}