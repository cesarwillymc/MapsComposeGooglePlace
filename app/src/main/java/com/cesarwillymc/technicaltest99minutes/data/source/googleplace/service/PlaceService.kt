package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.service

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entities.DetailPlaceResultResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entities.PlaceResultResponse
import com.cesarwillymc.technicaltest99minutes.extension.DEFAULT_MAX_RADIUS
import com.cesarwillymc.technicaltest99minutes.extension.TYPE_DEFAULT_PLACE
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Willy on 15/12/2021.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
interface PlaceService {

    @GET(URL_NEARBY)
    suspend fun getNearbyPlace(
        @Query(RADIUS) radius: Int = DEFAULT_MAX_RADIUS,
        @Query(TYPES) types: String = TYPE_DEFAULT_PLACE,
        @Query(LATLONG) latLong: String
    ): PlaceResultResponse

    @GET(URL_DETAIL)
    suspend fun getPlaceDetail(@Query(ID_PLACE) placeId: String): DetailPlaceResultResponse

    companion object {
        private const val BASE_URL = "place"
        const val LATLONG = "location"
        const val ID_PLACE = "placeid"
        const val TYPES = "types"
        const val RADIUS = "radius"

        const val URL_NEARBY = "$BASE_URL/nearbysearch/json"
        const val URL_DETAIL = "$BASE_URL/details/json"
    }
}
