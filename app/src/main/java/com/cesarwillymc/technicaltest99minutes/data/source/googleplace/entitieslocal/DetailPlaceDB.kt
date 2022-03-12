package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.local.localentities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cesarwillymc.technicaltest99minutes.data.source.room.DatabaseApp.Companion.TABLE_PLACE
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.PlaceReview

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@Entity(tableName = TABLE_PLACE)
data class DetailPlaceDB(
    @PrimaryKey(autoGenerate = false)
    val idPlace: String,
    val photos: List<String>,
    val icon: String,
    val name: String,
    val rating: Float,
    val reviews: List<PlaceReviewDB>,
    val isFavorite: Boolean,
    val latitude: Double,
    val longitude: Double
)
