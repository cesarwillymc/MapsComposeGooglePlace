package com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceDataSource
import com.cesarwillymc.technicaltest99minutes.data.util.coroutine.IoDispatcher
import com.cesarwillymc.technicaltest99minutes.domain.base.SuspendMapperUseCase
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.LatLongParams
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.Place
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.mapper.ListPlaceMapper
import com.cesarwillymc.technicaltest99minutes.extension.Result
import com.cesarwillymc.technicaltest99minutes.ui.home.entities.PlacePresentation
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
class GetNearbyPlacesUseCase @Inject constructor(
    private val repository: PlaceDataSource,
    mapper: ListPlaceMapper,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : SuspendMapperUseCase<LatLongParams, List<PlacePresentation>, List<Place>>(
    coroutineDispatcher = dispatcher,
    mapper = mapper
) {
    override suspend fun execute(parameters: LatLongParams): Result<List<Place>> {
        return repository.getNearbyPlace(parameters.latitude, parameters.longitude)
    }
}
