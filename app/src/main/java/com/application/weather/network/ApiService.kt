package com.application.weather.network

import com.application.weather.LocWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    fun getLocWeather(
        @Query("q") q: String? = null,
        @Query("lat") lat: Double? = null,
        @Query("lon") lon: Double? = null,
        @Query("appid") appid: String = "ce7dc25e783c8df0b76a082dcc47afc9"
    ): Call<LocWeatherResponse>
}