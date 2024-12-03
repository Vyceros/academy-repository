package com.example.learnapplication

val singleDigitsEnglish = NumberArraysEnglish().singleDigit
val twoDigitsEnglish = NumberArraysEnglish().twoDigit
val tenMultiplesEnglish = NumberArraysEnglish().tenMultiples


class ConvertToEnglish {
    fun convertToEnglish(input : Int?) : String{
        if(input != null)
        {
            if(input >= 1000) return "cant count beyond 1000"
            if(input % 10 == 0 && input < 100) return tenMultiplesEnglish[input / 10]
            return when{
                input < 10 -> singleDigitsEnglish[input]
                input < 20 -> twoDigitsEnglish[input - 10]
                input < 100 -> {
                    val tensPlace = tenMultiplesEnglish[input / 10]
                    val onesPlace = singleDigitsEnglish[input % 10]
                    "$tensPlace $onesPlace"
                }

                else -> {
                    val hundredsPlace = input / 100
                    val remainder = input % 100

                    if (remainder == 0)
                    {
                        return singleDigitsEnglish[hundredsPlace] + " hundred"
                    }else
                    {
                        val remainderLeft = convertToEnglish(remainder)
                        "${singleDigitsEnglish[hundredsPlace]} hundred $remainderLeft"
                    }

                }
            }
        }
        return "Null not accepted"
    }
}
