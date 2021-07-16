package com.flatstack.qatesttask

import android.os.Bundle
import android.view.Gravity
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.flatstack.qatesttask.databinding.ActivityMainBinding
import com.flatstack.qatesttask.databinding.BtnConfirmBinding


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toolbar =binding.toolbar
        val btnConfirmBinding = BtnConfirmBinding.inflate(layoutInflater)
        toolbar.apply {
            addView(btnConfirmBinding.root, androidx.appcompat.widget.Toolbar.LayoutParams(Gravity.END))
        }
        setSupportActionBar(toolbar)

        navController =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment)
                .navController
        val appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.newsFragment,
                R.id.categoryFragment,
                R.id.settingsFragment
            ),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )
        with(binding) {
            bottomNavigationView.setupWithNavController(navController)
        }
        setupActionBarWithNavController(navController, appBarConfiguration)

    }

}
