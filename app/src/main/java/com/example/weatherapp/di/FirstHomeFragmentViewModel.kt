package com.example.weatherapp.di

import com.example.weatherapp.ui.homefragments.firstfragment.FirstHomeFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val firstHomeFragmentViewModel = module {
    viewModel { FirstHomeFragmentViewModel() }
}