package com.example.weatherapp.di

import android.app.Application
import androidx.room.Room
import com.example.weatherapp.data.db.CitiesDataBase
import com.example.weatherapp.utils.Constants
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    fun provideDataBase(application: Application): CitiesDataBase {
        return Room.databaseBuilder(application,
            CitiesDataBase::class.java,
            Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
    single { provideDataBase(androidApplication()) }
}