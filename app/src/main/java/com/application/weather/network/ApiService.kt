package com.application.weather.network

import com.application.weather.LocWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    fun getLocWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appid: String = "ce7dc25e783c8df0b76a082dcc47afc9"
    ): Call<LocWeatherResponse>
}