package com.example.baseandroidproject.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseandroidproject.data.models.MessageDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<MessageDto>?>(emptyList())
    val messages = _messages.asStateFlow()


    companion object {
        private const val JSON = """
            [
                {
                    "id": 1,
                    "image": "https://www.alia.ge/wp-content/uploads/2022/09/grisha.jpg",
                    "owner": "გრიშა ონიანი",
                    "last_message": "თავის ტერიტორიას ბომბავდა",
                    "last_active": "4:20 PM",
                    "unread_messages": 3,
                    "is_typing": false,
                    "laste_message_type": "text"
                },
                {
                    "id": 2,
                    "image": null,
                    "owner": "ჯემალ კაკაურიძე",
                    "last_message": "შემოგევლე",
                    "last_active": "3:00 AM",
                    "unread_messages": 0,
                    "is_typing": true,
                    "laste_message_type": "voice"
                },
                {
                    "id": 3,
                    "image": "https://i.ytimg.com/vi/KYY0TBqTfQg/hqdefault.jpg",
                    "owner": "გურამ ჯინორია",
                    "last_message": "ცოცხალი ვარ მა რა ვარ შე.. როდის იყო კვტარი ტელეფონზე ლაპარაკობდა",
                    "last_active": "1:00 ",
                    "unread_messages": 0,
                    "is_typing": false,
                    "laste_message_type": "file"
                },
                {
                    "id": 4,
                    "image": "",
                    "owner": "კაკო წენგუაშვილი",
                    "last_message": "ადამიანი რო მოსაკლავად გაგიმეტებს თანაც ქალი ის დასანდობი არ არი",
                    "last_active": "1:00 PM",
                    "unread_messages": 0,
                    "is_typing": false,
                    "laste_message_type": "text"
                }
            ]
        """
    }

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val messageListType =
        Types.newParameterizedType(List::class.java, MessageDto::class.java)

    private val jsonAdapter = moshi.adapter<List<MessageDto>>(messageListType)
    private var messageList: List<MessageDto>? = null

    init {
        val jsonString = JSON
        messageList = jsonAdapter.fromJson(jsonString)
        search("")
    }

    fun search(query: String) {
        viewModelScope.launch {
            val newMessages = messageList?.filter { it.owner.contains(query) }
            _messages.emit(newMessages?.toList())
        }
    }

    fun parseMessage() {
        viewModelScope.launch {
            val jsonString = JSON
            val messages = jsonAdapter.fromJson(jsonString)
            _messages.value = messages ?: emptyList()
        }
    }


}