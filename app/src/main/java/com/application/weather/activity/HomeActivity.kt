package com.application.weather.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.application.weather.LocWeatherResponse
import com.application.weather.databinding.ActivityHomeBinding
import com.application.weather.databinding.ActivityMainBinding
import com.application.weather.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getResponseWeather()
    }

    private fun getResponseWeather() {
        val client = ApiConfig.getApiService().getLocWeather(-7.7827885, 110.3650402)
        client.enqueue(object: Callback<LocWeatherResponse> {
            override fun onResponse(
                call: Call<LocWeatherResponse>,
                response: Response<LocWeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val result = responseBody.toString()
                        binding.tvResult.text = result
                    }
                } else {
                    Log.e("MainActivity", "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LocWeatherResponse>, t: Throwable) {
                Log.e("HomeActivity", "onFailure: ${t.message}")
            }
        })
    }
}