package com.example.androidwithkotlin.repository

import com.example.androidwithkotlin.data.Weather

class RepositoryImpl : Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getWeatherFromLocalStorage(): Weather {
        return Weather()
    }
}