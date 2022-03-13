package com.cesarwillymc.technicaltest99minutes.data.di

import com.cesarwillymc.technicaltest99minutes.BuildConfig
import com.cesarwillymc.technicaltest99minutes.data.source.googleplace.framework.PlaceService
import com.cesarwillymc.technicaltest99minutes.data.util.interceptor.GoogleInterceptor
import com.cesarwillymc.technicaltest99minutes.extension.OkHttpHelper.getOkHttpBuilder
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun gson() = Gson()

    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MAPS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesGoogleOkhttp(
        googleInterceptor: GoogleInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ) = getOkHttpBuilder(loggingInterceptor).addInterceptor(googleInterceptor).build()

    @Singleton
    @Provides
    fun providesGoogle(
        okHttpClient: OkHttpClient
    ) = provideServiceGoogle<PlaceService>(okHttpClient)

    @Singleton
    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private inline fun <reified T : Any> provideServiceGoogle(
        okhttpClient: OkHttpClient
    ): T {
        return providesRetrofit(okhttpClient).create(T::class.java)
    }
}
