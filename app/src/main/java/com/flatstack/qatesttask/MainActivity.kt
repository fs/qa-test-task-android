package com.flatstack.qatesttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.databinding.ActivityMainBinding
import com.flatstack.qatesttask.feature.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            Timber.d(it.toString())
        }
    }
}
