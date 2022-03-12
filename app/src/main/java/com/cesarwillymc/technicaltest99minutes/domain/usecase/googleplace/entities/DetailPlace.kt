package com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
data class DetailPlace(
    val idPlace: String,
    val photos: List<String>,
    val icon: String,
    val name: String,
    val rating: Float,
    val reviews: List<PlaceReview>,
    val isFavorite: Boolean,
    val latitude: Double,
    val longitude: Double,
    val contactNumber: String,
    val address: String
)
