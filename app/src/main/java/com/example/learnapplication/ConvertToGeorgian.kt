package com.example.learnapplication

class ConvertToGeorgian {
    fun convertToGeorgian(input : Int) : String
    {
        if(input >= 1000) return "1000-ის იქით თვლა არ ვიცით"
        if(input % 10 == 0 && input < 100) return tenMultiples[input / 10]
        return when{
            input < 10 -> singleDigits[input]
            input < 20 -> twoDigits[input - 10] // მაგ, 19 თუა 19 - 10, 9 ინდექსზეა 19 მასივში
            input < 100 -> {
                val onesPlace = input % 10
                val tensPlace = input / 10
                if(input / 10 == 3 || input / 10 == 5 || input / 10 == 7|| input / 10 == 9)
                {
                    "${tenCompounds[tensPlace]}${twoDigits[onesPlace]}"
                }
                else{
                    "${tenCompounds[tensPlace]}${singleDigits[onesPlace]}"
                }
            }
            else -> {
                val hundredsPlace = input / 100
                val remainder = input % 100

                if (remainder == 0){
                    return threeDigits[hundredsPlace]
                }else
                {
                    val whateversLeft = convertToGeorgian(remainder)
                    "${threeDigits[hundredsPlace]} $whateversLeft"
                }

            }
        }
    }
}