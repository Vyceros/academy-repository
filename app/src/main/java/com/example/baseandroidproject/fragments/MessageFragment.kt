package com.example.baseandroidproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseandroidproject.adapters.MessageAdapter
import com.example.baseandroidproject.data.Storage
import com.example.baseandroidproject.data.User
import com.example.baseandroidproject.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageAdapter by lazy {
        MessageAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        listeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecycler() {
        binding.recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = messageAdapter

        }
    }

    private fun listeners() {
        binding.btnBack.setOnClickListener {
            requireActivity().finish()
        }

        binding.btnSendText.setOnClickListener {
            onSendButton()
        }
    }

    private fun onSendButton() {
        val user = User(
            messageBody = binding.etInputText.text.toString(),
            messageDate = System.currentTimeMillis(),
            position = Storage.messageList.size
        )
        Storage.sendMessage(user)

        messageAdapter.submitList(Storage.messageList.toMutableList())
        binding.etInputText.text?.clear()
    }
}