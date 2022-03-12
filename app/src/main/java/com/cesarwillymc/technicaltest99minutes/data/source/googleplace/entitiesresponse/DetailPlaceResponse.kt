package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse

import com.google.gson.annotations.SerializedName

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
data class DetailPlaceResponse(
    @SerializedName("address_components")
    val components: List<ComponentsResponse>,
    @SerializedName("formatted_address")
    val formattedAddress: String,
    @SerializedName("place_id")
    val placeId: String,
    val geometry: GeometryResponse,
    @SerializedName("formatted_phone_number")
    val contactNumber: String?,
    val name: String,
    val rating: Double, // 4.6
    val types: List<String>,
    val photos: List<PlacePhotoResponse>?,
    val vicinity: String, // 15 Bligh St, Sydney
    val reviews: List<PlaceReviewResponse>?,
    val icon: String
)