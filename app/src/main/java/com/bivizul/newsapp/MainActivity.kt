package com.bivizul.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bivizul.newsapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.fragment_splash)

        Handler(Looper.myLooper()!!).postDelayed({
            setContentView(binding.root)
            bottomNavMenu.setupWithNavController(
                nav_host_fragment.findNavController()
            )
        }, 5000)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}