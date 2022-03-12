package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitieslocal

data class PlaceReviewDB(
    val authorName: String,
    val authorUrl: String,
    val profilePhotoUrl: String,
    val rating: Float, // 5
    val relativeTimeDescription: String, // 10 meses atrás
)