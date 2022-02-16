package com.example.weatherapp.network

import com.example.weatherapp.model.CurrentDayWeatherData
import com.example.weatherapp.model.WeekForecastWeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/data/2.5/weather")
    suspend fun getWeatherDetail(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("units") units: String,
        @Query("appid") apiKey: String,
    ): CurrentDayWeatherData

    @GET("/data/2.5/onecall")
    suspend fun getAllDayWeatherDetail(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("exclude") exclude: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String,
    ): WeekForecastWeatherData
}