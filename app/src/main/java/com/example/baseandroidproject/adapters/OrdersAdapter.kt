package com.example.baseandroidproject.adapters

import android.graphics.Color
import android.provider.CalendarContract.Colors
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.data.Order
import com.example.baseandroidproject.data.OrderStatus
import com.example.baseandroidproject.databinding.OrderItemBinding

private class OrderDiffUtils : DiffUtil.ItemCallback<Order>() {
    override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.id == newItem.id && oldItem.status == newItem.status
    }

    override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.id == newItem.id && oldItem.status == newItem.status
    }
}

class OrdersAdapter(private val onReviewClick : (String) -> Unit) : ListAdapter<Order,OrdersAdapter.ActiveOrderVH>(OrderDiffUtils()) {

    inner class ActiveOrderVH(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(order: Order) {
            with(binding) {

                tvProductName.text = order.name

                tvProductColor.text = order.color

                tvProductQuantity.text = "| Qty = ${order.quantity}"

                tvProductPrice.text = "$" + order.price
                tvColorCircle.background.setTint(Color.parseColor(order.color))
                order.orderReview = null

                when(order.status){
                    OrderStatus.Active -> {
                        tvProductStatus.text = "In Delivery"
                        btnReview.text = "Track Order"
                    }

                    else -> {
                        tvProductStatus.text = "Completed"
                        btnReview.text = "Leave Review"
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveOrderVH {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActiveOrderVH(binding)
    }


    override fun onBindViewHolder(holder: ActiveOrderVH, position: Int) {
        holder.onBind(getItem(position))
    }
}