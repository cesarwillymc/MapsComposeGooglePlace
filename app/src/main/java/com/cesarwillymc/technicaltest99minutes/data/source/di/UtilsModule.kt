package com.cesarwillymc.technicaltest99minutes.data.source.di

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceDataSource
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepository
import com.cesarwillymc.technicaltest99minutes.data.util.coroutine.ConnectionUtils
import com.cesarwillymc.technicaltest99minutes.data.util.coroutine.ConnectionUtilsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class UtilsModule {
    @Binds
    abstract fun bindStatusInternet(connection: ConnectionUtilsImpl): ConnectionUtils
}