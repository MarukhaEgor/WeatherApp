package com.example.weatherapp.utils

import com.example.weatherapp.R

object ImageSelector {
    fun selectImage(code:String):Int{
        when(code){
            "01d"->return R.drawable.ic_sunny
            "02d"->return R.drawable.ic_mainly_cloudy
            "03d"->return R.drawable.ic_cloudy
            "04d"->return R.drawable.ic_cloudy
            "09d"->return R.drawable.ic_rain
            "10d"->return R.drawable.ic_rain
            "11d"->return R.drawable.ic_storm
            "13d"->return R.drawable.ic_snow
            "50d"->return R.drawable.ic_mainly_cloudy
            "01n"->return R.drawable.ic_night
            "02n"->return R.drawable.ic_night_cloudy
            "03n"->return R.drawable.ic_night_cloudy
            "04n"->return R.drawable.ic_night_cloudy
            "09n"->return R.drawable.ic_night_overcast_rain
            "10n"->return R.drawable.ic_night_overcast_rain
            "11n"->return R.drawable.ic_night_overcast_rain
            "13n"->return R.drawable.ic_snow
            "50n"->return R.drawable.ic_night
        }
        return R.drawable.ic_cloudy
    }
}