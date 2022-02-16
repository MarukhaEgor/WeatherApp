package com.example.weatherapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CitiesDao {
    @Query("SELECT * FROM CitiesEntity ORDER BY id ASC")
    fun getAllCities(): Flow<List<CitiesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: CitiesEntity)
}