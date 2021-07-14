package com.flatstack.qatesttask.feature.viewmodel

import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatstack.qatesttask.adapters.Category
import com.flatstack.qatesttask.data.guardiannews.model.CoolResponseBase
import com.flatstack.qatesttask.data.guardiannews.retrofit.GuardianRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CategoryFragmentViewModel(private val retrofit: GuardianRetrofit):ViewModel() {

    private lateinit var section: CoolResponseBase
    private val sections: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>> = sections
    private val checkedCategories: ArrayList<String> = ArrayList()

     fun getInfo(){
        var sectionNames:List<Category> = ArrayList()

        viewModelScope.launch(Dispatchers.IO) {
           section = retrofit.getSections()
           sectionNames = section.response.results.map { Category(it.webTitle, false) }
           sections.postValue(sectionNames)
        }

    }

    fun checkCategory(position : Int){
        categories.value?.let { categories ->
            val item = categories[position]
            val newCategories =categories.toMutableList().also {
                it[position] = item
            }
            sections.value = newCategories
        }
    }




}