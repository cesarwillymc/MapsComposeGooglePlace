package com.cesarwillymc.technicaltest99minutes.domain.base

import com.cesarwillymc.technicaltest99minutes.domain.mapper.Mapper
import com.cesarwillymc.technicaltest99minutes.extension.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Created by cesarwillymamanicanaza on 11/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

/**
 * It can be used for use cases that need map a domain Entity
 * into Presentation Entity
 */
abstract class SuspendMapperUseCase<in Params, out Results, Type>(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val mapper: Mapper<Type, Results>
) {
    suspend operator fun invoke(parameters: Params): Result<Results> {
        return try {
            withContext(coroutineDispatcher) {
                mapper.domainToPresentation(execute(parameters))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: Params): Result<Type>
}
