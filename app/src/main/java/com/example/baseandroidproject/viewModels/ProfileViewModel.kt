package com.example.baseandroidproject.viewModels

import androidx.lifecycle.ViewModel
import com.example.baseandroidproject.data.ProfileDto
import kotlinx.serialization.json.Json

class ProfileViewModel : ViewModel() {
    private val listOfMap = mutableListOf<Map<Int?, String>>()
    val inputMap = mutableMapOf<Int?, String>()

    companion object {
        const val DATA_JSON = """[
   [
      {
         "field_id":1,
         "hint":"UserName",
         "field_type":"input",
         "keyboard":"text",
         "required":false,
         "is_active":true,
         "icon":"https://jemala.png/"
      },
      {
         "field_id":2,
         "hint":"Email",
         "field_type":"input",
         "required":true,
         "keyboard":"text",
         "is_active":true,
         "icon":"https://jemala.png/"
      },
      {
         "field_id":3,
         "hint":"phone",
         "field_type":"input",
         "required":true,
         "keyboard":"number",
         "is_active":true,
         "icon":"https://jemala.png/"
      }
   ],
   [
      {
         "field_id":4,
         "hint":"FullName",
         "field_type":"input",
         "keyboard":"text",
         "required":true,
         "is_active":true,
         "icon":"https://jemala.png/"
      },
      {
         "field_id":14,
         "hint":"Jemali",
         "field_type":"input",
         "keyboard":"text",
         "required":false,
         "is_active":true,
         "icon":"https://jemala.png/"
      },
      {
         "field_id":89,
         "hint":"Birthday",
         "field_type":"chooser",
         "required":false,
         "is_active":true,
         "icon":"https://jemala.png/"
      },
      {
         "field_id":898,
         "hint":"Gender",
         "field_type":"chooser",
         "required":"false",
         "is_active":true,
         "icon":"https://jemala.png/"
      }
   ]
]"""
    }
    //decoding from json to data class
    fun parser() : List<List<ProfileDto>> {
        val json = Json{
            explicitNulls = false
        }
        return json.decodeFromString(DATA_JSON)
    }

    fun saveData() = listOfMap.add(inputMap)

    fun fetchData(id : Int?): ProfileDto? {

        return parser().flatten().find { it.fieldId == id }
    }
}