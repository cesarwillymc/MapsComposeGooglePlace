package com.cesarwillymc.technicaltest99minutes.data.util

import com.cesarwillymc.technicaltest99minutes.data.util.network.ErrorApi
import com.cesarwillymc.technicaltest99minutes.data.util.network.ErrorHandler
import com.cesarwillymc.technicaltest99minutes.data.util.network.ErrorSource
import com.cesarwillymc.technicaltest99minutes.extension.Result
import com.cesarwillymc.technicaltest99minutes.extension.toDomain
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

abstract class BaseRemoteDataSource : ErrorHandler {

    protected suspend fun <Out> getResult(
        call: suspend () -> Out
    ): Result<Out> = try {
        Result.Success(call())
    } catch (e: Exception) {
        Result.Error(exception = getError(e).toDomain())
    }

    override fun getError(throwable: Throwable): ErrorSource = when (throwable) {
        is IOException -> ErrorSource.Network
        is HttpException -> getErrorFromBody(throwable.response()?.errorBody())
        else -> ErrorSource.Unknown
    }

    private fun getErrorFromBody(errorBody: ResponseBody?): ErrorSource {
        return errorBody?.let {
            try {
                val errorApi = Gson().fromJson(it.string(), ErrorApi::class.java)
                errorApi.errorCode?.let { code ->
                    ErrorSource.ServiceError(
                        code.toString(),
                        errorApi.message.orEmpty()
                    )
                } ?: ErrorSource.Unknown
            } catch (jsonException: JsonSyntaxException) {
                ErrorSource.Unknown
            }
        } ?: ErrorSource.Unknown
    }
}
