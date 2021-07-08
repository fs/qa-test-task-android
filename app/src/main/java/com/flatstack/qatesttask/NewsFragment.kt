package com.flatstack.qatesttask

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import by.kirich1409.viewbindingdelegate.viewBinding
import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.databinding.FragmentNewsBinding
import com.flatstack.qatesttask.feature.adapter.NewsAdapter
import com.flatstack.qatesttask.feature.model.PostDto
import com.flatstack.qatesttask.feature.viewmodel.ListFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class NewsFragment : Fragment(R.layout.fragment_news) {
    private val binding: FragmentNewsBinding by viewBinding()
    private val viewModel: ListFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = binding.recyclerViewNewsFragmentNews
        val dividerItemDecoration = DividerItemDecoration(
            activity,
            VERTICAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

        val newsAdapter = NewsAdapter(
            onClickListener = {
                Timber.d("click")
            },
            onBottomReachedListener = {
                Timber.d("the bottom had been reached")
            }
        )
        recyclerView.adapter = newsAdapter
        viewModel.currentNewsList.observe(viewLifecycleOwner) { list->
            newsAdapter.submitList(list)
        }
        // TODO: get section and language from preferences
        viewModel.getSection(
            "sport",
            (viewModel.currentPageNumber.value ?: 1),
            Language.ENGLISH,
        )
        // TODO: get section and language from preferences
        binding.floatingActionButtonGetMoreNews.setOnClickListener {
            viewModel.getSection(
                "sport",
                (viewModel.currentPageNumber.value ?: 1) + 1,
                Language.ENGLISH,
            )
        }
    }
}
