package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
data class PlacePhotoResponse(
    @SerializedName("photo_reference")
    val reference:String
)
