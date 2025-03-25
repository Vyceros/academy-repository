package com.example.senderbroadcast

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.senderbroadcast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerBroadcast()
    }

    private fun registerBroadcast(){
        val intent = Intent("com.example.senderbroadcast.TEST_BROADCAST")
        intent.setPackage("com.example.baseandroidproject")
        sendBroadcast(intent)
    }


}