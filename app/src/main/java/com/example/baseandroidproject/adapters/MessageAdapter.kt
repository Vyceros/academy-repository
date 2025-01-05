package com.example.baseandroidproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.R
import com.example.baseandroidproject.data.User
import com.example.baseandroidproject.databinding.LeftMessageItemBinding
import com.example.baseandroidproject.databinding.RightMessageItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class MessageDiffUtils : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}

class MessageAdapter : ListAdapter<User, RecyclerView.ViewHolder>(MessageDiffUtils()) {

    companion object {
        private const val SENDER_MESSAGE_BUBBLE = 1
        private const val RECEIVER_MESSAGE_BUBBLE = 2
    }

    inner class DefaultViewHolder(private val binding: RightMessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: User) {
            binding.tvMessageText.text = user.messageBody
            binding.tvDate.text = itemView.context.getString(
                R.string.today,
                SimpleDateFormat("hh:mm a", Locale.getDefault()).format(user.messageDate)
            )
        }

    }

    inner class ReceiverViewHolder(private val binding: LeftMessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(user: User) {
            binding.tvMessageText.text = user.messageBody
            binding.tvDate.text = itemView.context.getString(
                R.string.today,
                SimpleDateFormat("hh:mm a", Locale.getDefault()).format(user.messageDate)
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == SENDER_MESSAGE_BUBBLE) DefaultViewHolder(
            RightMessageItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        ) else ReceiverViewHolder(
            LeftMessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) SENDER_MESSAGE_BUBBLE else RECEIVER_MESSAGE_BUBBLE

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is DefaultViewHolder) holder.onBind(getItem(position)) else if (holder is ReceiverViewHolder) holder.onBind(getItem(position))
    }

}