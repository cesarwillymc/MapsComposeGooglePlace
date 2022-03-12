package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.local

import androidx.room.Insert
import androidx.room.Query
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitieslocal.DetailPlaceDB
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.DetailPlaceResultResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.PlaceResultResponse
import com.cesarwillymc.technicaltest99minutes.data.source.room.DatabaseApp
import com.cesarwillymc.technicaltest99minutes.extension.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
interface PlaceLocalDataSource {

    suspend fun findByDistance(latitude: Double, longitude: Double): Flow<List<DetailPlaceDB>>

    suspend fun getPlaceById(idPlace: String): Result<DetailPlaceDB?>

    suspend fun deletePlace(data: DetailPlaceDB): Result<Unit>

    suspend fun addPlace(data: DetailPlaceDB): Result<Unit>
}
