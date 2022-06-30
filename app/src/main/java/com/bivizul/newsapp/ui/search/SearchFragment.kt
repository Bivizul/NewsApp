package com.bivizul.newsapp.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bivizul.newsapp.R
import com.bivizul.newsapp.databinding.FragmentSearchBinding
import com.bivizul.newsapp.ui.adapters.NewsAdapter
import com.bivizul.newsapp.utils.Constants
import com.bivizul.newsapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val viewModel by viewModels<SearchViewModel>()
    lateinit var newsAdapter: NewsAdapter
    var job: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        binding.etSearch.addTextChangedListener { text ->
            job?.cancel()
            job = MainScope().launch {
                delay(500)
                text?.let {
                    if (it.toString().isEmpty()) {
                        viewModel.getSearchNews(query = it.toString())
                    }
                }
            }
        }

        viewModel.searchNewsLiveData.observe(viewLifecycleOwner) { response ->
            with(binding) {
                when (response) {
                    is Resource.Success -> {
                        pbPageSearch.visibility = View.INVISIBLE
                        response.data?.let {
                            newsAdapter.differ.submitList(it.articles)
                        }
                    }
                    is Resource.Error -> {
                        pbPageSearch.visibility = View.INVISIBLE
                        response.data?.let {
                            Log.d(Constants.TAG, "MainFragment error: $it")
                        }
                    }
                    is Resource.Loading -> {
                        pbPageSearch.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter()
        binding.rvSearchNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}