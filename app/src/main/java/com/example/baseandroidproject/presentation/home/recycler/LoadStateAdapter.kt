package com.example.baseandroidproject.presentation.home.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.databinding.LoadingItemBinding

class UserLoadStateAdapter() : LoadStateAdapter<UserLoadStateAdapter.UserLoadStateViewHolder>() {
    inner class UserLoadStateViewHolder(private val binding: LoadingItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(loadState: LoadState){
            with(binding){
                progressBar.isVisible = loadState is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: UserLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): UserLoadStateViewHolder {
        val binding = LoadingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserLoadStateViewHolder(binding)
    }

}