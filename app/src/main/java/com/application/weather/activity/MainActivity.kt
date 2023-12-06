package com.application.weather.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.application.weather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSplash()
    }

    private fun setSplash() {
        val runnable = Runnable {
            val intent = Intent(this@MainActivity, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        Handler(Looper.getMainLooper()).postDelayed(
            runnable, DELAYED_TIME
        )
    }

    companion object {
        private const val DELAYED_TIME = 3000L
    }
}