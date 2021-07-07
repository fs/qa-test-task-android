package com.flatstack.qatesttask.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.data.guardiannews.model.GuardianResponse
import com.flatstack.qatesttask.data.guardiannews.retrofit.GuardianRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val retrofit: GuardianRetrofit) : ViewModel() {
    private val mCurrentSection: MutableLiveData<GuardianResponse> = MutableLiveData()

    val currentSection: LiveData<GuardianResponse> = mCurrentSection

    fun getSection(section: String, page: Int, language: Language) {
        viewModelScope.launch(Dispatchers.IO) {
            mCurrentSection.postValue(
                retrofit.getSectionNewsList(
                    page,
                    section,
                    language.langName
                )
            )
        }
    }
}
