package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse

import com.google.gson.annotations.SerializedName

data class InfoPlaceResponse(
    val icon: String, // https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/restaurant-71.png
    val name: String, // Restaurant Hubert
    @SerializedName("opening_hours")
    val openingHours: OpeningHours,
    val photos: List<PlacePhotoResponse>?,
    @SerializedName("place_id")
    val placeId: String, // ChIJF5-RdGquEmsR5rN_H74uSqQ
    @SerializedName("price_level")
    val priceLevel: Int, // 3
    val rating: Double, // 4.6
    val types: List<String>,
    @SerializedName("user_ratings_total")
    val userRatingsTotal: Int, // 2228
    val vicinity: String, // 15 Bligh St, Sydney
    val geometry: GeometryResponse
)
