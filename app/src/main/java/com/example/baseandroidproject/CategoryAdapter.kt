package com.example.baseandroidproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.databinding.CategoryLayoutBinding

class CategoryAdapter(private val categories: List<String>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var onItemClick: ((String) -> Unit)? = null

    private var selectedCategory = 0

    fun onClickListener(listener : (String) -> Unit){
        this.onItemClick = listener
    }

    inner class CategoryViewHolder(private val binding: CategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindCategory(category: String, isSelected: Boolean) {
            binding.tvCategory.text = category
            binding.categoryContainer.setBackgroundResource(
                if (isSelected) R.drawable.category_background_selected
                else R.drawable.category_background
            )
            binding.root.setOnClickListener {

                val oldPosition = selectedCategory

                selectedCategory = adapterPosition

                notifyItemChanged(oldPosition)
                notifyItemChanged(selectedCategory)

                onItemClick?.invoke(categories[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindCategory(categories[position], position == selectedCategory)
    }
}
