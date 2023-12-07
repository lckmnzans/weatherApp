package com.application.weather.activity

import android.app.SearchManager
import android.content.Context
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModel
import com.application.weather.LocWeatherResponse
import com.application.weather.broadcast.NetworkChangeReceiver
import com.application.weather.databinding.ActivityHomeBinding
import com.application.weather.databinding.ActivityMainBinding
import com.application.weather.fragment.OverviewFragment
import com.application.weather.network.ApiConfig
import com.application.weather.viewmodel.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val networkChangeReceiver = NetworkChangeReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerNetworkChangeReceiver()
        setSearchField()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetworkChangeReceiver()
    }

    private fun getResponseWeather(q: String?) {
        val client = ApiConfig.getApiService().getLocWeather(q = q)
        client.enqueue(object: Callback<LocWeatherResponse> {
            override fun onResponse(
                call: Call<LocWeatherResponse>,
                response: Response<LocWeatherResponse>
            ) {
                if (response.isSuccessful) {
                    setFragmentView()
                    val responseBody = response.body()
                    if (responseBody != null) {
                        binding.tvResult.text = responseBody.toString()
                        viewModel.setWeatherResult(responseBody.name, responseBody.weather[0].main, responseBody.main.temp)
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

    private fun setSearchField() {
        val searchManager: SearchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.svLocCityName.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        binding.svLocCityName.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.svLocCityName.clearFocus()
                getResponseWeather(q = query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //
                return false
            }
        })
    }

    private fun setFragmentView() {
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentView.id, OverviewFragment())
            .commit()
    }

    private fun registerNetworkChangeReceiver() {
        val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(networkChangeReceiver, filter)
    }

    private fun unregisterNetworkChangeReceiver() {
        unregisterReceiver(networkChangeReceiver)
    }
}