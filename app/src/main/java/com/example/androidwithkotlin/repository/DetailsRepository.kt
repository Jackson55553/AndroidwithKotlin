package com.example.androidwithkotlin.repository

import com.example.androidwithkotlin.dto.WeatherDTO

interface DetailsRepository {
        fun getWeatherDetailsFromServer(
            lat: Double,
            lon: Double,
            callback: retrofit2.Callback<WeatherDTO>
        )
}