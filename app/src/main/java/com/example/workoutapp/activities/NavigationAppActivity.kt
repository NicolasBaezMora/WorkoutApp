package com.example.workoutapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.workoutapp.R
import com.example.workoutapp.databinding.ActivityNavigationAppBinding
import com.google.android.material.internal.ViewUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationAppActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var activityNavigationAppBinding: ActivityNavigationAppBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val listMainFragments = mutableListOf<Int>()

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNavigationAppBinding = ActivityNavigationAppBinding.inflate(layoutInflater)
        setContentView(activityNavigationAppBinding.root)

        listMainFragments.addAll(listOf(
            R.id.exerciseHomeFragment,
            R.id.trainingPlansFragment,
            R.id.motivationFragment,
            R.id.trainFragment
        ))
        setupNavigation()

    }

    private fun setupNavigation(){
        setSupportActionBar(activityNavigationAppBinding.toolBar)
        appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.exerciseHomeFragment,
                        R.id.trainingPlansFragment,
                        R.id.motivationFragment,
                        R.id.trainFragment
                ),
                activityNavigationAppBinding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        activityNavigationAppBinding.bottomNavigationView.setupWithNavController(navController)
        activityNavigationAppBinding.navigationViewDrawer.setupWithNavController(navController)

        navController.addOnDestinationChangedListener(this)

    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        if (destination.id in listMainFragments) activityNavigationAppBinding.bottomNavigationView.visibility = View.VISIBLE
        else activityNavigationAppBinding.bottomNavigationView.visibility = View.GONE
    }
}