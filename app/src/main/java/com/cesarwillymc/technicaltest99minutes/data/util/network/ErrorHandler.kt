package com.cesarwillymc.technicaltest99minutes.data.util.network

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorSource
}
