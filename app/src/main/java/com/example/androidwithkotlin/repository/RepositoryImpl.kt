package com.example.androidwithkotlin.repository

import com.example.androidwithkotlin.data.Weather
import com.example.androidwithkotlin.data.getRussianCities
import com.example.androidwithkotlin.data.getWorldCities

class RepositoryImpl : Repository {
    override fun getWeatherFromServer() = Weather()
    override fun getWeatherFromLocalStorageRus() = getRussianCities()
    override fun getWeatherFromLocalStorageWorld() = getWorldCities()

}