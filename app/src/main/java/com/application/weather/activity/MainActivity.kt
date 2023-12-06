package com.application.weather.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.application.weather.databinding.ActivityMainBinding
import com.application.weather.manager.SessionManager

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val loginStatus = getSharedPreferences(SessionManager.LOGIN_PREF, Context.MODE_PRIVATE)
            .getBoolean(SessionManager.LOGIN_STATUS, false)
        setSplash(loginStatus)
    }

    private fun setSplash(loginStatus: Boolean) {
        val runnable = Runnable {
            if (loginStatus) {
                startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this@MainActivity, WelcomeActivity::class.java))
                finish()
            }
        }
        Handler(Looper.getMainLooper()).postDelayed(
            runnable, DELAYED_TIME
        )
    }

    companion object {
        private const val DELAYED_TIME = 3000L
    }
}