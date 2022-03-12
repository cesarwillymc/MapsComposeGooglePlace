package com.cesarwillymc.technicaltest99minutes.data.di

import android.app.Application
import androidx.room.Room
import com.cesarwillymc.technicaltest99minutes.BuildConfig
import com.cesarwillymc.technicaltest99minutes.data.source.room.DatabaseApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: DatabaseApp.Callback): DatabaseApp {
        return Room.databaseBuilder(application, DatabaseApp::class.java, BuildConfig.DB_NAME)
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }
}
