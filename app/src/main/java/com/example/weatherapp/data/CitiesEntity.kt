package com.example.weatherapp.data

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CitiesEntity(
    @PrimaryKey
    @NonNull
    val id: Int,
    val lat: Float,
    val lon: Float,
    val tempCurrent: Double,
    val humidityCurrent: Int,
    val tempTomorrow: Double,
    val humidityTomorrow: Int,
    val imgCurrent: String,
    val imgTomorrow: String,
)
