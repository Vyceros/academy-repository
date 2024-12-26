package com.example.baseandroidproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.data.Address
import com.example.baseandroidproject.databinding.ItemDeliveryAddressBinding


private class AddressDiffUtil : DiffUtil.ItemCallback<Address>() {
    override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean {
        return oldItem.id == newItem.id
    }

}

class AddressAdapter :
    ListAdapter<Address, AddressAdapter.AddressViewHolder>(AddressDiffUtil()) {

    private var onAddressClick: ((Address) -> Unit)? = null

    fun onClick(listener: (Address) -> Unit) {
        this.onAddressClick = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val binding = ItemDeliveryAddressBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    inner class AddressViewHolder(private val binding: ItemDeliveryAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(address: Address) {
            with(binding) {
                ivIconImage.setImageResource(chooseIcon(address.title))
                tvLocation.text = address.title
                tvAddress.text = address.address

                root.setOnLongClickListener {
                    onAddressClick?.invoke(address)
                    true
                }

                tvEditButton.setOnClickListener {
                    onAddressClick?.invoke(address)
                }
            }
        }
    }

    private fun chooseIcon(addressTitle: String): Int {
        return if (addressTitle.contains("home", ignoreCase = true)) {
            R.drawable.home_location
        } else {
            R.drawable.random_location
        }
    }
}