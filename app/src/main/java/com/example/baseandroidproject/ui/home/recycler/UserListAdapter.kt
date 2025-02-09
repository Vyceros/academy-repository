package com.example.baseandroidproject.ui.home.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baseandroidproject.R
import com.example.baseandroidproject.data.users.UserDto
import com.example.baseandroidproject.databinding.UserItemBinding

private class UserDiffUtils : DiffUtil.ItemCallback<UserDto>() {
    override fun areItemsTheSame(oldItem: UserDto, newItem: UserDto): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UserDto, newItem: UserDto): Boolean {
        return oldItem == newItem
    }

}

class UserListAdapter(private val toRefreshList : () -> Unit) : PagingDataAdapter<UserDto, UserListAdapter.UserViewHolder>(
    UserDiffUtils()
) {

    inner class UserViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userDto : UserDto) {
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