package com.example.baseandroidproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidproject.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        onRegisterButton()
        onLoginButton()
        setUp()
    }

    override fun onStop() {
        super.onStop()
        auth.signOut()
    }

    private fun onRegisterButton() {
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onLoginButton() {
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun setUp(){
        val user = auth.currentUser
        if (user != null){
            binding.tvWelcomeMessage.text = getString(R.string.welcome_banner_2, user.displayName)
            binding.tvWelcomeMessage.visibility = View.VISIBLE
        }else{
            binding.tvWelcomeMessage.visibility = View.GONE
        }

    }
}
