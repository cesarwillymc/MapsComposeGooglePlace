package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entities.DetailPlaceResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entities.InfoPlaceResponse
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.DetailPlace
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.Place
/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
interface PlaceDataMapper {
    fun responseToDomain(info: List<InfoPlaceResponse>): List<Place>
    fun responseToDomain(info: DetailPlaceResponse): DetailPlace?
}
