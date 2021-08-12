package com.flatstack.qatesttask.feature.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flatstack.qatesttask.data.guardiannews.model.Category
import com.flatstack.qatesttask.data.guardiannews.model.CategoryResponseBase
import com.flatstack.qatesttask.data.guardiannews.retrofit.GuardianService
import com.flatstack.qatesttask.repository.PreferenceRepository
import com.flatstack.qatesttask.repository.SELECTED_CATEGORIES_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryFragmentViewModel(
    private val retrofit: GuardianService,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private lateinit var section: CategoryResponseBase
    private val sections: MutableLiveData<List<Category>> = MutableLiveData()
    val categories: LiveData<List<Category>> = sections

    fun getInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            section = retrofit.getSections()
            val sectionNames = section.response.results.map { Category(it.webTitle, it.id, false) }
            sections.postValue(sectionNames)
        }
    }

    fun checkCategory(position: Int) {
        categories.value?.let { categories ->
            val item = categories[position]
            val newCategories = categories.toMutableList().also {
                it[position] = item.copy(isChecked = !item.isChecked)
            }
            sections.value = newCategories
        }
    }

    fun saveCategories() {
        val checkedCategories = categories.value?.filter {
            it.isChecked
        }?.map { it.id }
        val resString = checkedCategories?.joinToString("&")
        viewModelScope.launch {
            preferenceRepository.setProperty(SELECTED_CATEGORIES_KEY, resString)
        }
    }
}
