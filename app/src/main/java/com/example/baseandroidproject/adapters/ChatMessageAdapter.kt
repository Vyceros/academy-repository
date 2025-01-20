package com.example.baseandroidproject.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baseandroidproject.data.enums.MessageType
import com.example.baseandroidproject.data.models.Message
import com.example.tbcacademyhomework.R
import com.example.tbcacademyhomework.databinding.MessageItemBinding

private class MessageDiffUtils : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }
}

class ChatMessageAdapter :
    ListAdapter<Message, ChatMessageAdapter.MessageViewHolder>(MessageDiffUtils()) {

    inner class MessageViewHolder(private val binding: MessageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message) {
            binding.tvMessage.text = message.lastMessage
            binding.tvMessageTime.text = message.lastActive
            binding.tvUnreadMessages.text = message.unreadMessages.toString()
            binding.tvName.text = message.owner
            binding.tvIsWriting.text = if (message.isTyping) ".." else ""
            when (message.lastMessageType) {
                MessageType.TEXT -> {
                    binding.ivMessageType.visibility = View.GONE
                }

                MessageType.VOICE -> {
                    binding.ivMessageType.visibility = View.VISIBLE
                    binding.tvUnreadMessages.visibility = View.GONE
                    binding.ivMessageType.setImageResource(R.drawable.recorder)
                }

                MessageType.FILE -> {
                    binding.ivMessageType.visibility = View.VISIBLE
                    binding.tvUnreadMessages.visibility = View.GONE
                    binding.ivMessageType.setImageResource(R.drawable.file_type)
                }
            }



            message.image?.let {
                Glide.with(binding.root.context)
                    .load(it)
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.ivImage)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            MessageItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}