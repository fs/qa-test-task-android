package com.flatstack.qatesttask.feature.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.flatstack.qatesttask.R
import com.flatstack.qatesttask.adapters.SectionsRecyclerAdapter
import com.flatstack.qatesttask.databinding.FragmentCategoryBinding
import com.flatstack.qatesttask.feature.category.viewmodel.CategoryFragmentViewModel
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class CategoryFragment :
    Fragment(R.layout.fragment_category),
    SectionsRecyclerAdapter.OnItemClickListener {

    private val sectionsAdapter = SectionsRecyclerAdapter(this)
    private val binding: FragmentCategoryBinding by viewBinding()
    private val viewModel: CategoryFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        confirmCategories()
        viewModel.getInfo()
        initRecycler()
        addDataset()
    }

    private fun initRecycler() {
        binding.rvSections.apply {
            adapter = sectionsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun addDataset() {
        viewModel.categories.observe(viewLifecycleOwner) {
            sectionsAdapter.submitList(it)
        }
    }

    override fun onItemClick(position: Int) {
        viewModel.checkCategory(position)
    }

    private fun confirmCategories() {
        val confirmButton = activity?.findViewById<MaterialButton>(R.id.btn_confirm)
        confirmButton?.setOnClickListener {
            val criticalFail = Random.nextInt(0, 100)
            if (criticalFail % 5 == 0) {
                activity?.finish()
            } else {
                viewModel.saveCategories()
            }
        }
    }
}
