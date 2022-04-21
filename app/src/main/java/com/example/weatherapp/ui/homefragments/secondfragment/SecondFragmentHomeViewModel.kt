package com.example.weatherapp.ui.homefragments.secondfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.weatherapp.model.CurrentDayWeatherData
import com.example.weatherapp.model.WeekForecastWeatherData
import com.example.weatherapp.repository.Repository
import com.example.weatherapp.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class SecondFragmentHomeViewModel(private val repository: Repository) : ViewModel() {

    private val _navigationEvent = SingleLiveEvent<NavDirections>()
    val navigationEvent: LiveData<NavDirections> = _navigationEvent

    private val _weatherDetail = MutableLiveData<CurrentDayWeatherData>()
    val weatherDetail: LiveData<CurrentDayWeatherData> = _weatherDetail

    private val _weekWeatherDetail = MutableLiveData<WeekForecastWeatherData>()

    fun getWeatherData(lat: Float, lon: Float) {
        viewModelScope.launch {
            val weather = repository.getWeatherDetails(lat, lon)
            _weatherDetail.postValue(weather)
        }
    }

    fun getAllDayWeatherData(lat: Float, lon: Float) {
        viewModelScope.launch {
            val weather = repository.getAllWeatherDetail(lat, lon)
            _weekWeatherDetail.postValue(weather)
        }
    }

    fun goToTheWeeklyData() {
        _navigationEvent.value =
            SecondHomeFragmentDirections.actionSecondHomeFragmentToWeekWeatherFragment(
                _weekWeatherDetail.value)
    }

    fun saveLocation(lat: Float, lon: Float) {
        viewModelScope.launch {
            repository.saveLocation(0, lat, lon)
        }
    }

}