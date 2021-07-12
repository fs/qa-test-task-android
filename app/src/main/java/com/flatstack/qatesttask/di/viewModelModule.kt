package com.flatstack.qatesttask.di

import com.flatstack.qatesttask.feature.viewmodel.NewsFragmentViewModel
import com.flatstack.qatesttask.feature.viewmodel.SettingsViewModel
import com.flatstack.qatesttask.feature.viewmodel.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewsFragmentViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { SplashScreenViewModel(get()) }
}
