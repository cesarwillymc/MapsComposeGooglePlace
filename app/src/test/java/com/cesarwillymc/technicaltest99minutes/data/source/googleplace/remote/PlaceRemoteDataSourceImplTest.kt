package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.remote

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.LATITUDE
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.LONGITUDE
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.PLACE_ID
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.detailResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.placeResultResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.framework.PlaceService
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.remote.PlaceRemoteDataSourceImpl.Companion.LOCATION_ARG_FORMAT
import com.cesarwillymc.technicaltest99minutes.extension.getData
import com.cesarwillymc.technicaltest99minutes.extension.isSuccess
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@ExperimentalCoroutinesApi
class PlaceRemoteDataSourceImplTest {

    @RelaxedMockK
    lateinit var service: PlaceService

    lateinit var placeRemoteDataSource: PlaceRemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        placeRemoteDataSource = PlaceRemoteDataSourceImpl(service)
    }

    @Test
    fun getNearbyPlace() = runTest {
        coEvery {
            service.getNearbyPlace(
                latLong = LOCATION_ARG_FORMAT.format(
                    LATITUDE,
                    LONGITUDE
                )
            )
        } returns placeResultResponse
        placeRemoteDataSource.getNearbyPlace(LATITUDE, LONGITUDE).let {
            assert(it.isSuccess)
            Assert.assertEquals(
                placeResultResponse.results?.first()?.icon,
                it.getData().results?.first()?.icon
            )
        }
    }

    @Test
    fun getPlaceDetail() = runTest {
        coEvery { service.getPlaceDetail(PLACE_ID) } returns detailResponse
        placeRemoteDataSource.getPlaceDetail(PLACE_ID).let {
            assert(it.isSuccess)
            Assert.assertEquals(
                detailResponse.result.contactNumber,
                it.getData().result.contactNumber
            )
        }
    }
}
