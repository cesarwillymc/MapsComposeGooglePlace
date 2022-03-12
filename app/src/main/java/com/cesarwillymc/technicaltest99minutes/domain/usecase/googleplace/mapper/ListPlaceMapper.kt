package com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.mapper

import com.cesarwillymc.technicaltest99minutes.domain.mapper.Mapper
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.Place
import com.cesarwillymc.technicaltest99minutes.ui.home.entities.PlacePresentation
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

/**
 * Created by Willy on 15/12/2021.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
class ListPlaceMapper @Inject constructor() :
    Mapper<List<Place>, List<PlacePresentation>>() {
    override fun map(info: List<Place>): List<PlacePresentation> {
        return info.map {
            PlacePresentation(
                idPlace = it.idPlace,
                mainPhoto = it.mainPhoto,
                icon = it.icon,
                name = it.name,
                rating = it.rating,
                isOpen = it.isOpen,
                isFavorite = it.isFavorite,
                latLong = LatLng(
                    it.latitude,
                    it.longitude
                )
            )
        }
    }
}
