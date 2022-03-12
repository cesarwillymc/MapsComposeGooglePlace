package com.cesarwillymc.technicaltest99minutes.domain.base

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
 * It can be used for use cases that return Unit, Boolean, Int, etc.
 * No mapper needed
 */
abstract class SuspendUseCase<in Params, out Results>(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(parameters: Params): Result<Results> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters)
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: Params): Result<Results>
}

suspend operator fun <Results> SuspendUseCase<Unit, Results>.invoke(): Result<Results> = this(Unit)
