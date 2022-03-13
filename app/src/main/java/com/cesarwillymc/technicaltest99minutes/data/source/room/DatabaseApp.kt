package com.cesarwillymc.technicaltest99minutes.data.source.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.entitieslocal.DetailPlaceDB
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.framework.PlaceDao
import com.cesarwillymc.technicaltest99minutes.data.util.coroutine.IoDispatcher
import com.cesarwillymc.technicaltest99minutes.data.util.local.Converters
import com.cesarwillymc.technicaltest99minutes.extension.ONE
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

@Database(entities = [DetailPlaceDB::class], version = ONE, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DatabaseApp : RoomDatabase() {

    abstract fun getPlaceDao(): PlaceDao

    class Callback @Inject constructor(
        private val database: Provider<DatabaseApp>,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ) : RoomDatabase.Callback()

    companion object {
        const val TABLE_PLACE = "table_place"
    }
}
