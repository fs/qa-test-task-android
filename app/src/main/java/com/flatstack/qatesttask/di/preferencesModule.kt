package com.flatstack.qatesttask.di

import android.content.Context
import androidx.datastore.dataStore
import com.flatstack.qatesttask.dataStore
import com.flatstack.qatesttask.feature.PreferenceRepository
import org.koin.dsl.module

val preferencesModule = module {
    single {
        get<Context>().dataStore
    }
    single {
        PreferenceRepository(get())
    }
}
