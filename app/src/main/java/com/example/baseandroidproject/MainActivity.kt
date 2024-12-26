package com.example.baseandroidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }


    private fun setup(){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main,MainFragment())
            commit()
        }
    }

}