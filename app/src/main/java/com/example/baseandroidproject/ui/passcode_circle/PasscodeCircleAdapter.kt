package com.example.baseandroidproject.ui.passcode_circle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.R
import com.example.baseandroidproject.data.passcode.PasscodeCircle
import com.example.baseandroidproject.databinding.CircleItemBinding


private class PasscodeDiffUtils : DiffUtil.ItemCallback<PasscodeCircle>() {
    override fun areItemsTheSame(oldItem: PasscodeCircle, newItem: PasscodeCircle): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: PasscodeCircle, newItem: PasscodeCircle): Boolean {
        return oldItem == newItem
    }

}

class PasscodeCircleAdapter(
) : ListAdapter<PasscodeCircle, PasscodeCircleAdapter.PasscodeCircleViewHolder>(PasscodeDiffUtils()) {

    inner class PasscodeCircleViewHolder(private val binding: CircleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(circle: PasscodeCircle) {
            with(binding) {
                ivPasscode.setImageResource(
                    if (circle.fillState) {
                        R.drawable.passcode_circle_filled
                    } else R.drawable.passcode_circle
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasscodeCircleViewHolder {
        val binding = CircleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PasscodeCircleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PasscodeCircleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}