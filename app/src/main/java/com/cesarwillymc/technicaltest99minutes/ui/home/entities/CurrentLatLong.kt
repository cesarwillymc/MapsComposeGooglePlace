package com.cesarwillymc.technicaltest99minutes.ui.home.entities

import com.cesarwillymc.technicaltest99minutes.extension.ZERO
import com.google.android.gms.maps.model.LatLng

/**
 * Created by cesarwillymamanicanaza on 13/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
data class CurrentLatLong(
    val location: LatLng? = null,
    val random: Int = ZERO
)
