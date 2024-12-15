package com.example.baseandroidproject

import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidproject.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onRegisterButton()
        onLoginButton()

    }



    private fun onRegisterButton(){
        binding.btnRegister.setOnClickListener {
            val intents = Intent(this,RegisterActivity::class.java)
            startActivity(intents)
        }
    }

    private fun onLoginButton(){
        binding.btnLogin.setOnClickListener {
            val intents = Intent(this,LoginActivity::class.java)
            startActivity(intents)
        }
    }
}