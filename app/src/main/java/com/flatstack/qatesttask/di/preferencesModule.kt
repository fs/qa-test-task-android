package com.flatstack.qatesttask.di

import android.content.Context
import com.flatstack.qatesttask.dataStore
import com.flatstack.qatesttask.repository.PreferenceRepository
import org.koin.dsl.module

val preferencesModule = module {
    single {
        get<Context>().dataStore
    }
    single {
        PreferenceRepository(get())
    }
}
