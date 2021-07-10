package com.flatstack.qatesttask.feature

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn

object PreferencesKeys {
    val DARK_THEME_MODE = booleanPreferencesKey("darkTheme")
    val LANG = stringPreferencesKey("lang")
}

class PreferenceRepository(private val dataStore: DataStore<Preferences>) {
    suspend fun <P> setProperty(preference: Preferences.Key<P>, value: P) {
        dataStore
            .edit {
                it[preference] = value
            }
    }
    suspend fun <P> setPropertyChangeListener(
        preference: Preferences.Key<P>,
        action: suspend (value: P) -> Unit
    ) {
        dataStore.data
            .flowOn(Dispatchers.IO)
            .collect {
                it[preference]?.let { v -> action(v) }
            }
    }
    suspend fun <P> getFirstPropertyValue(preference: Preferences.Key<P>) =
        dataStore.data
            .flowOn(Dispatchers.IO)
            .first {
                it.contains(preference)
            }[preference]
}
