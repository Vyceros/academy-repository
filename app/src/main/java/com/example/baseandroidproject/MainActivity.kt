package com.example.baseandroidproject

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.baseandroidproject.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val anagramStorage = AnagramStorage()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        onSaveButton()
        onResultButton()
        onClearButton()


    }


    private fun onSaveButton() {
        binding.btnSave.setOnClickListener {
            val anagram = initializeAnagram().trim()

            if (anagram.isNotEmpty()) {
                anagramStorage.saveToStorage(anagram)
            } else {
                Snackbar.make(binding.main, "empty", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun initializeAnagram(): String {
        val anagram = binding.etEnterAnagram.text.toString()

        return anagram
    }

    private fun onResultButton() {
        binding.btnOutput.setOnClickListener {
            val groupedAnagrams = anagramStorage.groupingWord()

            val resultText = if (groupedAnagrams.isNotEmpty())
            {
                groupedAnagrams.joinToString("\n ") { anagroup ->
                    "[${anagroup.joinToString(", ")}]"
                }
            }
            else {
                "nothing man"
            }

            binding.resultContainer.visibility = View.VISIBLE
            binding.inputContainer.visibility = View.GONE
            binding.tvAnagrams.text = resultText

            binding.tvAnagramCount.text = anagramStorage.getStorageSize()
        }
    }

    private fun onClearButton(){
        binding.btnClear.setOnClickListener {
            binding.resultContainer.visibility = View.GONE
            binding.inputContainer.visibility = View.VISIBLE


        }
    }

}

