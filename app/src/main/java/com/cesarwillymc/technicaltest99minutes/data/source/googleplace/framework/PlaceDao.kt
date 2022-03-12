package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.framework

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitieslocal.DetailPlaceDB
import com.cesarwillymc.technicaltest99minutes.data.source.room.DatabaseApp.Companion.TABLE_PLACE
import com.cesarwillymc.technicaltest99minutes.extension.LIMIT_DATA_FOR_TABLE

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

@Dao
@SuppressWarnings("MaxLineLength")
interface PlaceDao {

    @Query("SELECT * FROM $TABLE_PLACE ORDER BY ABS(latitude - :latitude) + ABS(longitude - :longitude) ASC limit $LIMIT_DATA_FOR_TABLE")
    suspend fun findByDistance(latitude: Double, longitude: Double): List<DetailPlaceDB>

    @Query("SELECT * FROM $TABLE_PLACE WHERE :idPlace like idPlace")
    suspend fun getPlaceById(idPlace: String): DetailPlaceDB?

    @Delete
    suspend fun deletePlace(data: DetailPlaceDB)

    @Insert
    suspend fun addPlace(data: DetailPlaceDB)
}
