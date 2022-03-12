package com.cesarwillymc.technicaltest99minutes.extension

import com.cesarwillymc.technicaltest99minutes.data.util.network.ErrorSource
import com.cesarwillymc.technicaltest99minutes.domain.base.entities.Failure

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

fun Exception.toDomain() = when (this) {
    is ErrorSource.Network -> Failure.NoInternet
    is ErrorSource.ServiceError -> Failure.Source(errorCode, errorMessage)
    else -> Failure.Generic
}
