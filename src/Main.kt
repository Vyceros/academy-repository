
fun main() {

   var gameLoop = true

    while(gameLoop){
        println("შეიყვანე X-ის მნიშვნელობა")
        val stringX = readlnOrNull() ?: break
        println("შეიყვანე Y-ის მნიშვნელობა")
        val stringY = readlnOrNull() ?: break

        val numberX = stringX.filter{ it.isDigit() }.toDoubleOrNull() ?: 0.0
        val numberY = stringY.filter { it.isDigit() }.toDoubleOrNull() ?: 0.0

        val z : Double = numberX / numberY

        println("$numberX გაყოფილი $numberY-ზე  უდრის $z\n" +
                "ცდი ხელახლა? გთხოვთ შეიყვანოთ Y ან N >>> <Y/N>")
        val answer = readlnOrNull() ?: break

        gameLoop = answer.lowercase() == "y"
    }
}

