package com.meowplex.weather_app.ui.screens.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.meowplex.weather_app.model.TemperatureUnits
import com.meowplex.weather_app.repository.SharedPrefsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val sharedPrefsRepository: SharedPrefsRepository
) : ViewModel() {

    private val _currentTempUnit =
        MutableLiveData(sharedPrefsRepository.getTempUnit() ?: TemperatureUnits.Celsius)
    val currentTempUnit: LiveData<TemperatureUnits> = _currentTempUnit

    fun onSelectTempUnit(value: TemperatureUnits) {
        _currentTempUnit.value = value
        sharedPrefsRepository.setTempUnit(value)
    }
}