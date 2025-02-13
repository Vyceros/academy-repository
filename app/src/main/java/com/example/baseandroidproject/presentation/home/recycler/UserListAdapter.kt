package com.example.baseandroidproject.presentation.home.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baseandroidproject.R
import com.example.baseandroidproject.data.local.entities.UserEntity
import com.example.baseandroidproject.databinding.UserItemBinding

private class UserDiffUtils : DiffUtil.ItemCallback<UserEntity>() {
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem == newItem
    }

}

class UserListAdapter : PagingDataAdapter<UserEntity, UserListAdapter.UserViewHolder>(
    UserDiffUtils()
) {

    inner class UserViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userDto : UserEntity) {
            binding.tvFirstName.text = userDto.firstName
            binding.tvLastName.text = userDto.lastName
            binding.tvEmail.text = userDto.email
            binding.tvUserId.text = userDto.id.toString()
            Glide.with(binding.root)
                .load(userDto.avatar)
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.ivAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let {
            holder.bind(it)
        }
    }
}