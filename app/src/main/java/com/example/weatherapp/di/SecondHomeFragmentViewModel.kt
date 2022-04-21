package com.example.weatherapp.di

import com.example.weatherapp.ui.homefragments.secondfragment.SecondFragmentHomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val secondHomeFragmentViewModel = module {
    viewModel { SecondFragmentHomeViewModel(get()) }
}