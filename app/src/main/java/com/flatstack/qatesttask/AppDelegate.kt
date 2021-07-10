package com.flatstack.qatesttask

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.preferences.preferencesDataStore
import com.flatstack.qatesttask.di.guardianModule
import com.flatstack.qatesttask.di.preferencesModule
import com.flatstack.qatesttask.di.viewModelModule
import com.flatstack.qatesttask.feature.PreferenceRepository
import com.flatstack.qatesttask.feature.PreferencesKeys
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

private const val USER_PREFERENCES_NAME = "user_preferences"

val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME,
)
class AppDelegate : Application(), CoroutineScope by MainScope() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant()
        val koin = startKoin {
            androidLogger()
            androidContext(this@AppDelegate)
            modules(guardianModule, viewModelModule, preferencesModule)
        }
        launch {
            koin.koin.get<PreferenceRepository>()
                .setPropertyChangeListener(PreferencesKeys.DARK_THEME_MODE) { darkMode ->
                    if (darkMode) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    } else
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
        }
    }
}
