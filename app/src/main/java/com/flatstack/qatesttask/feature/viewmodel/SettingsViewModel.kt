package com.flatstack.qatesttask.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.feature.PreferenceRepository
import com.flatstack.qatesttask.feature.PreferencesKeys
import kotlinx.coroutines.launch

class SettingsViewModel(private val preferenceRepository: PreferenceRepository) : ViewModel() {
    fun setDarkModeValue(darkMode: Boolean) {
        viewModelScope.launch {
            preferenceRepository.setProperty(PreferencesKeys.DARK_THEME_MODE, darkMode)
        }
    }
    fun setDarkModeChangeCallback(callback: suspend (Boolean) -> Unit) {
        viewModelScope.launch {
            preferenceRepository.setPropertyChangeListener(
                PreferencesKeys.DARK_THEME_MODE,
                this,
                callback
            )
        }
    }
    fun setLangValue(lang: Language) {
        viewModelScope.launch {
            preferenceRepository.setProperty(PreferencesKeys.LANG, lang.langName)
        }
    }
}
