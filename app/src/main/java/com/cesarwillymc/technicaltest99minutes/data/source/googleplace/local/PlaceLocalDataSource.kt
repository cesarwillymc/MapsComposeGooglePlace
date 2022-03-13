package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.local

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitieslocal.DetailPlaceDB
import com.cesarwillymc.technicaltest99minutes.extension.Result

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
interface PlaceLocalDataSource {

    suspend fun findByDistance(latitude: Double, longitude: Double): Result<List<DetailPlaceDB>>

    suspend fun getPlaceById(idPlace: String): Result<DetailPlaceDB?>

    suspend fun deletePlace(data: DetailPlaceDB): Result<Unit>

    suspend fun addPlace(data: DetailPlaceDB): Result<Unit>
}
