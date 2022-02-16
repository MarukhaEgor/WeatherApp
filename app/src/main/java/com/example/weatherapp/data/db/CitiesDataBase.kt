package com.example.weatherapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.data.CitiesDao
import com.example.weatherapp.data.CitiesEntity

@Database(entities = [CitiesEntity::class], version = 1, exportSchema = false)
abstract class CitiesDataBase : RoomDatabase() {
    abstract fun citiesDao(): CitiesDao
}