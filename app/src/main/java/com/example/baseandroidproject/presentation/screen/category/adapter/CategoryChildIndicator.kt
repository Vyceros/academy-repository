package com.example.baseandroidproject.presentation.screen.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.databinding.IndicatorItemBinding
import com.example.baseandroidproject.presentation.screen.models.CategoryIndicator

private class IndicatorDiffUtils : ItemCallback<CategoryIndicator>() {
    override fun areItemsTheSame(oldItem: CategoryIndicator, newItem: CategoryIndicator): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: CategoryIndicator,
        newItem: CategoryIndicator
    ): Boolean {
        return oldItem == newItem
    }

}

class CategoryChildIndicator :
    ListAdapter<CategoryIndicator, CategoryChildIndicator.IndicatorViewHolder>(IndicatorDiffUtils()) {

    inner class IndicatorViewHolder(binding: IndicatorItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorViewHolder {
        val binding =
            IndicatorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IndicatorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IndicatorViewHolder, position: Int) {}
}