
fun main() {

   var gameLoop = true

    while(gameLoop){
        println("Enter value for X")
        val stringX = readlnOrNull() ?: break
        println("Enter value for Y")
        val stringY = readlnOrNull() ?: break

        val numberX = stringX.filter { it.isDigit() }
        val numberY = stringY.filter { it.isDigit() }

        val doubleFormatX = numberX.toDoubleOrNull() ?: 0.0
        val doubleFormatY = numberY.toDoubleOrNull() ?: 0.0

        val z = doubleFormatX / doubleFormatY

        println("X divided by Y  is $z\n" +
                "Try again? <Y/N>")
        val input = readln().lowercase()

        // ending the game if the input is negative
        gameLoop = input == "y"
    }
}