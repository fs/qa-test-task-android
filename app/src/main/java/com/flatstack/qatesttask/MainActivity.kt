package com.flatstack.qatesttask

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.flatstack.qatesttask.data.guardiannews.model.Language
import com.flatstack.qatesttask.databinding.FragmentNewsListBinding
import com.flatstack.qatesttask.feature.adapter.NewsAdapter
import com.flatstack.qatesttask.feature.model.PostDto
import com.flatstack.qatesttask.feature.viewmodel.ListFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.fragment_news_list) {

    private val binding: FragmentNewsListBinding by viewBinding()
    private val viewModel: ListFragmentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView = binding.newsList
        val dividerItemDecoration = DividerItemDecoration(
            this,
            VERTICAL
        )
        recyclerView.addItemDecoration(dividerItemDecoration)

        val newsAdapter = NewsAdapter(
            onClickListener = {
                Toast.makeText(this, "click", Toast.LENGTH_LONG).show()
            },
            onBottomReachedListener = {
                Toast.makeText(this, "the bottom had bean reached", Toast.LENGTH_LONG).show()

            })
        viewModel.getSection(
            "sport",
            1,
            Language.ENGLISH,
        )
        recyclerView.adapter = newsAdapter

        viewModel.currentNewsList.observe(this) { guardianNews ->
            val list = guardianNews.map {
                PostDto(
                    it.id,
                    it.title,
                    it.webUrl,
                    it.additionalFields.thumbnailUrl
                )
            }
            newsAdapter.submitList(list)
        }
    }
}
