package com.example.rshop.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.rshop.R
import com.example.rshop.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController: NavController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.manClothesFragment -> bottomNav.visibility = View.GONE
                R.id.womanClothesFragment -> bottomNav.visibility = View.GONE
                R.id.electronicFragment -> bottomNav.visibility = View.GONE
                R.id.jeweleryFragment -> bottomNav.visibility = View.GONE
                R.id.allProductFragment -> bottomNav.visibility = View.GONE
                R.id.productDetailsFragment -> bottomNav.visibility = View.GONE
                R.id.homeFragment -> bottomNav.visibility = View.VISIBLE


            }

        }
    }
}