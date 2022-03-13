package com.cesarwillymc.technicaltest99minutes.data.source.googleplace

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitieslocal.DetailPlaceDB
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitieslocal.PlaceReviewDB
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.DetailPlaceResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.DetailPlaceResultResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.GeometryResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.InfoPlaceResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.LocationResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.OpeningHours
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.PlacePhotoResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.PlaceResultResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitiesresponse.PlaceReviewResponse

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
object PlaceRepositoryDataGenerator {
    const val PLACE_ID = "iddatagenerator"
    val LIST_PHOTOS = listOf("photo1", "photo2", "photo3")
    const val ICON = "icon1"
    const val NAME = "Region"
    const val RATING = 5f
    const val NAME_ADDRESS = "lIMA SAN MIGUEL"
    const val CONTACT_NUMBER = "962374234"
    val REVIEW = listOf(
        PlaceReviewDB(
            authorName = "autonr",
            authorUrl = "author url",
            profilePhotoUrl = "profile",
            rating = RATING,
            relativeTimeDescription = "description coment"
        )
    )
    val REVIEW_RESPONSE = listOf(
        PlaceReviewResponse(
            authorName = "autonr",
            authorUrl = "author url",
            profilePhotoUrl = "profile",
            rating = RATING,
            relativeTimeDescription = "description coment",
            text = "",
            time = 10
        )
    )
    const val LATITUDE = 70.123123
    const val LONGITUDE = 30.123123
    val detailPlace = DetailPlaceDB(
        PLACE_ID,
        photos = LIST_PHOTOS,
        ICON,
        NAME,
        RATING,
        reviews = REVIEW,
        isFavorite = true,
        NAME_ADDRESS,
        CONTACT_NUMBER,
        LATITUDE,
        LONGITUDE
    )
    val detailPlace2 = DetailPlaceDB(
        PLACE_ID + "2",
        photos = LIST_PHOTOS + "2",
        ICON + "2",
        NAME + "2",
        RATING,
        reviews = REVIEW,
        isFavorite = true,
        NAME_ADDRESS,
        CONTACT_NUMBER,
        LATITUDE,
        LONGITUDE
    )

    val placeResultResponse = PlaceResultResponse(
        results = listOf(
            InfoPlaceResponse(
                icon = ICON,
                name = NAME,
                openingHours = OpeningHours(true),
                photos = LIST_PHOTOS.map {
                    PlacePhotoResponse(it)
                },
                placeId = PLACE_ID,
                priceLevel = 10,
                rating = RATING.toDouble(),
                types = listOf(),
                userRatingsTotal = 20,
                vicinity = "",
                geometry = GeometryResponse(LocationResponse(LATITUDE, LONGITUDE))
            )
        ),
        status = "success"
    )

    val detailResponse = DetailPlaceResultResponse(
        result = DetailPlaceResponse(
            components = listOf(),
            formattedAddress = "direccion format",
            placeId = PLACE_ID,
            geometry = GeometryResponse(LocationResponse(LATITUDE, LONGITUDE)),
            contactNumber = "96260130",
            name = "RESTAURANT",
            rating = RATING.toDouble(),
            types = listOf(),
            photos = LIST_PHOTOS.map {
                PlacePhotoResponse(it)
            },
            vicinity = "",
            reviews = REVIEW_RESPONSE,
            icon = ICON
        )
    )
    val listDetail = listOf(detailPlace, detailPlace2)
}
