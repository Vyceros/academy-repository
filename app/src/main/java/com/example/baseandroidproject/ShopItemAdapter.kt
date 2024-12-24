package com.example.baseandroidproject


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.data.ShopItem
import com.example.baseandroidproject.databinding.ShopItemsLayoutBinding

class ShopItemAdapter(
    private var items: List<ShopItem>
) : RecyclerView.Adapter<ShopItemAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ShopItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: ShopItem) {
            binding.apply {
                ivProduct.setImageResource(item.image)
                tvPrice.text = item.price
                tvName.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ShopItemsLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindItem(items[position])
    }

    fun filterItem(newItems: List<ShopItem>) {
        items = newItems
        notifyDataSetChanged()
    }
}
