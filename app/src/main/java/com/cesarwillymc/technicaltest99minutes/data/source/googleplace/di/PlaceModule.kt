package com.cesarwillymc.technicaltest99minutes.data.source.googleplace.di

import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceDataSource
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.PlaceRepository
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.local.PlaceLocalDataSource
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.local.PlaceLocalDataSourceImpl
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper.PlaceDataMapper
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.mapper.PlaceDataMapperImpl
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.remote.PlaceRemoteDataSource
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.remote.PlaceRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PlaceModule {

    @Binds
    abstract fun bindRepository(repository: PlaceRepository): PlaceDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: PlaceRemoteDataSourceImpl): PlaceRemoteDataSource

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: PlaceLocalDataSourceImpl): PlaceLocalDataSource

    @Binds
    abstract fun bindMapper(mapper: PlaceDataMapperImpl): PlaceDataMapper
}
