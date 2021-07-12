package com.flatstack.qatesttask.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

const val DARK_THEME_MODE_KEY = "darkTheme"
const val LANG_KEY = "lang"

class PreferenceRepository(private val dataStore: DataStore<Preferences>) {
    suspend fun <T> setProperty(preference: String, value: T) {
        dataStore
            .edit {
                figureOutKey<T>(preference)?.let { key ->
                    it[key] = value
                }
            }
    }
    suspend fun <T> setPropertyChangeListener(
        preference: String,
        scope: CoroutineScope,
        action: suspend (value: T) -> Unit
    ) {

        dataStore.data
            .flowOn(Dispatchers.IO)
            .onEach {
                figureOutKey<T>(preference)?.let { key ->
                    it[key]?.let { v -> action(v) }
                }
            }
            .launchIn(scope)
    }
    suspend fun <T> getCurrentPropertyValue(preference: String) =
        figureOutKey<T>(preference)?.let { key ->
            dataStore.data
                .flowOn(Dispatchers.IO)
                .first()[key]
        }

    private fun <T> figureOutKey(key: String): Preferences.Key<T>? =
        when (key) {
            DARK_THEME_MODE_KEY -> booleanPreferencesKey(DARK_THEME_MODE_KEY) as? Preferences.Key<T>
            LANG_KEY -> stringPreferencesKey(LANG_KEY) as? Preferences.Key<T>
            else -> null
        }
}
