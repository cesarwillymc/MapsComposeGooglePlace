package com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceDataSource
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.LATITUDE
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.LONGITUDE
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper.PlaceDataMapper
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper.PlaceDataMapperImpl
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.LatLongParams
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.mapper.ListPlaceMapper
import com.cesarwillymc.technicaltest99minutes.extension.Result
import com.cesarwillymc.technicaltest99minutes.extension.isError
import com.cesarwillymc.technicaltest99minutes.extension.isSuccess
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@ExperimentalCoroutinesApi
class GetNearbyPlacesUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: PlaceDataSource

    lateinit var mapper: PlaceDataMapper

    lateinit var mapperDomain: ListPlaceMapper

    lateinit var useCaseTest: GetNearbyPlacesUseCase
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapper = PlaceDataMapperImpl()
        mapperDomain = ListPlaceMapper()
        useCaseTest = GetNearbyPlacesUseCase(repository, mapperDomain, UnconfinedTestDispatcher())
    }

    @Test
    fun execute() = runTest {
        val response = mapper.responseToDomain(PlaceRepositoryDataGenerator.placeResultResponse)
        coEvery { repository.getNearbyPlace(LATITUDE, LONGITUDE) } returns Result.Success(response)
        useCaseTest(LatLongParams(LATITUDE, LONGITUDE)).let {
            assert(it.isSuccess)
        }
    }

    @Test
    fun executeError() = runTest {
        coEvery { repository.getNearbyPlace(LATITUDE, LONGITUDE) } returns Result.Error(Exception("error"))
        useCaseTest(LatLongParams(LATITUDE, LONGITUDE)).let {
            assert(it.isError)
        }
    }
}
