package com.frenchquest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.frenchquest.R
import com.frenchquest.databinding.ActivityMainBinding

/**
 * Single-activity host with Navigation Component.
 * Bottom nav: Home | Progress
 * On first launch: navigates to PlacementTestFragment.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)

        // Hide bottom nav on splash, placement, and game screens
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNav.visibility = when (destination.id) {
                R.id.homeFragment, R.id.progressFragment -> android.view.View.VISIBLE
                else -> android.view.View.GONE
            }
        }
    }
}
