package com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceDataSource
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.framework.PlaceService.Companion.ID_PLACE
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper.PlaceDataMapper
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper.PlaceDataMapperImpl
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
class GetPlaceIdUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: PlaceDataSource

    lateinit var mapper: PlaceDataMapper

    lateinit var useCaseTest: GetPlaceIdUseCase
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mapper = PlaceDataMapperImpl()
        useCaseTest = GetPlaceIdUseCase(repository, UnconfinedTestDispatcher())
    }

    @Test
    fun execute() = runTest {
        val response = mapper.responseToDomain(PlaceRepositoryDataGenerator.detailResponse)
        coEvery { repository.getPlaceDetail(ID_PLACE) } returns Result.Success(response)
        useCaseTest(ID_PLACE).let {
            assert(it.isSuccess)
        }
    }

    @Test
    fun executeError() = runTest {
        coEvery { repository.getPlaceDetail(ID_PLACE) } returns Result.Error(Exception("error"))
        useCaseTest(ID_PLACE).let {
            assert(it.isError)
        }
    }
}
