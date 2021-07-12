package com.flatstack.qatesttask.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatstack.qatesttask.data.guardiannews.model.GuardianInfo
import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.data.guardiannews.retrofit.GuardianRetrofit
import com.flatstack.qatesttask.feature.PreferenceRepository
import com.flatstack.qatesttask.feature.PreferencesKeys
import com.flatstack.qatesttask.feature.model.PostDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class NewsFragmentViewModel(
    private val retrofit: GuardianRetrofit,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {
    private val _defaultLanguage = Language.ENGLISH
    private var _currentLanguage: Language = _defaultLanguage

    private val _currentNewsList: MutableLiveData<Set<PostDto>> = MutableLiveData()
    private val _currentPageInfo: MutableLiveData<GuardianInfo> = MutableLiveData()
    private val _currentPageNumber: MutableLiveData<Int> = MutableLiveData()
    private val _requestIsOngoing: MutableLiveData<Boolean> = MutableLiveData()

    val requestIsOngoing: LiveData<Boolean> = _requestIsOngoing
    val currentPageInfo: LiveData<GuardianInfo> = _currentPageInfo
    val currentNewsList: LiveData<Set<PostDto>> = _currentNewsList

    init {
        viewModelScope.launch(Dispatchers.Main) {
            preferenceRepository.getCurrentPropertyValue(
                PreferencesKeys.LANG
            )?.let {
                _currentLanguage = Language.resolveLanguage(it) ?: _defaultLanguage
            }
        }
    }
    fun getInitialSection(section: String, exceptionHandler: (IOException) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            preferenceRepository.getCurrentPropertyValue(
                PreferencesKeys.LANG
            )?.let {
                _currentLanguage = Language.resolveLanguage(it) ?: _defaultLanguage
            }
            getSection(section, _currentPageNumber.value ?: 1, exceptionHandler)
        }
    }
    fun getNextSection(section: String, exceptionHandler: (IOException) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            getSection(section, ((_currentPageNumber.value ?: 0) + 1), exceptionHandler)
        }
    }
    private suspend fun getSection(section: String, page: Int, exceptionHandler: (IOException) -> Unit) {
        try {
            _requestIsOngoing.postValue(true)
            if (_currentPageInfo.value?.pages ?: 1 >= page) {
                val info = retrofit.getSectionNewsList(
                    page,
                    section,
                    _currentLanguage.langName
                ).info
                _currentNewsList.postValue(
                    (_currentNewsList.value ?: setOf()).plus(
                        info.results.map {
                            PostDto(
                                it.id,
                                it.title,
                                it.webUrl,
                                it.additionalFields?.thumbnailUrl ?: "missing"
                            )
                        }
                    )
                )
                _currentPageNumber.postValue(page)
                _currentPageInfo.postValue(info)
            }
        } catch (e: IOException) {
            Timber.e("io exception")
            exceptionHandler.invoke(e)
        } finally {
            _requestIsOngoing.postValue(false)
        }
    }
}
