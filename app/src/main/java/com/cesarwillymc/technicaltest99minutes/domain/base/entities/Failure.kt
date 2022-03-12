package com.cesarwillymc.technicaltest99minutes.domain.base.entities

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

sealed class Failure : Exception() {
    object NoInternet : Failure()

    object Generic : Failure()

    data class Source(val code: String? = null, val errorMessage: String) : Failure()
}
