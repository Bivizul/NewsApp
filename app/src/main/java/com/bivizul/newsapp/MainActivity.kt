package com.bivizul.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bivizul.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.fragment_splash)

        // 1-st variant
//        Handler(Looper.myLooper()!!).postDelayed({
//            setContentView(binding.root)
//            bottomNavMenu.setupWithNavController(
//                nav_host_fragment.findNavController()
//            )
//        }, 5000)

        // 2-nd variant
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            setContentView(binding.root)
            with(binding){
                bottomNavMenu.setupWithNavController(
                    navHostFragment.findNavController()
                )
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}