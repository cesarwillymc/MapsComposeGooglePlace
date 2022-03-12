package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entities.DetailPlaceResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entities.InfoPlaceResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entities.PlaceReviewResponse
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.DetailPlace
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.Place
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.PlaceReview
import javax.inject.Inject

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
class PlaceDataMapperImpl @Inject constructor() :
    PlaceDataMapper {

    override fun responseToDomain(info: List<InfoPlaceResponse>): List<Place> {
        return info.map {
            Place(
                idPlace = it.place_id,
                mainPhoto = it.photos?.firstOrNull()?.reference,
                icon = it.icon,
                name = it.name,
                rating = it.rating.toFloat(),
                isOpen = it.opening_hours.open_now,
                isFavorite = false
            )
        }
    }

    override fun responseToDomain(info: DetailPlaceResponse): DetailPlace? {
        return DetailPlace(
            idPlace = info.placeId,
            photos = info.photos?.map { it.reference }.orEmpty(),
            name = info.name,
            rating = info.rating.toFloat(),
            reviews = info.reviews?.map { responseToDomain(it) }.orEmpty(),
            isFavorite = false,
            icon = info.icon
        )
    }

    private fun responseToDomain(info: PlaceReviewResponse) = PlaceReview(
        authorName = info.authorName,
        authorUrl = info.authorUrl,
        profilePhotoUrl = info.profilePhotoUrl,
        rating = info.rating,
        relativeTimeDescription = info.relativeTimeDescription
    )
}
