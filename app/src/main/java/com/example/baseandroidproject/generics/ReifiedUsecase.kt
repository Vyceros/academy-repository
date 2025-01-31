package com.example.baseandroidproject.generics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

//region usecase1

//same as box analogy, to wrap the common logic in a single place

class Resource<T> {
    private var data: T? = null
    private var error: String? = null
    private var isLoading: Boolean = false

    fun setSuccess(value: T) {
        data = value
        error = null
        isLoading = false
    }

    fun setError(message: String) {
        data = null
        error = message
        isLoading = false
    }

    fun setLoading() {
        isLoading = true
    }
}


// endregion

//region usecase2

//repository patterns/CRUD operations
//generic repository interface that works with any data type
interface Repository<T> {
    suspend fun fetch(id: String): T?
    suspend fun save(item: T)
    suspend fun delete(id: String)
    suspend fun getAll(): List<T>
}


//endregion

//region usecase 3
class Analytics {
    //log events of any type in runtime
    inline fun <reified T> logEvent(event: T) {
        println(T::class.simpleName ?: "Unknown")
    }
}

//endregion

//region base class


abstract class BaseFragment<VB : ViewBinding>(
    private val inflateBinding: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

//endregion

//region vmm

@Suppress("UNCHECKED_CAST")
class ViewModelFactory<VM : ViewModel>(
    private val viewModelClass: Class<VM>,
    private val creator: () -> VM
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(viewModelClass)) {

            return creator() as T
        }
        throw IllegalArgumentException("unknown ViewModel: ${viewModelClass.name}")
    }
}

//endregion

//region mapper
//I - what we map from O- what we map to
interface Mapper<I, O> {
    fun map(input: I): O
}


data class UserEntity(val id: String, val fullName: String)
data class UserDTO(val id: String, val nameModified: String)

class UserMapper : Mapper<UserEntity, UserDTO> {
    override fun map(input: UserEntity): UserDTO {
        return UserDTO(input.id, input.fullName.substring(0,5))
    }
}


val userEntity = UserEntity("111", "luka kurashvili")
val userDTO = UserMapper().map(userEntity)

fun main(){
    println(userDTO)
    val mixedList = listOf(1,5.3,"racxa","kotlini",'c')
    val stringProvider : DataProvider<String> = StringProvider()
    val anyProvider: DataProvider<Any> = stringProvider
    val stringList = filterByType<String>(mixedList)
    println(stringList)
}
//endregion

inline fun <reified T> filterByType(list: List<Any>): List<T> {

    return list.filterIsInstance<T>()

}

interface DataProvider<out T> {
    fun getData(): T
}

class StringProvider : DataProvider<String> {
    override fun getData(): String = "Helloo bro"
}

interface DataConsumer<in T> {
    fun saveData(data: T)
}

class AnySaver : DataConsumer<Any> {
    override fun saveData(data: Any) {
        println("Saving data: $data")
    }
}