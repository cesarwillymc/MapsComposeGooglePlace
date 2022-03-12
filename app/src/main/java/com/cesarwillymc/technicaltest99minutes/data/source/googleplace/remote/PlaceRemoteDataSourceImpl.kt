package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.remote

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.framework.PlaceService
import com.cesarwillymc.technicaltest99minutes.data.util.BaseRemoteDataSource
import javax.inject.Inject

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
class PlaceRemoteDataSourceImpl @Inject constructor(
    private val service: PlaceService
) : PlaceRemoteDataSource, BaseRemoteDataSource() {

    companion object {
        const val LOCATION_ARG_FORMAT = "%f,%f"
    }

    override suspend fun getNearbyPlace(lat: Double, long: Double) = getResult {
        service.getNearbyPlace(latLong = LOCATION_ARG_FORMAT.format(lat, long))
    }

    override suspend fun getPlaceDetail(idPlace: String) = getResult {
        service.getPlaceDetail(idPlace)
    }
}
