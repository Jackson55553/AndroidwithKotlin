package com.example.androidwithkotlin.data

data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0
)

fun getDefaultCity() = City("Москва", 72.3123, 41.4234)
