package com.example.androidwithkotlin.repository

import com.example.androidwithkotlin.data.Weather
import com.example.androidwithkotlin.data.convertHistoryEntityToWeather
import com.example.androidwithkotlin.data.convertWeatherToEntity
import com.example.androidwithkotlin.room.HistoryDao


class LocalRepositoryImpl(private val localDataSource: HistoryDao): LocalRepository {
    override fun getAllHistory(): List<Weather> {
        return convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: Weather) {
        return localDataSource.insert(convertWeatherToEntity(weather))
    }
}