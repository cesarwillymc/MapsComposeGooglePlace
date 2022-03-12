package com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities

data class PlaceReview(
    val authorName: String,
    val authorUrl: String,
    val profilePhotoUrl: String,
    val rating: Float, // 5
    val relativeTimeDescription: String, // 10 meses atrás
)