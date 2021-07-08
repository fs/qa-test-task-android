package com.flatstack.qatesttask.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.data.guardiannews.retrofit.GuardianRetrofit
import com.flatstack.qatesttask.feature.model.PostDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListFragmentViewModel(private val retrofit: GuardianRetrofit) : ViewModel() {
    private val _currentNewsList: MutableLiveData<List<PostDto>> = MutableLiveData()
    private val _currentPageNumber: MutableLiveData<Int> = MutableLiveData()

    val currentNewsList: LiveData<List<PostDto>> = _currentNewsList
    val currentPageNumber: LiveData<Int> = _currentPageNumber

    fun getSection(section: String, page: Int, language: Language) {
        viewModelScope.launch(Dispatchers.IO) {
            _currentNewsList.postValue(
                (_currentNewsList.value ?: listOf<PostDto>()).plus(
                    retrofit.getSectionNewsList(
                        page,
                        section,
                        language.langName
                    ).info.results.map {
                        PostDto(
                            it.id,
                            it.title,
                            it.webUrl,
                            it.additionalFields.thumbnailUrl
                        )
                    }
                )
            )
            _currentPageNumber.postValue(page)
        }
    }
}
