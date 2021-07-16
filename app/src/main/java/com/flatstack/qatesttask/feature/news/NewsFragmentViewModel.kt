package com.flatstack.qatesttask.feature.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatstack.qatesttask.data.guardiannews.model.GuardianInfo
import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.feature.news.model.PostDto
import com.flatstack.qatesttask.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class NewsFragmentViewModel(
    private val newsRepository: NewsRepository,
    // private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private var currentLanguage: Language = Language.default
    private var currentPageNumber = 1

    private val _currentNewsList: MutableLiveData<Set<PostDto>> = MutableLiveData()
    private val _currentPageInfo: MutableLiveData<GuardianInfo> = MutableLiveData()
    private val _requestIsLoading: MutableLiveData<Boolean> = MutableLiveData()

    val requestIsLoading: LiveData<Boolean> = _requestIsLoading
    val currentPageInfo: LiveData<GuardianInfo> = _currentPageInfo
    val currentNewsList: LiveData<Set<PostDto>> = _currentNewsList

    fun getInitialSection(section: String, exceptionHandler: (IOException) -> Unit) {
        _currentNewsList.value = setOf()
        viewModelScope.launch(Dispatchers.IO) {
            getSection(section, 1, exceptionHandler)
        }
    }
    fun getNextSection(section: String, exceptionHandler: (IOException) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            getSection(section, currentPageNumber + 1, exceptionHandler)
        }
    }
    private suspend fun getSection(section: String, page: Int, exceptionHandler: (IOException) -> Unit) {
        try {
            _requestIsLoading.postValue(true)
            if (_currentPageInfo.value?.pages ?: 1 >= page) {
                val info = newsRepository.getNewsListPageInfo(
                    page,
                    section,
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
