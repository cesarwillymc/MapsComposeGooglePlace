package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse

import com.google.gson.annotations.SerializedName

data class OpeningHours(
    @SerializedName("open_now")
    val openNow: Boolean // false
)
