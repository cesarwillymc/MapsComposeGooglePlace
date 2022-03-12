package com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
data class Place(
    val idPlace: String,
    val mainPhoto: String?,
    val icon: String,
    val name: String,
    val rating: Float,
    val isOpen: Boolean,
    val isFavorite: Boolean,
    val latitude: Double,
    val longitude: Double
)
