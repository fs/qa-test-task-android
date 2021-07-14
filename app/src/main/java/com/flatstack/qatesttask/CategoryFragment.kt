package com.flatstack.qatesttask


import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.flatstack.qatesttask.adapters.SectionsRecyclerAdapter
import com.flatstack.qatesttask.databinding.FragmentCategoryBinding
import com.flatstack.qatesttask.feature.viewmodel.CategoryFragmentViewModel
import com.google.android.material.card.MaterialCardView
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryFragment : Fragment(R.layout.fragment_category),
    SectionsRecyclerAdapter.OnItemClickListener {

    private val sectionsAdapter = SectionsRecyclerAdapter(this)
    private val binding: FragmentCategoryBinding by viewBinding()
    private val categoriesVM: CategoryFragmentViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        categoriesVM.getInfo()
        initRecycler()
        addDataset()
    }

    private fun initRecycler(){
        binding.sectionsRw.apply {
            adapter = sectionsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun addDataset(){
        categoriesVM.categories.observe(viewLifecycleOwner){
            val data = it
            sectionsAdapter.submitList(data)
        }
    }

    override fun onItemClick(position: Int) {
        categoriesVM.checkCategory(position)
    }


}