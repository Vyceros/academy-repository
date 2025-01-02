package com.example.baseandroidproject.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.data.User
import com.example.baseandroidproject.databinding.MessageItemBinding

class MessageDiffUtils : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

}

class MessageAdapter : ListAdapter<User,MessageAdapter.DefaultViewHolder>(MessageDiffUtils()) {

    inner class DefaultViewHolder(private val binding: MessageItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: DefaultViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}