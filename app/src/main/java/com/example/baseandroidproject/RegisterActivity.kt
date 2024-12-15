package com.example.baseandroidproject

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidproject.databinding.ActivityRegisterBinding
import com.example.baseandroidproject.validation.validateEmail
import com.example.baseandroidproject.validation.validatePassword
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        auth = FirebaseAuth.getInstance()
        setContentView(binding.root)
        onGoBackButton()
        onButtonClick()
    }

    private fun onButtonClick(){
        binding.btnRegisterNext.setOnClickListener {
            registerUser()
        }

    }
    private fun registerUser() {
        val email = binding.etRegisterEmail.text.toString()
        val password = binding.etRegisterPassword.text.toString()

        if (validateEmail(email) && validatePassword(password)) {
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val intent = Intent(this,SecondRegisterActivity::class.java)
                    intent.putExtra("email",email)
                    intent.putExtra("password",password)
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"signup went wrong",Toast.LENGTH_SHORT).show()
                    d("firebase error",it.exception.toString())
                }
            }
        } else {
            Toast.makeText(this,"email and password are not in valid format",Toast.LENGTH_SHORT).show()
        }
    }

    private fun onGoBackButton(){
        binding.btnGoBack.setOnClickListener{
            finish()
        }
    }
}
