package com.gkonstantakis.moviesapp.activities

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.gkonstantakis.moviesapp.R
import com.gkonstantakis.moviesapp.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    private lateinit var activityViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = activityViewBinding.root
        setContentView(view)
    }

    override fun onResume() {
        super.onResume()
        navigateToSearchScreen()
    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//
//        val navHostFragment =
//                supportFragmentManager.findFragmentByTag("fragment_nav") as NavHostFragment
//
//        navHostFragment.childFragmentManager.fragments[1].
//    }

    fun navigateToSearchScreen() {
        val navHostFragment =
            supportFragmentManager.findFragmentByTag("fragment_nav") as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        graph.startDestination = R.id.searchFragment
        navHostFragment.navController.graph = graph
    }
}