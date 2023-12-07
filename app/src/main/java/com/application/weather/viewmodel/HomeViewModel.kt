package com.application.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    private var _cityName = MutableLiveData<String>()
    val cityName : LiveData<String> = _cityName

    private var _weatherCond = MutableLiveData<String>()
    val weatherCond : LiveData<String> = _weatherCond

    private var _weatherTemp = MutableLiveData<Int>()
    val weatherTemp : LiveData<Int> = _weatherTemp

    fun setWeatherResult(cityName: String, weatherCond: String, weatherTemp: Double) {
        _weatherCond.value = weatherCond
        _cityName.value = cityName
        _weatherTemp.value = convertKelvinToCelcius(weatherTemp)
    }

    private fun convertKelvinToCelcius(kelvin: Double): Int {
        return (kelvin - 273.15).toInt()
    }
}