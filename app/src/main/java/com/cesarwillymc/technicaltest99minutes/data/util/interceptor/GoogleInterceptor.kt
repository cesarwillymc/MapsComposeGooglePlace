package com.cesarwillymc.technicaltest99minutes.data.util.interceptor

import com.cesarwillymc.technicaltest99minutes.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
class GoogleInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter(TOKEN_KEY, BuildConfig.MAPS_API_KEY)
            .build()

        return chain.proceed(chain.request().newBuilder().url(url).build())
    }

    companion object {
        const val TOKEN_KEY = "key"
    }
}
