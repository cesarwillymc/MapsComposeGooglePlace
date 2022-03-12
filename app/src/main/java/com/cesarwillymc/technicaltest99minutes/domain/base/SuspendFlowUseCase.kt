package cl.difarma.ecommerce.chile.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

abstract class SuspendFlowUseCase<in Params, out Results>(
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(parameters: Params): Flow<Results> {
        return withContext(coroutineDispatcher) {
            execute(parameters)
                .flowOn(coroutineDispatcher)
        }
    }

    abstract suspend fun execute(parameters: Params): Flow<Results>
}
