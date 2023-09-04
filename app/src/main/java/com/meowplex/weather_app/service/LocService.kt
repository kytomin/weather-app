package com.meowplex.weather_app.service

import android.annotation.SuppressLint
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocService @Inject constructor(
    private val locationManager: LocationManager,
) {

    private val listener = LocationListener { loc ->
        subscribers.forEach { subscriber ->
            lastLocation = loc
            subscriber.onLocationChanged(loc)
        }
    }

    private val subscribers: ArrayList<MyLocationListener> = arrayListOf()

    private var lastLocation: Location? = null


    @SuppressLint("MissingPermission")
    fun startLocationUpdates(subscriber: MyLocationListener) {
        subscribers.add(subscriber)
        if (lastLocation != null) subscriber.onLocationChanged(lastLocation!!)

        if (subscribers.size > 1)
            return
        try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                1000L,
                500f,
                listener
            )
        } catch (e: Exception) {
            Log.e("Permission", "${e.message}")
        }

    }

    fun stopLocationUpdates(subscriber: MyLocationListener) {
        subscribers.remove(subscriber)

        if (subscribers.size != 0)
            return

        try {
            locationManager.removeUpdates(listener)
        } catch (e: Exception) {
            Log.e("Permission", "${e.message}")
        }
    }
}

interface MyLocationListener {
    fun onLocationChanged(location: Location)
}