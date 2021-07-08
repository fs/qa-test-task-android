package com.flatstack.qatesttask.feature.viewmodel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListFragmentViewModel(get()) }
}
