package com.cesarwillymc.technicaltest99minutes.data.util.local

import androidx.room.TypeConverter
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitieslocal.PlaceReviewDB
import com.cesarwillymc.technicaltest99minutes.extension.fromJson
import com.cesarwillymc.technicaltest99minutes.extension.toJson

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
class Converters {
    @TypeConverter
    fun listReviewToString(listData: List<PlaceReviewDB>): String {
        return toJson(listData)
    }

    @TypeConverter
    fun jsonToListReview(json: String): List<PlaceReviewDB> {
        return fromJson(json)
    }

    @TypeConverter
    fun toString(listData: List<String>): String {
        return toJson(listData)
    }

    @TypeConverter
    fun toData(json: String): List<String> {
        return fromJson(json)
    }
}
