package com.example.baseandroidproject.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.R
import com.example.baseandroidproject.data.Card
import com.example.baseandroidproject.data.CardType
import com.example.baseandroidproject.databinding.CardItemLayoutBinding
import java.util.Calendar

private class CardDiffUtil : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }
}

class CardViewAdapter(
    private val onCardClick: ((String) -> Unit)? = null
) : ListAdapter<Card, CardViewAdapter.CardViewHolder>(CardDiffUtil()) {

    inner class CardViewHolder(private val binding: CardItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(card: Card) {
            with(binding) {
                tvCardNumber.text = card.cardNumber
                tvHolderName.text = card.name
                tvValidDate.text = card.expiryDate.toString()

                when (card.cardType) {
                    CardType.Visa -> {
                        ivCard.setBackgroundResource(R.drawable.visacard)
                    }

                    CardType.Mastercard -> {
                        ivCard.setBackgroundResource(R.drawable.mastercard)
                    }
                }

                itemView.setOnLongClickListener{
                    onCardClick?.invoke(card.id)
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        return CardViewHolder(
            CardItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
