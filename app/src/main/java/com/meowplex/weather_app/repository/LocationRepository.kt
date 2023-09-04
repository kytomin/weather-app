package com.meowplex.weather_app.repository

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationRepository @Inject constructor(
    private val locationManager: LocationManager
) {
    private val _isUpdating = MutableLiveData<Boolean>(false)
    val isUpdating: LiveData<Boolean> = _isUpdating

    private val _lastLocation = MutableLiveData<Location?>(null)
    val lastLocation: LiveData<Location?> = _lastLocation

    private val listener = LocationListener {
        _lastLocation.value = it
    }


    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000L,
            500f,
            listener
        )
        _isUpdating.value = true

    }

    fun stopLocationUpdates() {
        locationManager.removeUpdates(listener)
        _isUpdating.value = false
        _lastLocation.value = null
    }
}