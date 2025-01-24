package com.example.baseandroidproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.baseandroidproject.databinding.ActivityMainBinding
import com.example.baseandroidproject.sessions.UserSessions


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment : NavHostFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSession()
    }

    private fun setupSession(){
        navHostFragment = supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        val controller = navHostFragment.navController
        val sessionManager = UserSessions(applicationContext)
        val token = sessionManager.returnToken()

        val startDestination = if(token != null){
            R.id.homeFragment
        }else{
            R.id.loginFragment

        }

        val navGraph = controller.navInflater.inflate(R.navigation.nav_graph)
        navGraph.setStartDestination(startDestination)
        controller.graph = navGraph
      }
}
