package com.example.baseandroidproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidproject.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        onGoBackButton()
        setUp()
        onLogginButtonClick()
    }
    private fun onGoBackButton(){
        binding.btnGoBack.setOnClickListener{
            finish()
        }
    }
    private fun onLogginButtonClick(){
        binding.btnLogin.setOnClickListener {
            loginUser()
        }

    }

    private fun loginUser() {
        val email = binding.etLogInEmail.text.toString()
        val password = binding.etLogInPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Login failed: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUp(){
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            binding.etLogInEmail.setText(email)
            binding.etLogInPassword.setText(password)
        }
    }
}
