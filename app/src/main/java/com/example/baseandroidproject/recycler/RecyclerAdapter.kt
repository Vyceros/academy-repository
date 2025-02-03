package com.example.baseandroidproject.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baseandroidproject.R
import com.example.baseandroidproject.databinding.UserItemsBinding
import com.example.baseandroidproject.persistence.local.UserEntity

private class UserDiffUtils : DiffUtil.ItemCallback<UserEntity>() {
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem == newItem
    }
}

class RecyclerAdapter : ListAdapter<UserEntity, RecyclerAdapter.UserViewHolder>(UserDiffUtils()) {

    inner class UserViewHolder(private val binding: UserItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserEntity) {
            with(binding) {
                tvName.text = "${user.firstName} ${user.lastName}"
                tvAboutMessage.text = user.about
                tvStatus.text = when {
                    user.activationStatus.toDouble() < 1 -> "Not Activated"
                    user.activationStatus.toDouble() == 1.0 -> "Activated"
                    user.activationStatus.toDouble() == 2.0 -> "Active minutes ago"
                    user.activationStatus.toDouble() in 3.0..23.0 -> "Active few hours ago"
                    else -> "Active long time ago"
                }

                Glide.with(ivAvatar)
                    .load(user.avatar)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(ivAvatar)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = UserItemsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}