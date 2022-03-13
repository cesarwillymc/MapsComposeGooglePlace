package com.cesarwillymc.technicaltest99minutes.ui.home.entities

import com.google.android.gms.maps.model.LatLng

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
data class PlacePresentation(
    val idPlace: String,
    val mainPhoto: String?,
    val icon: String,
    val name: String,
    val rating: Float,
    val isOpen: Boolean,
    val isFavorite: Boolean,
    val latLong: LatLng
)
