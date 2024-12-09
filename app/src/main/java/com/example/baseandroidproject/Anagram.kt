package com.example.baseandroidproject

class AnagramStorage {
    private val storage = mutableListOf<String>()

    fun saveToStorage(string: String) {
        if (string.isNotBlank()) {
            storage.add(string)
        }
    }

    fun getStorageSize(): String {
        return "Count: ${groupingWord().size}"
    }


    fun groupingWord(): List<List<String>> {
        return storage.groupBy {
            it.toCharArray().sorted().joinToString("")
        }.values.toList()
    }
}