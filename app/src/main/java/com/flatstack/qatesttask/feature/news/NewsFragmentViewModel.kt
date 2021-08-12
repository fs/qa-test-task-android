package com.flatstack.qatesttask.feature.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatstack.qatesttask.data.guardiannews.model.GuardianInfo
import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.feature.news.model.PostDto
import com.flatstack.qatesttask.repository.NewsRepository
import com.flatstack.qatesttask.repository.PreferenceRepository
import com.flatstack.qatesttask.repository.SELECTED_CATEGORIES_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

const val DEFAULT_SECTION = "world"

class NewsFragmentViewModel(
    private val newsRepository: NewsRepository,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private var currentLanguage: Language = Language.default
    private var currentPageNumber = 1
    private var currentSection = DEFAULT_SECTION

    private val _currentNewsList: MutableLiveData<Set<PostDto>> = MutableLiveData()
    private val _currentPageInfo: MutableLiveData<GuardianInfo> = MutableLiveData()
    private val _requestIsLoading: MutableLiveData<Boolean> = MutableLiveData()

    val requestIsLoading: LiveData<Boolean> = _requestIsLoading
    val currentPageInfo: LiveData<GuardianInfo> = _currentPageInfo
    val currentNewsList: LiveData<Set<PostDto>> = _currentNewsList

    fun getInitialSection(exceptionHandler: (IOException) -> Unit) {
        _currentNewsList.value = setOf()
        viewModelScope.launch(Dispatchers.IO) {
            _requestIsLoading.postValue(true)
            currentSection = preferenceRepository.getCurrentPropertyValue<String>(
                SELECTED_CATEGORIES_KEY
            ) ?: DEFAULT_SECTION
            getSection(1, exceptionHandler)
        }
    }

    fun getNextSection(exceptionHandler: (IOException) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            _requestIsLoading.postValue(true)
            getSection(currentPageNumber + 1, exceptionHandler)
        }
    }

    private suspend fun getSection(page: Int, exceptionHandler: (IOException) -> Unit) {
        try {
            if (_currentPageInfo.value?.pages ?: 1 >= page) {
                val info = newsRepository.getNewsListPageInfo(
                    page,
                    currentSection,
                    currentLanguage
                )
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
                currentPageNumber = page
                _currentPageInfo.postValue(info)
            }
        } catch (e: IOException) {
            Timber.e("io exception")
            exceptionHandler.invoke(e)
        } finally {
            _requestIsLoading.postValue(false)
        }
    }

    fun setNewsLanguage(language: Language) {
        // proper version
        /*
        preferenceRepository.getCurrentPropertyValue<String>(
            LANG_KEY
        )?.let {
            currentLanguage = Language.resolveLanguage(it)
        }
        */
        currentLanguage = language
    }
}
