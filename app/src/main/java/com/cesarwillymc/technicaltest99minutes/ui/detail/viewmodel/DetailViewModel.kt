package com.cesarwillymc.technicaltest99minutes.ui.detail.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.DeletePlaceUseCase
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.GetPlaceIdUseCase
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.SavePlaceUseCase
import com.cesarwillymc.technicaltest99minutes.extension.dataOrNull
import com.cesarwillymc.technicaltest99minutes.extension.isError
import com.cesarwillymc.technicaltest99minutes.extension.isSuccess
import com.cesarwillymc.technicaltest99minutes.ui.detail.entities.DetailUiState
import com.cesarwillymc.technicaltest99minutes.ui.route.action.HomeAction
import com.cesarwillymc.technicaltest99minutes.ui.utils.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val savePlaceUseCase: SavePlaceUseCase,
    private val deletePlaceUseCase: DeletePlaceUseCase,
    private val getPlaceId: GetPlaceIdUseCase
) : ViewModel() {

    val detailUiState get() = _detailUiState.asStateFlow()
    private val _detailUiState = MutableStateFlow(DetailUiState())

    private var placeId: String? = null

    init {
        savedStateHandle.get<String>(HomeAction.MODEL_PRODUCT_ARGS)?.let {
            placeId = it
            onLoadPlaceId()
        }
    }

    fun onMarkFavorite() {
        detailUiState.value.data?.let { detail ->
            launch {
                savePlaceUseCase(detail).let {
                    if (it.isSuccess)
                        onUpdateData(true)
                }
            }
        }
    }

    fun onUnMarkFavorite() {
        detailUiState.value.data?.let { detail ->
            launch {
                deletePlaceUseCase(detail).let {
                    if (it.isSuccess)
                        onUpdateData(false)
                }
            }
        }
    }

    private fun onUpdateData(isFavorite: Boolean) {
        _detailUiState.update {
            it.copy(
                data = it.data?.copy(
                    isFavorite = isFavorite
                )
            )
        }
    }

    fun onLoadPlaceId() {
        placeId?.let {
            _detailUiState.update {
                DetailUiState(
                    isLoading = true
                )
            }
            launch {
                getPlaceId(it).let { result ->
                    _detailUiState.update {
                        DetailUiState(
                            isLoading = false,
                            isError = result.isError,
                            data = result.dataOrNull()
                        )
                    }
                }
            }
        }
    }
}
