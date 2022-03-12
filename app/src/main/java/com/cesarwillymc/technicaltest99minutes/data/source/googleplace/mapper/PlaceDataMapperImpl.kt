package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitieslocal.DetailPlaceDB
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitieslocal.PlaceReviewDB
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.DetailPlaceResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.DetailPlaceResultResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.InfoPlaceResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.PlaceResultResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.PlaceReviewResponse
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

    override fun responseToDomain(info: PlaceResultResponse): List<Place> {
        return info.result.map {
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

    override fun responseToDomain(detail: DetailPlaceResultResponse): DetailPlace {
        return detail.result.let { info ->
            DetailPlace(
                idPlace = info.placeId,
                photos = info.photos?.map { it.reference }.orEmpty(),
                name = info.name,
                rating = info.rating.toFloat(),
                reviews = info.reviews?.map { responseToDomain(it) }.orEmpty(),
                isFavorite = false,
                icon = info.icon,
                latitude = info.geometry.location.lat,
                longitue = info.geometry.location.lng
            )
        }
    }

    override fun domainToDb(info: DetailPlace): DetailPlaceDB {
        return DetailPlaceDB(
            idPlace = info.idPlace,
            photos = info.photos,
            icon = info.icon,
            name = info.name,
            rating = info.rating,
            reviews = info.reviews.map {
                PlaceReviewDB(
                    authorName = it.authorName,
                    authorUrl = it.authorUrl,
                    profilePhotoUrl = it.profilePhotoUrl,
                    rating = it.rating,
                    relativeTimeDescription = it.relativeTimeDescription
                )
            },
            isFavorite = true,
            latitude = info.latitude,
            longitude = info.longitue
        )
    }

    override fun dbToDomain(info: List<DetailPlaceDB>): List<Place> {
        return info.map {
            Place(
                it.idPlace,
                it.photos.first(),
                it.icon,
                it.name,
                it.rating,
                false,
                it.isFavorite
            )
        }
    }

    override fun dbToDomain(info: DetailPlaceDB): DetailPlace {
        return DetailPlace(
            idPlace = info.idPlace,
            photos = info.photos,
            icon = info.icon,
            name = info.name,
            rating = info.rating,
            reviews = info.reviews.map {
                PlaceReview(
                    authorName = it.authorName,
                    authorUrl = it.authorUrl,
                    profilePhotoUrl = it.profilePhotoUrl,
                    rating = it.rating,
                    relativeTimeDescription = it.relativeTimeDescription
                )
            },
            isFavorite = info.isFavorite,
            latitude = info.latitude,
            longitue = info.longitude
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
