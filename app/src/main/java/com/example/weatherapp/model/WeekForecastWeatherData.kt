package com.example.weatherapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WeekForecastWeatherData(
    val daily: List<Daily>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int,
) : Parcelable {
    @Parcelize
    data class Daily(
        val clouds: Int,
        val dew_point: Double,
        val dt: Int,
        val feels_like: FeelsLike,
        val humidity: Int,
        val moon_phase: Double,
        val moonrise: Int,
        val moonset: Int,
        val pop: Double,
        val pressure: Int,
        val rain: Double,
        val sunrise: Int,
        val sunset: Int,
        val temp: Temp,
        val uvi: Double,
        val weather: List<Weather>,
        val wind_deg: Int,
        val wind_gust: Double,
        val wind_speed: Double,
    ):Parcelable

    @Parcelize
    data class FeelsLike(
        val day: Double,
        val eve: Double,
        val morn: Double,
        val night: Double,
    ):Parcelable

    @Parcelize
    data class Temp(
        val day: Double,
        val eve: Double,
        val max: Double,
        val min: Double,
        val morn: Double,
        val night: Double,
    ):Parcelable

    @Parcelize
    data class Weather(
        val description: String,
        val icon: String,
        val id: Int,
        val main: String,
    ):Parcelable
}
