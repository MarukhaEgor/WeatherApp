package com.example.weatherapp.ui.homefragments.firstfragment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.weatherapp.data.CitiesEntity
import com.example.weatherapp.databinding.ItemsCitiesBinding
import com.example.weatherapp.utils.ImageSelector

class FirstFragmentAdapter: ListAdapter<CitiesEntity, FirstFragmentAdapter.ViewHolder>(HomeDiff) {

    object HomeDiff : DiffUtil.ItemCallback<CitiesEntity>() {
        override fun areItemsTheSame(
            oldItem: CitiesEntity,
            newItem: CitiesEntity,
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CitiesEntity,
            newItem: CitiesEntity,
        ): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(val binding: ItemsCitiesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemsCitiesBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = getItem(position)
        holder.binding.apply {
            tvCity.text = "how fix that? "
            ivTomorrowWeather.load(ImageSelector.selectImage(weather.imgTomorrow))
            ivCurrentWeather.load(ImageSelector.selectImage(weather.imgCurrent))
            tvToday.text = weather.tempCurrent.toString() + " / " + weather.humidityCurrent.toString() + "%" + " Today"
            tvTomorrow.text = weather.tempTomorrow.toString() + " / " + weather.humidityTomorrow.toString() + "%" + " Tomorrow"
        }
    }
}