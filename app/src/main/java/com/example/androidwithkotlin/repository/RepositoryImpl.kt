package com.example.androidwithkotlin.repository

import com.example.androidwithkotlin.data.Weather
import com.example.androidwithkotlin.data.getRussianCities
import com.example.androidwithkotlin.data.getWorldCities

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorageRus(): List<Weather> {
        return getRussianCities()
    }

    override fun getWeatherFromLocalStorageWorld(): List<Weather> {
        return getWorldCities()
    }


}