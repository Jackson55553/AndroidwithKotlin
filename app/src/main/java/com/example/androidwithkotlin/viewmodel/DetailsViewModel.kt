package com.example.androidwithkotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidwithkotlin.app.App.Companion.getHistoryDao
import com.example.androidwithkotlin.data.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.androidwithkotlin.data.convertDtoToModel
import com.example.androidwithkotlin.dto.FactDTO
import com.example.androidwithkotlin.dto.WeatherDTO
import com.example.androidwithkotlin.repository.*
import com.example.androidwithkotlin.ui.model.AppState
import java.io.IOException

private const val SERVER_ERROR = "Ошибка сервера"
private const val REQUEST_ERROR = "Ошибка запроса на сервер"
private const val CORRUPTED_DATA = "Неполные данные"

class DetailsViewModel(
    val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepository: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource()),
    private val historyRepository: LocalRepository = LocalRepositoryImpl(getHistoryDao())
) : ViewModel() {

    fun getWeatherFromRemoteSource(lat: Double, lon: Double) {
        detailsLiveData.value = AppState.Loading
        detailsRepository.getWeatherDetailsFromServer(lat, lon, callBack)
    }

    fun saveCityToDB(weather: Weather) {
        historyRepository.saveEntity(weather)
    }

    private val callBack = object : Callback<WeatherDTO> {

        @Throws(IOException::class)
        override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
            val serverResponse: WeatherDTO? = response.body()
            detailsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable(SERVER_ERROR))
                }
            )
        }

        override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
            detailsLiveData.postValue(AppState.Error(Throwable(t.message ?: REQUEST_ERROR)))
        }
    }

    fun checkResponse(serverResponse: WeatherDTO): AppState {
        val fact: FactDTO? = serverResponse.fact
        return if (fact?.temp == null || fact.feels_like == null || fact.condition.isNullOrEmpty()) {
            AppState.Error(Throwable(CORRUPTED_DATA))
        } else {
            AppState.Success(convertDtoToModel(serverResponse))
        }
    }
}
