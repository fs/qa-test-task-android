package com.flatstack.qatesttask.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatstack.qatesttask.data.guardiannews.model.GuardianPost
import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.data.guardiannews.retrofit.GuardianRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListFragmentViewModel(private val retrofit: GuardianRetrofit) : ViewModel() {
    private val mCurrentNewsList: MutableLiveData<List<GuardianPost>> = MutableLiveData()
    private val mCurrentPageNumber: MutableLiveData<Int> = MutableLiveData()

    val currentNewsList: LiveData<List<GuardianPost>> = mCurrentNewsList
    val currentPageNumber: LiveData<Int> = mCurrentPageNumber

    fun getSection(section: String, page: Int, language: Language) {
        viewModelScope.launch(Dispatchers.IO) {
            mCurrentNewsList.postValue(
                (mCurrentNewsList.value ?: listOf()).plus(
                    retrofit.getSectionNewsList(
                        page,
                        section,
                        language.langName
                    ).info.results
                )
            )
            mCurrentPageNumber.postValue(page)
        }
    }
}
