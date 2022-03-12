package com.cesarwillymc.technicaltest99minutes.data.util.network

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

sealed class ErrorSource : Exception() {

    object Network : ErrorSource()

    object Unknown : ErrorSource()

    data class ServiceError(val errorCode: String, val errorMessage: String) : ErrorSource()
}
