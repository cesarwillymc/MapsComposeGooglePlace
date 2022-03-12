package com.cesarwillymc.technicaltest99minutes.domain.mapper

import com.cesarwillymc.technicaltest99minutes.extension.Result
import com.cesarwillymc.technicaltest99minutes.extension.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

abstract class Mapper<In, Out> {

    fun domainToPresentation(info: Flow<Result<In>>): Flow<Result<Out>> = info.map {
        domainToPresentation(it)
    }

    fun domainToPresentation(info: Result<In>): Result<Out> = when {
        info.isSuccess -> Result.Success(map((info as Result.Success).data!!))
        else -> Result.Error((info as Result.Error).exception)
    }

    abstract fun map(info: In): Out
}
