package com.cesarwillymc.technicaltest99minutes.data.source.googleplace

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.LATITUDE
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.LONGITUDE
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.PLACE_ID
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.detailPlace
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.detailResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.listDetail
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.placeResultResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.local.PlaceLocalDataSource
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper.PlaceDataMapper
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper.PlaceDataMapperImpl
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.remote.PlaceRemoteDataSource
import com.cesarwillymc.technicaltest99minutes.data.util.network.ConnectionUtils
import com.cesarwillymc.technicaltest99minutes.extension.ONE
import com.cesarwillymc.technicaltest99minutes.extension.Result
import com.cesarwillymc.technicaltest99minutes.extension.ZERO
import com.cesarwillymc.technicaltest99minutes.extension.getData
import com.cesarwillymc.technicaltest99minutes.extension.isSuccess
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@ExperimentalCoroutinesApi
class PlaceRepositoryTest {
    @RelaxedMockK
    lateinit var remoteDataSource: PlaceRemoteDataSource

    @RelaxedMockK
    lateinit var localDataSource: PlaceLocalDataSource

    lateinit var mapper: PlaceDataMapper

    @RelaxedMockK
    lateinit var connectionUtils: ConnectionUtils
    lateinit var placeDataSource: PlaceDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapper = PlaceDataMapperImpl()
        placeDataSource = PlaceRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource,
            mapper = mapper,
            connectionUtils = connectionUtils
        )
    }

    @Test
    fun getNearbyPlace() = runTest {
        coEvery { connectionUtils.isNetworkAvailable() } returns true
        coEvery { remoteDataSource.getNearbyPlace(LATITUDE, LONGITUDE) } returns Result.Success(placeResultResponse)
        placeDataSource.getNearbyPlace(LATITUDE, LONGITUDE).let {
            coVerify(exactly = ZERO) { localDataSource.findByDistance(LATITUDE, LONGITUDE) }
            coVerify(exactly = ONE) { remoteDataSource.getNearbyPlace(LATITUDE, LONGITUDE) }
            assertEquals(it.getData().isNotEmpty(), true)
        }
    }

    @Test
    fun getNearbyPlaceNotInternet() = runTest {
        coEvery { connectionUtils.isNetworkAvailable() } returns false
        coEvery { localDataSource.findByDistance(LATITUDE, LONGITUDE) } returns Result.Success(listDetail)
        placeDataSource.getNearbyPlace(LATITUDE, LONGITUDE).let {
            coVerify(exactly = ONE) { localDataSource.findByDistance(LATITUDE, LONGITUDE) }
            coVerify(exactly = ZERO) { remoteDataSource.getNearbyPlace(LATITUDE, LONGITUDE) }
            assertEquals(it.getData().isNotEmpty(), true)
        }
    }

    @Test
    fun getPlaceDetail() = runTest {
        coEvery { connectionUtils.isNetworkAvailable() } returns true
        coEvery { remoteDataSource.getPlaceDetail(PLACE_ID) } returns Result.Success(detailResponse)
        coEvery { localDataSource.getPlaceById(PLACE_ID) } returns Result.Success(null)
        placeDataSource.getPlaceDetail(PLACE_ID).let {
            assert(it.isSuccess)
            coVerify(exactly = ONE) { localDataSource.getPlaceById(PLACE_ID) }
            coVerify(exactly = ONE) { remoteDataSource.getPlaceDetail(PLACE_ID) }

            assert(!it.getData().isFavorite)
        }
    }

    @Test
    fun getPlaceDetailFavorite() = runTest {
        coEvery { connectionUtils.isNetworkAvailable() } returns true
        coEvery { remoteDataSource.getPlaceDetail(PLACE_ID) } returns Result.Success(detailResponse)
        coEvery { localDataSource.getPlaceById(PLACE_ID) } returns Result.Success(detailPlace)
        placeDataSource.getPlaceDetail(PLACE_ID).let {
            assert(it.isSuccess)
            coVerify(exactly = ONE) { localDataSource.getPlaceById(PLACE_ID) }
            coVerify(exactly = ONE) { remoteDataSource.getPlaceDetail(PLACE_ID) }

            assert(it.getData().isFavorite)
        }
    }

    @Test
    fun getPlaceDetailNotNetwork() = runTest {
        coEvery { connectionUtils.isNetworkAvailable() } returns false
        coEvery { localDataSource.getPlaceById(PLACE_ID) } returns Result.Success(detailPlace)
        placeDataSource.getPlaceDetail(PLACE_ID).let {
            assert(it.isSuccess)
            coVerify(exactly = ONE) { localDataSource.getPlaceById(PLACE_ID) }
            coVerify(exactly = ZERO) { remoteDataSource.getPlaceDetail(PLACE_ID) }

            assert(it.getData().isFavorite)
            assertEquals(it.getData().idPlace, detailPlace.idPlace)
        }
    }
}
