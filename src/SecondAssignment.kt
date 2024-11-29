fun main() {

    val mathematics = MathematicsOperations()

    println(mathematics.greatCommonDivisor(10,3))
    println(mathematics.leastCommonFactor(12,15))
}

class MathematicsOperations()
{
    // e.g 12 and 8 returns 4
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
}