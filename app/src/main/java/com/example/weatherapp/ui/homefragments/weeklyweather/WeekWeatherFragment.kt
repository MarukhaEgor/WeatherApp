package com.example.weatherapp.ui.homefragments.weeklyweather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.databinding.FragmentWeekWeatherBinding
import com.example.weatherapp.model.WeekForecastWeatherData
import com.example.weatherapp.ui.homefragments.weeklyweather.adapter.WeekWeatherItemsAdapter


class WeekWeatherFragment : Fragment() {

    private lateinit var binding: FragmentWeekWeatherBinding

    private val homeAdapter by lazy {
        WeekWeatherItemsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentWeekWeatherBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = this.arguments
        val data: WeekForecastWeatherData? = bundle?.getParcelable("data")
        initRv()
        if(bundle != null){
            homeAdapter.submitList(data?.daily)
        }
    }

    private fun initRv() {
        binding.rvWeekWeatherList.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
    }
}