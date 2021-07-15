package com.flatstack.qatesttask.feature.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatstack.qatesttask.repository.DARK_THEME_MODE_KEY
import com.flatstack.qatesttask.repository.PreferenceRepository
import kotlinx.coroutines.launch
import timber.log.Timber
class SplashScreenViewModel(private val preferenceRepository: PreferenceRepository) : ViewModel() {

    private val _darkThemeIsActive: MutableLiveData<Boolean> = MutableLiveData()

    val darkThemeIsActive: LiveData<Boolean> = _darkThemeIsActive

    fun getDarkThemeStatus() {
        viewModelScope.launch {
            Timber.e("START")
            _darkThemeIsActive.value =
                preferenceRepository.getCurrentPropertyValue(DARK_THEME_MODE_KEY)
        }
    }
}
