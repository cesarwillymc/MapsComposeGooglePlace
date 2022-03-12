package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.remote

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entities.DetailPlaceResultResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entities.PlaceResultResponse
import com.cesarwillymc.technicaltest99minutes.extension.Result
/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
interface PlaceRemoteDataSource {
    suspend fun getNearbyPlace(lat: Double, long: Double): Result<PlaceResultResponse>
    suspend fun getPlaceDetail(idPlace: String): Result<DetailPlaceResultResponse>
}
