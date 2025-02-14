package com.example.baseandroidproject.presentation.carousel.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.baseandroidproject.R
import com.example.baseandroidproject.data.remote.models.ImageCardDto
import com.example.baseandroidproject.databinding.ImageCardItemBinding

private class ImageDiffUtils : DiffUtil.ItemCallback<ImageCardDto>(){
    override fun areItemsTheSame(oldItem: ImageCardDto, newItem: ImageCardDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ImageCardDto, newItem: ImageCardDto): Boolean {
        return oldItem == newItem
    }

}

class ViewPagerAdapter : ListAdapter<ImageCardDto,ViewPagerAdapter.ImageViewHolder >(ImageDiffUtils())
{
    inner class ImageViewHolder(private val binding: ImageCardItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: ImageCardDto){
            with(binding){
                tvLocation.text = item.location
                tvTitle.text = item.title
                tvReactionCount.text = item.reactionCount.toString()
                tvPrice.text = item.price.toString()
                ratingBar.rating = item.rate?.toFloat() ?: 2.0f
                Glide.with(ivBackground).load(item.cover)
                    .placeholder(R.drawable.fire_shape)
                    .error(R.drawable.fire_shape)
                    .into(ivBackground)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ImageCardItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}