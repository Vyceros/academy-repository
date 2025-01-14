package com.example.baseandroidproject.data
import androidx.lifecycle.ViewModel
import com.example.baseandroidproject.utils.setExpiryDate

class CardViewModel : ViewModel() {
    private val cards = mutableListOf<Card>()

    init {
        cards.add(Card("1234 5678 9012 3456", "John Doe", "12/25", setExpiryDate().toString(),"232",CardType.Visa))
        cards.add(Card("1234 5678 9012 3456", "John Doe", "12/25", setExpiryDate().toString(),"232",CardType.Mastercard))
    }

    private fun addCard(card : Card){
        cards.add(0,card)
    }

    fun getCards() : List<Card>{
        return cards
    }

    fun deleteCard(id : String){
        val card = cards.find { it.id == id }
        cards.remove(card)
    }


}