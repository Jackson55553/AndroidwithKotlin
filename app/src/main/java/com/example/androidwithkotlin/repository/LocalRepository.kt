package com.example.androidwithkotlin.repository

import com.example.androidwithkotlin.data.Weather

interface LocalRepository {
        fun getAllHistory(): List<Weather>
        fun saveEntity(weather: Weather)

}