package com.bivizul.newsapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bivizul.newsapp.R
import com.bivizul.newsapp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    // 1 variant
//    private val viewModel by viewModels<MainViewModel>()
    // 2 variant
    private val viewModel: MainViewModel by viewModels()
    // 3 variant
//    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
//        ViewModelProvider(this)[MainViewModel::class.java]
//    }

//    private var _binding: FragmentMainBinding? = null
//    private val binding: FragmentMainBinding
//        get() = _binding ?: throw RuntimeException("FragmentMainBinding is null")

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.all

//        viewModel.all.observe(viewLifecycleOwner){
//
//        }
    }
}