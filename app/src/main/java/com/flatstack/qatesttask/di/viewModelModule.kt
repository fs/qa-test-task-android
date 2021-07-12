package com.flatstack.qatesttask.di

import com.flatstack.qatesttask.feature.news.NewsFragmentViewModel
import com.flatstack.qatesttask.feature.settings.SettingsViewModel
import com.flatstack.qatesttask.feature.splash_screen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewsFragmentViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { SplashScreenViewModel(get()) }
}
