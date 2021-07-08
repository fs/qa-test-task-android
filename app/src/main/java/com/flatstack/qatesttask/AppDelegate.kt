package com.flatstack.qatesttask

import android.app.Application
import com.flatstack.qatesttask.di.guardianModule
import com.flatstack.qatesttask.feature.viewmodel.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant()
        startKoin {
            androidLogger()
            androidContext(this@AppDelegate)
            modules(guardianModule, viewModelModule)
        }
    }
}
