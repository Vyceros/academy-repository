package com.example.baseandroidproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.data.Order
import com.example.baseandroidproject.data.OrderStatus
import com.example.baseandroidproject.databinding.OrderItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

private class OrderDiffUtils : DiffUtil.ItemCallback<Order>() {
    override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.orderId == newItem.orderId && oldItem.status == newItem.status
    }

    override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.orderId == newItem.orderId && oldItem.status == newItem.status
    }
}

class OrderAdapter : ListAdapter<Order,OrderAdapter.OrderViewHolder>(OrderDiffUtils()) {

    private var onDetailClick : ((Order) -> Unit)? = null

    fun onDetail(listener : (Order) -> Unit){
        this.onDetailClick = listener
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
                tvOrderDate.text = SimpleDateFormat("yy-MM-dd",Locale.getDefault()).format(order.date)
                tvOrderQuantity.text = order.quantity.toString()
                tvOrderNumber.text = itemView.context.getString(R.string.order_id,order.orderId.toString().substring(0,8))
                tvOrderTracking.text =
                    itemView.context.getString(R.string.tracking_number, order.trackNumber)
                tvOrderTotalPrice.text = itemView.context.getString(R.string.subtotal, order.totalPrice.toString())
                tvOrderStatus.text = order.status.toString()
            }

            binding.btnOrderDetails.setOnClickListener {
                onDetailClick?.invoke(order)
            }
        }

    }

}