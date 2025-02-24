package com.example.baseandroidproject.presentation.home.adapters

import ImageAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baseandroidproject.databinding.PostItemXmlBinding
import com.example.baseandroidproject.presentation.models.Post

class PostAdapter : ListAdapter<Post, PostAdapter.PostViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostItemXmlBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PostViewHolder(private val binding: PostItemXmlBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            with(binding){
                tvUsername.text = post.fullName
                tvTimestamp.text = post.postDate
                tvMessage.text = post.shareContent
                tvComments.text = post.comments
                tvLikes.text = post.likes
                Glide.with(binding.root).load(post.profilePicture).into(ivUserAvatar)
            }

            if (post.images.isNotEmpty()) {
                binding.rvImages.visibility = View.VISIBLE
                val imageAdapter = ImageAdapter()
                binding.rvImages.apply {
                    layoutManager = GridLayoutManager(context, 2)
                    adapter = imageAdapter
                }
                imageAdapter.submitList(post.images)
            } else {
                binding.rvImages.visibility = View.GONE
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean = oldItem == newItem
    }
}
