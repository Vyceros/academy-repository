package com.example.baseandroidproject.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

//Helper methods, for example to validate inputs and format input

// validate card is 16 digits and only digits
fun validateCardNumber(cardNumber: String): Boolean {
    return when {
        cardNumber.length < 16 -> false

        cardNumber.all { !it.isDigit() } -> false

        else -> {
            true
        }
    }
}

// return card in format **** **** **** ****
fun formatCardNumber(cardNumber: String): String {
    return cardNumber.chunked(4).joinToString { " " }
}

//validate that name is not empty and is only letters
fun validateName(name: String): Boolean {
    return when {
        name.isEmpty() -> false
        name.all { !it.isLetter() } -> false

        else -> {
            true
        }
    }
}

//validate that cvv is only 3 digits
fun validateCvv(cvv: String): Boolean {
    return when {
        cvv.length < 3 -> false
        cvv.all { !it.isDigit() } -> false
        else -> {
            true
        }
    }
}

//Expiry date is Long. in Unix format, it gets current date and then adds 4 years on top of that
//Because cards are expired after 4 years
fun setExpiryDate(): Calendar {
    val currentDate = Calendar.getInstance()
    val simpleDateFormat = SimpleDateFormat("MM/yy", Locale.getDefault())
    currentDate.add(Calendar.YEAR, 4)
    simpleDateFormat.format(currentDate)

    return currentDate
}