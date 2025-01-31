package com.example.baseandroidproject.generics


//region WHY DO WE NEED THEM

//fun main() {
//    /**
//     *NOT TYPE SAFE
//     * TYPE IS NOT ENFORCED AT COMPILE TIME.
//     * DOESNT GIV ERRORS HERE BUT WILL THROW AN EXCEPTION IN RUNTIME
//     * **/
//
//    val anyBox = AnyBox()
//    anyBox.setValue(1)
//    val anyValue = anyBox.getValue()
//
//    /**
//     * TYPE SAFE
//     * TYPE IS STRICTLY ENFORCED AT COMPILE TIME
//     * NO ROOM FOR ERROR IN RUNTIME
//     * **/
//    val genericBox = Box<String>()
//    genericBox.setValue("Rearar")
//    val genericValue = genericBox.getValue()
//
////No need for type casting
//
//
//}

class Box<T> {
    private var value: T? = null

    fun setValue(value: T) {
        this.value = value
    }

    fun getValue(): T? {
        return value
    }
}

class AnyBox {
    private var value: Any? = null

    fun setValue(value: Any) {
        this.value = value
    }

    fun getValue(): Any? {
        return value
    }
}

//endregion

//region interfaces
//fun main() {
//    CarFactory().apply {
//        this.getById(1).also { println(it) }
//        this.getAll().also { println(it) }
//    }
//
//}

interface Factory<T> {
    fun getById(id: Int): T
    fun getAll(): List<T>
}

class CarFactory : Factory<Car> {
    private val cars = listOf(Car(1, "Toyota"))

    override fun getById(id: Int): Car {
        return cars.first { it.id == id }
    }

    override fun getAll(): List<Car> {
        return cars
    }
}

data class Car(val id: Int, val name: String)


//endregion

//region methods

//fun main() {
//    val result = multipleTypeParams("Hello World") { it.substring(0, 5) }
//    println(result)
//    listOf(1,2,3).also { printList(it) }
//    listOf("One","Two","Three").also { printList(it) }
//}

fun <T, R> multipleTypeParams(item: T, action: (T) -> R): R {
    return action(item)
}

fun <T> printList(list: List<T>) {
    list.forEach { println(it) }
}

//endregion

//region type constraint

//fun main() {
//    printMax(listOf(1, 2, 3))
//    println(compareNumbers(1, 5))
//}

//upper bound
fun <T : Comparable<T>> printMax(list: List<T>) {
    val max = list.maxOrNull()
    println(max)
}

//multiple upper bounds with WHERE clause

fun <T> compareNumbers(first: T, second: T) : Boolean
        where T : Number, T : Comparable<T>{
    return first > second
}

//endregion

//region variance

//fun main(){
//    val outClass = OutClass("Im out classs")
//    val refToOut : OutClass<Any> = outClass
//
//    val inClass = InClass<Number>()
//    val refToIn : InClass<Double> = inClass
//
//
//    val invariantGeneric = GenericClass("Im a string")
//    val refToInvariant : GenericClass<Any> = invariantGeneric
//}

//region In Contravariance

/**in - consumer of T, can only consume the value of T but not return it **/
class InClass< in T>{
    fun convertToString(item: T) : String{
        return item.toString()
    }
}
// endregion

//region Invariance
/** By default generics are INVARIANT, meaning they dont have any supertype/subtype implementation
 * available
 */

class GenericClass<out T>(val value : T){
    fun foo() : T{
        return value
    }
}


// endregion

//region Out Covariance
    //out - producer of T, can only produce/return the value of T but not consume it
    class OutClass<out T>(val value : T){
        fun get() : T{
            return value
        }
    }


//endregion

//region reified

//fun main() {
//    val intList : List<Int> = listOf(1,2,3)
//    val stringList : List<String> = listOf("one","two","tthree")
//    checkTypeAtRuntime<String>(stringList)
//
//    //type erased at runtime, List<Int> becomes just ArrayList
//    println(intList.javaClass)
//}

/** Reified implementation. By declaring the generic as reified, it will be available at runtime
 **/
inline fun <reified T> checkTypeAtRuntime(list: List<T>){
    if (T::class == String::class){
        println("List is of type String")
    }
}
//endregion






