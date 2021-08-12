package com.flatstack.qatesttask

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.flatstack.qatesttask.di.guardianModule
import com.flatstack.qatesttask.di.languageModule
import com.flatstack.qatesttask.di.preferencesModule
import com.flatstack.qatesttask.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

private const val USER_PREFERENCES_NAME = "user_preferences"

val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME,
)

class AppDelegate : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        startKoin {
            androidLogger()
            androidContext(this@AppDelegate)
            modules(guardianModule, viewModelModule, preferencesModule, languageModule)
        }
    }
}
