package com.example.baseandroidproject

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
        return oldItem.orderId == newItem.orderId && oldItem.status == newItem.status
    }

    override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.orderId == newItem.orderId && oldItem.status == newItem.status
    }
}

class OrderAdapter : ListAdapter<Order,OrderAdapter.OrderViewHolder>(OrderDiffUtils()) {

    private var onFilterClick : ((OrderStatus) -> Unit)? = null

    fun onFilter(listener : (OrderStatus) -> Unit){
        this.onFilterClick = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class OrderViewHolder(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            with(binding) {
                tvOrderDate.text = order.date.toString()
                tvOrderQuantity.text = order.quantity.toString()
                tvOrderNumber.text = itemView.context.getString(R.string.order_id,order.orderId.toString())
                tvOrderTracking.text =
                    itemView.context.getString(R.string.tracking_number, order.trackNumber)
                tvOrderTotalPrice.text = itemView.context.getString(R.string.subtotal, order.totalPrice.toString())
                tvOrderStatus.text = order.status.toString()
            }
        }

    }

}