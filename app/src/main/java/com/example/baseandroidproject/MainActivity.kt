package com.example.baseandroidproject

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.baseandroidproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var receiver: AirplaneModeReceiver
    private lateinit var customReceiver: CustomReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerReceiver()
        //registerCustomReceiver()
    }

    private fun registerReceiver() {
        receiver = AirplaneModeReceiver {
            binding.loader.isVisible = it
        }
        val intentFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(receiver, intentFilter)
    }

//    private fun registerCustomReceiver() {
//        customReceiver = CustomReceiver()
//        val intentFilter =
//            IntentFilter("com.warmane.wowtracker.presentation.activity.TEST_BROADCAST")
//        registerReceiver(customReceiver,intentFilter, RECEIVER_EXPORTED)
//    }

    override fun onDestroy() {
        super.onDestroy()
       unregisterReceiver(receiver)
        //unregisterReceiver(customReceiver)
    }
}