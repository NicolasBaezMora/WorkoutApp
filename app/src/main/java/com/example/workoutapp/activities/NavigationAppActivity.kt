package com.example.workoutapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.example.workoutapp.R
import com.example.workoutapp.databinding.ActivityNavigationAppBinding

class NavigationAppActivity : AppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var activityNavigationAppBinding: ActivityNavigationAppBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNavigationAppBinding = ActivityNavigationAppBinding.inflate(layoutInflater)
        setContentView(activityNavigationAppBinding.root)

        setupNavigation()

    }

    private fun setupNavigation(){
        setSupportActionBar(activityNavigationAppBinding.toolBar)
        appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.exercisesFragment,
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
        if (
            destination.id != R.id.exercisesFragment &&
            destination.id != R.id.trainingPlansFragment &&
            destination.id != R.id.motivationFragment &&
            destination.id != R.id.trainFragment
        ) activityNavigationAppBinding.bottomNavigationView.visibility = View.GONE
        else activityNavigationAppBinding.bottomNavigationView.visibility = View.VISIBLE
    }
}