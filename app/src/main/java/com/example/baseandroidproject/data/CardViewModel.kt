package com.example.baseandroidproject.data

import androidx.lifecycle.ViewModel

class CardViewModel : ViewModel() {
    private val cards = mutableListOf<Card>()

    init {
        cards.addAll(
            listOf(
                Card(
                    name = "Jane Doe",
                    cardNumber = "1234 5678 9012 3456",
                    expiryDate = "12/25",
                    cvv = "12/25",
                    cardType = CardType.Mastercard
                ),
                Card(
                    name = "Jane Doe",
                    cardNumber = "1234 5678 9012 3456",
                    expiryDate = "12/25",
                    cvv = "12/25",
                    cardType = CardType.Visa
                )
            )
        )
    }


    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getCards(): List<Card> {
        return cards
    }

    fun deleteCard(id: String) {
        val card = cards.find { it.id == id }
        cards.remove(card)
    }

}