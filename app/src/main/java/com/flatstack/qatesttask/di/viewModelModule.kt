package com.flatstack.qatesttask.di

import com.flatstack.qatesttask.feature.viewmodel.NewsFragmentViewModel
import com.flatstack.qatesttask.feature.viewmodel.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewsFragmentViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
}
