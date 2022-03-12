package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitieslocal.DetailPlaceDB
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.DetailPlaceResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.DetailPlaceResultResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.InfoPlaceResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.PlaceResultResponse
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.DetailPlace
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.Place
/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
interface PlaceDataMapper {
    fun responseToDomain(info: PlaceResultResponse): List<Place>
    fun responseToDomain(detail: DetailPlaceResultResponse): DetailPlace
    fun domainToDb(info: DetailPlace): DetailPlaceDB
    fun dbToDomain(info: List<DetailPlaceDB>) : List<Place>
    fun dbToDomain(info: DetailPlaceDB) : DetailPlace
}
