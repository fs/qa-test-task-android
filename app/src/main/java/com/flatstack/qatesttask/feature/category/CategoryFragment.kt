package com.flatstack.qatesttask.feature.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.flatstack.qatesttask.R
import com.flatstack.qatesttask.databinding.FragmentCategoryBinding
import com.google.android.material.button.MaterialButton
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private val binding: FragmentCategoryBinding by viewBinding()
    private val viewModel: CategoryFragmentViewModel by viewModel()

    private var sectionsAdapter: SectionsRecyclerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        confirmCategories()
        viewModel.getInfo()
        initRecycler()
        addDataset()
    }

    private fun initRecycler() {
        sectionsAdapter = SectionsRecyclerAdapter(viewModel::checkCategory)
        binding.rvSections.adapter = sectionsAdapter
    }

    private fun addDataset() {
        viewModel.categories.observe(viewLifecycleOwner) {
            sectionsAdapter?.submitList(it)
        }
    }

    @Suppress("MagicNumber", "TooGenericExceptionThrown")
    private fun confirmCategories() {
        val confirmButton = activity?.findViewById<MaterialButton>(R.id.btn_confirm)
        confirmButton?.setOnClickListener {
            val criticalFail = Random.nextInt(0, 100)
            if (criticalFail % 5 == 0) {
                throw RuntimeException()
            } else {
                viewModel.saveCategories()
            }
        }
    }
}
