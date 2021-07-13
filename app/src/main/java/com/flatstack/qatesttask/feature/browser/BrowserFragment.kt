package com.flatstack.qatesttask.feature.browser

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.flatstack.qatesttask.R
import com.flatstack.qatesttask.databinding.FragmentNewsBrowserBinding
class BrowserFragment : Fragment(R.layout.fragment_news_browser) {
    private val binding: FragmentNewsBrowserBinding by viewBinding()
    private val args: BrowserFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webViewMain.loadUrl(args.url)
    }
}
