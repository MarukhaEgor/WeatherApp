package com.example.weatherapp.repository

import android.content.Context
import com.example.weatherapp.data.CitiesEntity
import com.example.weatherapp.data.db.CitiesDataBase
import com.example.weatherapp.model.CurrentDayWeatherData
import com.example.weatherapp.model.WeekForecastWeatherData
import com.example.weatherapp.network.Network
import kotlinx.coroutines.flow.Flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class Repository(context: Context) : KoinComponent {

    private val db: CitiesDataBase by inject()

    private val sp = context.getSharedPreferences("OpenApp", Context.MODE_PRIVATE)

    val allCities: Flow<List<CitiesEntity>> = db.citiesDao().getAllCities()

    private val apiService = Network.getApiServices()

    suspend fun getWeatherDetails(lat: Float, lon: Float): CurrentDayWeatherData =
        apiService.getWeatherDetail(lat, lon, "metric", Network.apikey)

    suspend fun getAllWeatherDetail(lat: Float, lon: Float): WeekForecastWeatherData =
        apiService.getAllDayWeatherDetail(
            lat,
            lon,
            "hourly,current,minutely,alerts",
            "metric",
            Network.apikey
        )

    suspend fun saveLocation(id: Int, lat: Float, lon: Float) {
        val tomorrow = getAllWeatherDetail(lat, lon)
        val current = getWeatherDetails(lat, lon)
        val tempCurrent = current.main.temp
        val humidityCurrent = current.main.humidity
        val tempTomorrow = tomorrow.daily[0].temp.day
        val humidityTomorrow = tomorrow.daily[0].humidity
        val imgCurrent = current.weather[0].icon
        val imgTomorrow = tomorrow.daily[0].weather[0].icon
        val obj = CitiesEntity(
            id = id,
            lat = lat,
            lon = lon,
            tempCurrent = tempCurrent,
            humidityCurrent = humidityCurrent,
            tempTomorrow = tempTomorrow,
            humidityTomorrow = humidityTomorrow,
            imgCurrent = imgCurrent,
            imgTomorrow = imgTomorrow
        )
        db.citiesDao().insertCity(obj)
    }

    fun onBoardingFinished(): Boolean = sp.getBoolean("OpenApp", false)
}