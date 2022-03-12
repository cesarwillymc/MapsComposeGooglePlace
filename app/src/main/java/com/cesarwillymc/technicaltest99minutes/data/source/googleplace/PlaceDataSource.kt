package com.cesarwillymc.technicaltest99minutes.data.source.googleplace

import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.DetailPlace
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.Place
import com.cesarwillymc.technicaltest99minutes.extension.Result

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
interface PlaceDataSource {
    suspend fun getNearbyPlace(lat: Double, long: Double): Result<List<Place>>
    suspend fun getPlaceDetail(idPlace: String): Result<DetailPlace>
}
