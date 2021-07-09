package com.flatstack.qatesttask


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.flatstack.qatesttask.adapters.SectionsRecyclerAdapter
import com.flatstack.qatesttask.databinding.FragmentCategoryBinding
import com.flatstack.qatesttask.feature.viewmodel.CategoryFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private val sectionsAdaper = SectionsRecyclerAdapter()
    private val binding: FragmentCategoryBinding by viewBinding()
    private val categoriesVM: CategoryFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecycler()
        addDataset()
    }

    private fun initRecycler(){
        binding.sectionsRw.apply {
            adapter = sectionsAdaper
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun addDataset(){
        val data = categoriesVM.getInfo().value
        if (data!=null){
            sectionsAdaper.submitData(data)
        }


    }


}