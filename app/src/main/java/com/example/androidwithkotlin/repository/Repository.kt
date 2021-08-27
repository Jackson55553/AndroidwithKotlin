package com.example.androidwithkotlin.repository

import com.example.androidwithkotlin.data.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}