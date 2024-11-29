fun main() {

    val mathematics = MathematicsOperations()

    println(mathematics.greatCommonDivisor(10,3))
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
}