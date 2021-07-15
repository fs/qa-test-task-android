package com.flatstack.qatesttask.feature.news

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import by.kirich1409.viewbindingdelegate.viewBinding
import com.flatstack.qatesttask.R
import com.flatstack.qatesttask.databinding.FragmentNewsBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NewsFragment : Fragment(R.layout.fragment_news) {

    private val binding: FragmentNewsBinding by viewBinding()
    private val viewModel: NewsFragmentViewModel by viewModel()
    private val httpExceptionHandler: (IOException) -> Unit = { exception ->
        when (exception) {
            is UnknownHostException ->
                Snackbar.make(binding.root, "Bad Gateway", Snackbar.LENGTH_LONG).show()
            is SocketTimeoutException ->
                Snackbar.make(binding.root, "Timeout", Snackbar.LENGTH_LONG).show()
        }
    }
    @InternalCoroutinesApi
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
                NewsFragmentDirections
                    .actionNewsFragmentToBrowserFragment(it.url).let {
                        findNavController().navigate(it)
                    }
            },
            onBottomReachedListener = {
                Timber.d("the bottom had been reached")
            }
        )
        recyclerView.adapter = newsAdapter
        viewModel.currentNewsList.observe(viewLifecycleOwner) { list ->
            newsAdapter.submitList(list.toList())
        }
        viewModel.currentPageInfo.observe(viewLifecycleOwner) {
            if (it.currentPage == it.pages) {
                binding.floatingActionButtonGetMoreNews.hide()
            }
        }
        viewModel.requestIsLoading.observe(viewLifecycleOwner) {
            binding.floatingActionButtonGetMoreNews.isEnabled = (it == false)
        }
        Timber.e("request")
        viewModel.getInitialSection(
            "world",
            httpExceptionHandler
        )
        // TODO: get section from preferences
        binding.floatingActionButtonGetMoreNews.setOnClickListener {
            viewModel.getNextSection(
                "world",
                httpExceptionHandler
            )
        }
    }
}