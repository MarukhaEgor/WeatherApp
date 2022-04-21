package com.example.weatherapp.di

import com.example.weatherapp.ui.homefragments.firstfragment.FirstHomeFragmentViewModel
import com.example.weatherapp.ui.homefragments.secondfragment.SecondFragmentHomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val firstHomeFragmentViewModel = module {
    viewModel { FirstHomeFragmentViewModel(get()) }
}