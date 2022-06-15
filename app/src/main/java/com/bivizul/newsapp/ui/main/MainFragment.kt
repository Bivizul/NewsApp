package com.bivizul.newsapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bivizul.newsapp.R
import com.bivizul.newsapp.databinding.FragmentMainBinding
import com.bivizul.newsapp.ui.adapters.NewsAdapter
import com.bivizul.newsapp.utils.Constants.TAG
import com.bivizul.newsapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    // 1 variant
//    private val viewModel by viewModels<MainViewModel>()
    // 2 variant
    private val viewModel: MainViewModel by viewModels()
    // 3 variant
//    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
//        ViewModelProvider(this)[MainViewModel::class.java]
//    }

    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.newsLiveData.observe(viewLifecycleOwner) { response ->
            with(binding) {
                when (response) {
                    is Resource.Success -> {
                        progressBar.visibility = View.INVISIBLE
                        response.data?.let {
                            newsAdapter.differ.submitList(it.articles)
                        }
                    }
                    is Resource.Error -> {
                        progressBar.visibility = View.INVISIBLE
                        response.data?.let {
                            Log.d(TAG, "MainFragment error: $it")
                        }
                    }
                    is Resource.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }

        }
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter()
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}