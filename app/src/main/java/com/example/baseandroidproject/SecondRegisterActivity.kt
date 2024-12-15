package com.example.baseandroidproject

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.baseandroidproject.databinding.ActivitySecondRegisterBinding

class SecondRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}