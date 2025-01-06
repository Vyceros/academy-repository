package com.example.baseandroidproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.R
import com.example.baseandroidproject.databinding.ImageButtonBinding

class GameAdapter(
    private val size: Int,
    private val onButtonClick: (row: Int, col: Int, button: ImageButton) -> Unit
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    inner class GameViewHolder(private val binding: ImageButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val button: ImageButton = binding.button

        fun bind(row: Int, col: Int) {
            button.setBackgroundResource(R.color.red)
            button.isClickable = true
            button.setOnClickListener { onButtonClick(row, col, button) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ImageButtonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val row = position / size
        val col = position % size
        holder.bind(row, col)
    }

    override fun getItemCount(): Int = size * size
}
