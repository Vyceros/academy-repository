package com.example.baseandroidproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidproject.databinding.ActivitySecondRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest

class SecondRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondRegisterBinding
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        onGoBackButton()
        onButtonClick()
    }

    private fun onGoBackButton(){
        binding.btnGoBack.setOnClickListener{
            finish()
        }
    }
    private fun onButtonClick(){
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        binding.btnRegisterNext.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val user = auth.currentUser
            val changeUsername = userProfileChangeRequest {
                displayName = username
            }
            user?.updateProfile(changeUsername)
            if(username.isNotEmpty()){
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("email",email)
                intent.putExtra("password",password)
                startActivity(intent)
            }else{
                Toast.makeText(this,"you must enter username",Toast.LENGTH_SHORT).show()
            }
        }

    }
}
