package com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceDataSource
import com.cesarwillymc.technicaltest99minutes.data.util.coroutine.IoDispatcher
import com.cesarwillymc.technicaltest99minutes.domain.base.SuspendUseCase
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.DetailPlace
import com.cesarwillymc.technicaltest99minutes.extension.Result
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
class GetPlaceIdUseCase @Inject constructor(
    private val repository: PlaceDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : SuspendUseCase<String, DetailPlace>(
    coroutineDispatcher = dispatcher
) {
    override suspend fun execute(parameters: String): Result<DetailPlace> {
        return repository.getPlaceDetail(parameters)
    }
}
