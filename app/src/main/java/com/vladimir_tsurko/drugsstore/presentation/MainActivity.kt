package com.vladimir_tsurko.drugsstore.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vladimir_tsurko.drugsstore.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_bar)
        val navController = findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)
    }

    fun hideBottomBar(){
        findViewById<BottomNavigationView>(R.id.bottom_navigation_bar).visibility = View.GONE
    }

    fun showBottomBar(){
        findViewById<BottomNavigationView>(R.id.bottom_navigation_bar).visibility = View.VISIBLE
    }
}