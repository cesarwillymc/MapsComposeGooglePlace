package com.cesarwillymc.technicaltest99minutes.data.source.googleplace

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.local.PlaceLocalDataSource
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper.PlaceDataMapper
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.remote.PlaceRemoteDataSource
import com.cesarwillymc.technicaltest99minutes.data.util.network.ConnectionUtils
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.DetailPlace
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.Place
import com.cesarwillymc.technicaltest99minutes.extension.ERROR_DB
import com.cesarwillymc.technicaltest99minutes.extension.Result
import com.cesarwillymc.technicaltest99minutes.extension.dataOrNull
import com.cesarwillymc.technicaltest99minutes.extension.getData
import com.cesarwillymc.technicaltest99minutes.extension.isSuccess
import com.cesarwillymc.technicaltest99minutes.extension.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
class PlaceRepository @Inject constructor(
    private val remoteDataSource: PlaceRemoteDataSource,
    private val localDataSource: PlaceLocalDataSource,
    private val mapper: PlaceDataMapper,
    private val connectionUtils: ConnectionUtils
) : PlaceDataSource {
    override suspend fun getNearbyPlace(lat: Double, long: Double): Flow<List<Place>> {
        return if (connectionUtils.isNetworkAvailable()) {
            flowOf(
                remoteDataSource.getNearbyPlace(lat, long).map(mapper::responseToDomain).getData()
            )
        } else {
            localDataSource.findByDistance(lat, long).map { mapper.dbToDomain(it) }
        }
    }

    override suspend fun getPlaceDetail(idPlace: String): Result<DetailPlace> {
        val exist = localDataSource.getPlaceById(idPlace)
        return if (connectionUtils.isNetworkAvailable()) {
            remoteDataSource.getPlaceDetail(idPlace).map(mapper::responseToDomain).run {
                if (isSuccess) {
                    return@run Result.Success(
                        getData().copy(
                            isFavorite = exist.dataOrNull() != null
                        )
                    )
                } else {
                    return@run this@run
                }
            }
        } else {
            exist.dataOrNull()?.let {
                Result.Success(mapper.dbToDomain(it))
            } ?: Result.Error(Exception(ERROR_DB))
        }
    }


}
