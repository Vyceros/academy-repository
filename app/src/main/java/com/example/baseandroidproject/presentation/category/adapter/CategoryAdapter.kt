package com.example.baseandroidproject.presentation.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.databinding.CategoryItemBinding
import com.example.baseandroidproject.presentation.models.CategoryUi

private class DiffUtils() : ItemCallback<CategoryUi>(){
    override fun areItemsTheSame(oldItem: CategoryUi, newItem: CategoryUi): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryUi, newItem: CategoryUi): Boolean {
        return newItem == oldItem
    }

}

class CategoryAdapter : ListAdapter<CategoryUi, CategoryAdapter.CategoryViewHolder>(DiffUtils()){

    inner class CategoryViewHolder(private val binding : CategoryItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : CategoryUi){
            with(binding){
                tvCategoryName.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}