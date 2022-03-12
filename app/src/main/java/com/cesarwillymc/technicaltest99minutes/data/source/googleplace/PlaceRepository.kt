package com.cesarwillymc.technicaltest99minutes.data.source.googleplace

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper.PlaceDataMapper
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.remote.PlaceRemoteDataSource
import com.cesarwillymc.technicaltest99minutes.data.util.coroutine.ConnectionUtils
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.DetailPlace
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.Place
import com.cesarwillymc.technicaltest99minutes.extension.Result
import javax.inject.Inject

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
class PlaceRepository @Inject constructor(
    private val dataSource: PlaceRemoteDataSource,
    private val mapper: PlaceDataMapper,
    private val connectionUtils: ConnectionUtils
) : PlaceDataSource {
    override suspend fun getNearbyPlace(lat: Double, long: Double): Result<List<Place>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlaceDetail(idPlace: String): Result<DetailPlace> {
        TODO("Not yet implemented")
    }


}
