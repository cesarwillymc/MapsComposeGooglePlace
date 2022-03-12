package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse

import com.google.gson.annotations.SerializedName

data class PlaceReviewResponse(
    @SerializedName("author_name")
    val authorName: String, // Shari Vimal
    @SerializedName("author_url")
    val authorUrl: String, // https://www.google.com/maps/contrib/116914868362703415007/reviews
    @SerializedName("profile_photo_url")
    val profilePhotoUrl: String, // https://lh3.googleusercontent.com/a-/AOh14Gg-pR5QVACbSTPF1G-CWJ4GMLhBSvFX2Amf40RUaw=s128-c0x00000000-cc-rp-mo-ba2
    @SerializedName("rating")
    val rating: Float, // 5
    @SerializedName("relative_time_description")
    val relativeTimeDescription: String, // 10 meses atrás
    @SerializedName("text")
    val text: String,
    @SerializedName("time")
    val time: Int // 1621051823
)
