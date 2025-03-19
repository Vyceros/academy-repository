package com.example.baseandroidproject.presentation.screen.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.databinding.CategoryItemBinding
import com.example.baseandroidproject.presentation.screen.models.CategoryUi

private class DiffUtils() : ItemCallback<CategoryUi>() {
    override fun areItemsTheSame(oldItem: CategoryUi, newItem: CategoryUi): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: CategoryUi, newItem: CategoryUi): Boolean {
        return newItem == oldItem
    }

}

class CategoryAdapter : ListAdapter<CategoryUi, CategoryAdapter.CategoryViewHolder>(DiffUtils()) {

    inner class CategoryViewHolder(private val binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val indicatorAdapter by lazy {
            CategoryChildIndicator()
        }

        init {
            binding.indicatorRecycler.apply {
                layoutManager = LinearLayoutManager(
                    binding.root.context,
                    LinearLayoutManager.HORIZONTAL, false
                )
                adapter = indicatorAdapter
                itemAnimator = null
            }
        }

        fun bind() {
            with(binding) {
                val item = getItem(absoluteAdapterPosition)
                indicatorAdapter.submitList(item.children)
                indicatorRecycler.isVisible = item.children.isNotEmpty()
                tvName.text = item.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind()
    }


}