package com.example.androidwithkotlin.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Weather(
    val city: City = getDefaultCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0
) : Parcelable

fun getDefaultCity() = City("Москва", 72.3123, 41.4234)
