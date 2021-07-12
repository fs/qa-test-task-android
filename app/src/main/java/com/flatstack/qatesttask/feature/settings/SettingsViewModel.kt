package com.flatstack.qatesttask.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.repository.DARK_THEME_MODE_KEY
import com.flatstack.qatesttask.repository.LANG_KEY
import com.flatstack.qatesttask.repository.PreferenceRepository
import kotlinx.coroutines.launch
class SettingsViewModel(private val preferenceRepository: PreferenceRepository) : ViewModel() {
    fun setDarkModeValue(darkMode: Boolean) {
        viewModelScope.launch {
            preferenceRepository.setProperty(DARK_THEME_MODE_KEY, darkMode)
        }
    }
    fun setDarkModeChangeCallback(callback: suspend (Boolean) -> Unit) {
        viewModelScope.launch {
            preferenceRepository.setPropertyChangeListener(
                DARK_THEME_MODE_KEY,
                this,
                callback
            )
        }
    }
    fun setLangValue(lang: Language) {
        viewModelScope.launch {
            preferenceRepository.setProperty(LANG_KEY, lang.langName)
        }
    }
}
