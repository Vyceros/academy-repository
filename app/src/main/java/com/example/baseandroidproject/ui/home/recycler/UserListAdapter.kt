package com.example.baseandroidproject.ui.home.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baseandroidproject.R
import com.example.baseandroidproject.databinding.UserItemBinding
import com.example.baseandroidproject.storage.user_list.UserEntity

private class UserDiffUtils : DiffUtil.ItemCallback<UserEntity>() {
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem == newItem
    }

}

class UserListAdapter(private val toRefreshList : () -> Unit) : PagingDataAdapter<UserEntity, UserListAdapter.UserViewHolder>(
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

            binding.root.setOnLongClickListener {
                toRefreshList.invoke()
                true
            }
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