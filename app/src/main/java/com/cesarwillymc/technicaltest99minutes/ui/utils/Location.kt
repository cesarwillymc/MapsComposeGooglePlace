package com.cesarwillymc.technicaltest99minutes.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import com.cesarwillymc.technicaltest99minutes.extension.LOCATION_REFRESH_DISTANCE
import com.cesarwillymc.technicaltest99minutes.extension.LOCATION_REFRESH_TIME
import com.google.android.gms.maps.model.LatLng

/**
 * Created by Willy on 27/12/2021.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@SuppressLint("MissingPermission")
fun Context.currentLocationByNetwork(location: (LatLng) -> Unit, error: () -> Unit) {
    gpsLocation {
        if (it == null) {
            val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            val locationListener = object : LocationListener {
                override fun onLocationChanged(latLng: Location) {
                    location(LatLng(latLng.latitude, latLng.longitude))
                    locationManager.removeUpdates(this)
                }
            }
            if (hasNetwork) {
                locationManager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE,
                    locationListener
                )
            } else {
                error()
            }
        } else {
            location(it)
        }
    }
}

@SuppressLint("MissingPermission")
fun Context.currentLocationGps(location: (LatLng?) -> Unit) {
    gpsLocation {
        if (it == null) {
            val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
            val hasNetwork = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

            val locationListener = object : LocationListener {
                override fun onLocationChanged(latLng: Location) {
                    location(LatLng(latLng.latitude, latLng.longitude))
                    locationManager.removeUpdates(this)
                }
            }
            if (hasNetwork) {
                locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE,
                    locationListener
                )
            } else {
                location(null)
            }
        } else {
            location(it)
        }
    }
}

@SuppressLint("MissingPermission")
fun Context.gpsLocation(location: (LatLng?) -> Unit) {
    val locationManager =
        getSystemService(Context.LOCATION_SERVICE) as LocationManager?
    val gpsLoc = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
    val networkLoc = locationManager?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
    when {
        gpsLoc != null -> {
            location(LatLng(gpsLoc.latitude, gpsLoc.longitude))
        }
        networkLoc != null -> {
            location(LatLng(networkLoc.latitude, networkLoc.longitude))
        }
        else -> {
            location(null)
        }
    }
}
