package com.example.baseandroidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidproject.databinding.ActivityMainBinding
import com.example.baseandroidproject.fragments.MessageFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            initialSetup()
        }
    }

    private fun initialSetup() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main, MessageFragment())
            setReorderingAllowed(true)
            commit()
        }
    }
}