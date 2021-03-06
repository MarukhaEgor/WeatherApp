package com.example.weatherapp.ui.homefragments.firstfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.weatherapp.data.CitiesEntity
import com.example.weatherapp.repository.Repository


class FirstHomeFragmentViewModel() : ViewModel() {
    val citiesData: LiveData<List<CitiesEntity>?> = Repository.allCities.asLiveData()
}