package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse

data class InfoPlaceResponse(
    val icon: String, // https://maps.gstatic.com/mapfiles/place_api/icons/v1/png_71/restaurant-71.png
    val name: String, // Restaurant Hubert
    val opening_hours: OpeningHours,
    val photos: List<PlacePhotoResponse>?,
    val place_id: String, // ChIJF5-RdGquEmsR5rN_H74uSqQ
    val price_level: Int, // 3
    val rating: Double, // 4.6
    val types: List<String>,
    val user_ratings_total: Int, // 2228
    val vicinity: String, // 15 Bligh St, Sydney
    val geometry: GeometryResponse
)
