package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.detailPlace
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.detailResponse
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.listDetail
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepositoryDataGenerator.placeResultResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
class PlaceDataMapperImplTest {
    lateinit var mapper: PlaceDataMapper

    @Before
    fun setUp() {
        mapper = PlaceDataMapperImpl()
    }

    @Test
    fun responseToDomain() {
        mapper.responseToDomain(placeResultResponse).let {
            assertEquals(it.first().icon, placeResultResponse.result.first().icon)
            assertEquals(it.first().idPlace, placeResultResponse.result.first().placeId)
            assertEquals(it.first().name, placeResultResponse.result.first().name)
            assertEquals(
                it.first().mainPhoto,
                placeResultResponse.result.first().photos?.first()?.reference
            )
            assertEquals(
                it.first().latitude.toString(),
                placeResultResponse.result.first().geometry.location.lat.toString()
            )
            assertEquals(
                it.first().longitude.toString(),
                placeResultResponse.result.first().geometry.location.lng.toString()
            )
        }
    }

    @Test
    fun testResponseToDomain() {
        mapper.responseToDomain(detailResponse).let {
            assertEquals(it.address, detailResponse.result.formattedAddress)
            assertEquals(it.contactNumber, detailResponse.result.contactNumber)
            assertEquals(it.icon, detailResponse.result.icon)
            assertEquals(it.idPlace, detailResponse.result.placeId)
            assertEquals(it.name, detailResponse.result.name)
            assertEquals(it.photos.first(), detailResponse.result.photos?.first()?.reference)
            assertEquals(
                it.latitude.toString(),
                detailResponse.result.geometry.location.lat.toString()
            )
            assertEquals(
                it.longitude.toString(),
                detailResponse.result.geometry.location.lng.toString()
            )
        }
    }

    @Test
    fun domainToDb() {
        mapper.domainToDb(mapper.responseToDomain(detailResponse)).let {
            assertEquals(it.isFavorite, true)
        }
    }

    @Test
    fun dbToDomain() {
        mapper.dbToDomain(listDetail).let {
            assertEquals(it.first().icon, detailPlace.icon)
            assertEquals(it.first().idPlace, detailPlace.idPlace)
            assertEquals(it.first().name, detailPlace.name)
            assertEquals(it.first().mainPhoto, detailPlace.photos.first())
            assertEquals(it.first().latitude.toString(), detailPlace.latitude.toString())
            assertEquals(it.first().longitude.toString(), detailPlace.longitude.toString())
        }
    }

    @Test
    fun testDbToDomain() {
        mapper.dbToDomain(detailPlace).let {
            assertEquals(it.address, detailPlace.nameAddress)
            assertEquals(it.contactNumber, detailPlace.contactNumber)
            assertEquals(it.icon, detailPlace.icon)
            assertEquals(it.idPlace, detailPlace.idPlace)
            assertEquals(it.name, detailPlace.name)
            assertEquals(it.photos, detailPlace.photos)
            assertEquals(it.latitude.toString(), detailPlace.latitude.toString())
            assertEquals(it.longitude.toString(), detailPlace.longitude.toString())
        }
    }
}
