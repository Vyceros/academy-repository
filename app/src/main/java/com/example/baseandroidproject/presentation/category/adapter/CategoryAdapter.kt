package com.example.baseandroidproject.presentation.category.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.baseandroidproject.databinding.UserItemBinding
import com.example.baseandroidproject.domain.models.Category

private class DiffUtils() : ItemCallback<Category>(){
    override fun areItemsTheSame(
        oldItem: Category,
        newItem: Category
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Category,
        newItem: Category
    ): Boolean {
        return oldItem == newItem
    }

}

class CategoryAdapter : ListAdapter<Category,ViewHolder>(DiffUtils()){

    inner class ViewHolder(private val binding : UserItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Category){
            with(binding){

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}