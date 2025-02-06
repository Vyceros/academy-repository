package com.example.baseandroidproject.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.R
import com.example.baseandroidproject.databinding.DialpadItemBinding
import com.example.baseandroidproject.ui.dialpad.DialPad
import com.example.baseandroidproject.ui.dialpad.DialPadType


class DialPadAdapter(
    private val buttons: List<DialPad>,
    private val onItemClick: (DialPad) -> Unit
) : RecyclerView.Adapter<DialPadAdapter.DialPadViewHolder>() {

    inner class DialPadViewHolder(private val binding: DialpadItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val button = buttons[adapterPosition]
            binding.root.setOnClickListener { onItemClick(button) }

            when(button.type){
                DialPadType.Backspace -> {
                    binding.ivDialOther.isVisible = true
                    binding.tvDialNumbers.isVisible = false
                    binding.ivDialOther.setImageResource(R.drawable.backspace)

                }
                DialPadType.FingerPrint -> {
                    binding.ivDialOther.isVisible = true
                    binding.tvDialNumbers.isVisible = false
                    binding.ivDialOther.setImageResource(R.drawable.touch__id_1)
                }
                is DialPadType.Number -> {
                    binding.ivDialOther.isVisible = false
                    binding.tvDialNumbers.isVisible = true

                    binding.tvDialNumbers.text = button.type.value

                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialPadViewHolder {
        val binding = DialpadItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DialPadViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return buttons.size
    }

    override fun onBindViewHolder(holder: DialPadViewHolder, position: Int) {
        holder.bind()
    }
}