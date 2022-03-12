package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.local

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitieslocal.DetailPlaceDB
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.framework.PlaceDao
import com.cesarwillymc.technicaltest99minutes.extension.Result
import javax.inject.Inject

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
class PlaceLocalDataSourceImpl @Inject constructor(
    private val dao: PlaceDao
) : PlaceLocalDataSource {
    override suspend fun findByDistance(
        latitude: Double,
        longitude: Double
    ) = try {
        Result.Success(dao.findByDistance(latitude, longitude))
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun getPlaceById(idPlace: String) = try {
        Result.Success(dao.getPlaceById(idPlace))
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun deletePlace(data: DetailPlaceDB) = try {
        Result.Success(dao.deletePlace(data = data))
    } catch (e: Exception) {
        Result.Error(e)
    }

    override suspend fun addPlace(data: DetailPlaceDB) = try {
        Result.Success(dao.addPlace(data))
    } catch (e: Exception) {
        Result.Error(e)
    }
}
