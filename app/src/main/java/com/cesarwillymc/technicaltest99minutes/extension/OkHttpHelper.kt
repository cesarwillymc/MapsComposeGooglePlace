package com.cesarwillymc.technicaltest99minutes.extension

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpHelper {

    fun getOkHttpBuilder(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            connectTimeout(TIMEOUT, TimeUnit.MINUTES)
            readTimeout(TIMEOUT, TimeUnit.MINUTES)
            writeTimeout(TIMEOUT, TimeUnit.MINUTES)
        }
}
