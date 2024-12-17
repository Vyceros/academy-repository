package com.example.baseandroidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.baseandroidproject.UserInfo.User
import com.example.baseandroidproject.UserInfo.UserStorage
import com.example.baseandroidproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var user: User
    private lateinit var userStorage: UserStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    private fun onAddUserButton() {
        binding.btnAddUser.setOnClickListener {
            userStorage = UserStorage()
            user = User(
                firstName = binding.etFirstName.text.toString(),
                lastName = binding.etLastName.text.toString(),
                email = binding.etEmail.text.toString(),
                age = binding.etAge.text.toString()

            )
            userStorage.addUser(user)
        }
    }
}