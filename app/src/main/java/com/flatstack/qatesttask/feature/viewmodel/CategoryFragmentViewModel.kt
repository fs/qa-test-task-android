package com.flatstack.qatesttask.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatstack.qatesttask.data.guardiannews.model.CoolResponse
import com.flatstack.qatesttask.data.guardiannews.model.CoolResponseBase
import com.flatstack.qatesttask.data.guardiannews.retrofit.GuardianRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class CategoryFragmentViewModel(private val retrofit: GuardianRetrofit):ViewModel() {

    private lateinit var section: CoolResponseBase
    private val sections: MutableLiveData<List<String>> = MutableLiveData()

     fun getInfo():LiveData<List<String>>{
        val sectionNames:ArrayList<String> = ArrayList()

        viewModelScope.launch(Dispatchers.IO) {
           section = retrofit.getSections()
           for (i in section.response.results){
               sectionNames.add(i.webTitle)
           }
           sections.postValue(sectionNames)
        }
        return sections
    }


}