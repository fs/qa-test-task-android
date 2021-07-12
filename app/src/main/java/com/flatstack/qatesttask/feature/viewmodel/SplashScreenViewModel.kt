package com.flatstack.qatesttask.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatstack.qatesttask.feature.PreferenceRepository
import com.flatstack.qatesttask.feature.PreferencesKeys
import kotlinx.coroutines.launch
import timber.log.Timber

class SplashScreenViewModel(private val preferenceRepository: PreferenceRepository) : ViewModel() {
    private val _darkThemeIsActive: MutableLiveData<Boolean> = MutableLiveData()

    val darkThemeIsActive: LiveData<Boolean> = _darkThemeIsActive

    fun getDarkThemeStatus() {
        viewModelScope.launch {
            Timber.e("START")
            _darkThemeIsActive.value =
                preferenceRepository.getCurrentPropertyValue(PreferencesKeys.DARK_THEME_MODE)
        }
    }
    /* fun launch(action: suspend (Boolean)-> Unit){
        viewModelScope.launch {
            Timber.e("START")
            preferenceRepository.setPropertyChangeListener(PreferencesKeys.DARK_THEME_MODE,
                this,
                action)
        }
    }*/
}
