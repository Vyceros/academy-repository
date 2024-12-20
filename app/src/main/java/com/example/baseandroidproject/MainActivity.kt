package com.example.baseandroidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.baseandroidproject.databinding.ActivityMainBinding
import com.example.baseandroidproject.users.User
import com.example.baseandroidproject.users.UserStorage

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.btnAddUser.setOnClickListener {
            onAddUserButton()
        }
        binding.etUserSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchUser(query ?: "doesn't exist")
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                searchUser(query ?: "doesn't exist")
                return true
            }
        })
    }

    private fun displayUsers(users: List<User>) {
        if (users.isEmpty()) {
            binding.tvUsers.text = getString(R.string.no_use_found_warning)
        } else {
            val foundUser = users.joinToString("") { user ->
                "ID ${user.id}" +
                        " First Name ${user.firstName}\n" +
                        " Last name ${user.lastName}\n" +
                        " Date ${user.birthday}\n" +
                        " Email ${user.email}\n" +
                        " Address ${user.address}\"\n"

            }
            binding.tvUsers.text = foundUser
        }

    }

    private fun searchUser(search: String) {
        val userSearch = UserStorage.searchUser(search)
        displayUsers(userSearch)
    }

    private fun onAddUserButton() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragment, SignUpFragment(), "SignUpFragment")
        transaction.addToBackStack("SignUpFragment")
        transaction.commit()
    }
}