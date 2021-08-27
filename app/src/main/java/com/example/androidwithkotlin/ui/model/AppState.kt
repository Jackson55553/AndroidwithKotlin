package com.example.androidwithkotlin.ui.model

import com.example.androidwithkotlin.data.Weather

sealed class AppState {
    data class Success(val weatherData: Weather) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
