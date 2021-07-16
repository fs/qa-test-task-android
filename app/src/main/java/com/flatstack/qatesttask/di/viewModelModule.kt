package com.flatstack.qatesttask.di

import com.flatstack.qatesttask.feature.news.NewsFragmentViewModel
import com.flatstack.qatesttask.feature.settings.SettingsViewModel
import com.flatstack.qatesttask.feature.splashscreen.SplashScreenViewModel
import com.flatstack.qatesttask.feature.viewmodel.CategoryFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { NewsFragmentViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
    viewModel { SplashScreenViewModel(get()) }
    viewModel { CategoryFragmentViewModel(get(), get()) }
}
