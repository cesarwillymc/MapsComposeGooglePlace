package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.local

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.LATITUDE
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.LONGITUDE
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.PLACE_ID
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.detailPlace
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.listDetail
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.framework.PlaceDao
import com.cesarwillymc.technicaltest99minutes.extension.getData
import com.cesarwillymc.technicaltest99minutes.extension.isSuccess
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@ExperimentalCoroutinesApi
class PlaceLocalDataSourceImplTest {

    @RelaxedMockK
    lateinit var dao: PlaceDao

    lateinit var placeLocalDataSource: PlaceLocalDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        placeLocalDataSource = PlaceLocalDataSourceImpl(dao)
    }

    @Test
    fun findByDistance() = runTest {
        coEvery { dao.findByDistance(LATITUDE, LONGITUDE) } returns listDetail
        placeLocalDataSource.findByDistance(LATITUDE, LONGITUDE).let {
            assert(it.isSuccess)
            assertEquals(it.getData().first().latitude.toString(), detailPlace.latitude.toString())
            assertEquals(it.getData().first().longitude.toString(), detailPlace.longitude.toString())
        }
    }

    @Test
    fun getPlaceById() = runTest {
        coEvery { dao.getPlaceById(PLACE_ID) } returns detailPlace
        placeLocalDataSource.getPlaceById(PLACE_ID).let {
            assertTrue(it.isSuccess)
            assertEquals(it.getData()?.latitude, detailPlace.latitude)
            assertEquals(it.getData()?.longitude, detailPlace.longitude)
        }
    }

    @Test
    fun deletePlace() = runTest {
        coEvery { dao.deletePlace(detailPlace) } returns Unit
        placeLocalDataSource.deletePlace(detailPlace).let {
            assertTrue(it.isSuccess)
        }
    }

    @Test
    fun addPlace() = runTest {
        coEvery { dao.addPlace(detailPlace) } returns Unit
        placeLocalDataSource.addPlace(detailPlace).let {
            assertTrue(it.isSuccess)
        }
    }
}
