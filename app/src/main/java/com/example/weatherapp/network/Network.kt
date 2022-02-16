package com.example.weatherapp.network

import com.example.weatherapp.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {
    const val apikey = "9cac54088e7f05e5ad33493fa8c68a81"
    fun getApiServices(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}