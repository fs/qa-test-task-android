package com.flatstack.qatesttask

import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
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
import com.flatstack.qatesttask.util.LanguageViewConfigurator
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    lateinit var toolbar: androidx.appcompat.widget.Toolbar

    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toolbar = binding.toolbar
        val btnConfirmBinding = BtnConfirmBinding.inflate(layoutInflater)
        toolbar.apply {
            this.addView(btnConfirmBinding.root, androidx.appcompat.widget.Toolbar.LayoutParams(Gravity.END))
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
    private fun setViewConfiguration() {
        val viewConfigurator: LanguageViewConfigurator = get()
        binding.root.children.forEach {
            viewConfigurator.configureView(it)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            AppCompatDelegate.setDefaultNightMode(
                if (AppCompatDelegate.getDefaultNightMode() != AppCompatDelegate.MODE_NIGHT_YES)
                    AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
            )
        }
        return super.onKeyDown(keyCode, event)
    }
}
