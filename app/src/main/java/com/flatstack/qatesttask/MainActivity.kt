package com.flatstack.qatesttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.flatstack.qatesttask.data.guardiannews.di.guardianModule
import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.databinding.ActivityMainBinding
import com.flatstack.qatesttask.feature.viewmodel.MainActivityViewModel
import com.flatstack.qatesttask.feature.viewmodel.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidLogger()
            androidContext(this@MainActivity)
            modules(guardianModule, viewModelModule)
        }
    }

    override fun onStart() {
        super.onStart()
        // TODO: TEST
        viewModel.getSection(
            "sport",
            1,
            Language.ENGLISH,
        )
        viewModel.currentSection.observe(this) {
            println(it)
        }
    }
}
