package com.example.weatherapp.ui.homefragments.weeklyweather.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherapp.databinding.ItemsWeekWeatherBinding
import com.example.weatherapp.model.WeekForecastWeatherData
import com.example.weatherapp.utils.ImageSelector
import java.text.SimpleDateFormat
import java.util.*

class WeekWeatherItemsAdapter : ListAdapter<WeekForecastWeatherData.Daily, WeekWeatherItemsAdapter.ViewHolder>(HomeDiff) {

    object HomeDiff : DiffUtil.ItemCallback<WeekForecastWeatherData.Daily>() {
        override fun areItemsTheSame(
            oldItem: WeekForecastWeatherData.Daily,
            newItem: WeekForecastWeatherData.Daily,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: WeekForecastWeatherData.Daily,
            newItem: WeekForecastWeatherData.Daily,
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(val binding: ItemsWeekWeatherBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemsWeekWeatherBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = getItem(position)
        val sdf = SimpleDateFormat("EEEE", Locale.ENGLISH)
        holder.binding.apply {
            ivDayWeather.load(ImageSelector.selectImage(weather.weather[0].icon))
            tvDayOfWeek.text = sdf.format(weather.dt * 1000) + "\n" + weather.temp.day + " / " + weather.humidity + "%" + " / " + weather.wind_speed + " m/s"
        }
    }

}