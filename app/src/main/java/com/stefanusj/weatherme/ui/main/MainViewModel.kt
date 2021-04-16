package com.stefanusj.weatherme.ui.main

import androidx.lifecycle.ViewModel
import com.stefanusj.weatherme.usecase.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    currentWeatherUseCase: GetCurrentWeatherUseCase,
) : ViewModel() {

    val currentWeather = currentWeatherUseCase()

}