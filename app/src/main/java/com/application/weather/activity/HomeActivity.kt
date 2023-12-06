package com.application.weather.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.weather.databinding.ActivityHomeBinding
import com.application.weather.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}