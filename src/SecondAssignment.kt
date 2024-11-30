fun main() {

    val mathematics = MathematicsOperations()

    println(mathematics.greatCommonDivisor(10,3))
    println(mathematics.leastCommonFactor(12,15))
    println(mathematics.containsDollarSign("ei1321#$$"))
    println(mathematics.recursiveEven(100))
    val flipped = mathematics.flipNumber(12)
    print(flipped)
    println(mathematics.findPalindrome(122))

}

class MathematicsOperations()
{
    fun greatCommonDivisor(intOne : Int, intTwo : Int) : Int
    {
        if(intTwo == 0)
        {
            return intOne
        }else
        {
            return greatCommonDivisor(intTwo, intOne % intTwo)
        }

    }
    fun leastCommonFactor(intOne : Int, intTwo : Int) : Int
    {
        return (intOne * intTwo) / greatCommonDivisor(intOne,intTwo)
    }
    fun leastCommonFactor2(intOne : Int, intTwo : Int) : Int
    {
        val biggerNumber = maxOf(intOne,intTwo)
        var leastCommon = biggerNumber

        while(true)
        {
            if (leastCommon % intOne == 0 && leastCommon % intTwo ==0)
            {
                return leastCommon
            }
            leastCommon++
        }

    }
    fun containsDollarSign(input : String) : Boolean
    {
        return input.contains('$')
    }
    fun recursiveEven(ceiling : Int, number : Int = 0, sum : Int = 0) : Int{
        if(number >= ceiling)
        {
            return sum
        }else if(number % 2 == 0)
        {
            return recursiveEven(ceiling,number + 1,sum + number
            )
        }else
        {
            return recursiveEven(ceiling,number + 1,sum)
        }

    }
    fun flipNumber(number : Int) : Int
    {
        var tempDigit = 0
        var tempNumber = number
        var reversed = 0
        while(tempNumber != 0)
        {

            tempDigit = tempNumber % 10

            reversed = reversed * 10 + tempDigit

            tempNumber /= 10

        }
        return reversed
    }
    fun findPalindrome(number : Int) : Boolean
    {
        return number == flipNumber(number)
    }
}