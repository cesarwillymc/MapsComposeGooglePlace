package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entities

import com.google.gson.annotations.SerializedName

data class ComponentsResponse(
    @SerializedName("long_name")
    val longName: String, // Chile
    @SerializedName("short_name")
    val shortName: String, // CL
    val types: List<String>
)
