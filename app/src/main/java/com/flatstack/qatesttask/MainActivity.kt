package com.flatstack.qatesttask

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.children
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.flatstack.qatesttask.databinding.ActivityMainBinding
import com.flatstack.qatesttask.databinding.BtnConfirmBinding
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpBroadcastReceiver()
        val toolbar = binding.toolbar
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
        setViewConfiguration()
    }
    private fun setUpBroadcastReceiver() {
        val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val volume = intent?.extras?.get("android.media.EXTRA_VOLUME_STREAM_VALUE") as Int
                AppCompatDelegate.setDefaultNightMode(
                    if (volume % 2 == 0) AppCompatDelegate.MODE_NIGHT_YES
                    else AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }
        val filter = IntentFilter().apply { addAction("android.media.VOLUME_CHANGED_ACTION") }
        registerReceiver(broadcastReceiver, filter)
    }
    private fun setViewConfiguration() {
        val viewConfigurator: LanguageViewConfigurator = get()
        binding.root.children.forEach {
            viewConfigurator.configureView(it)
        }
    }
}
