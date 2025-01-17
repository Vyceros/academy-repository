package com.example.baseandroidproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.data.ProfileDto
import com.example.baseandroidproject.databinding.InputGroupItemBinding

class InputItemGroupAdapter(
    private val list: List<List<ProfileDto>>,
    private val onInputChanged: (Int?, String) -> Unit
) :
    RecyclerView.Adapter<InputItemGroupAdapter.InputItemGroupViewHolder>() {

    inner class InputItemGroupViewHolder(private val binding: InputGroupItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val inputs = list[adapterPosition]
            val adapter = InputItemAdapter(inputs,onInputChanged)
            with(binding) {
                recyclerView.layoutManager = LinearLayoutManager(root.context)
                recyclerView.adapter = adapter
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InputItemGroupViewHolder {
        val binding =
            InputGroupItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return InputItemGroupViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: InputItemGroupViewHolder, position: Int) {
        return holder.bind()
    }

}