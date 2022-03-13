package com.cesarwillymc.technicaltest99minutes.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.GetNearbyPlacesUseCase
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.LatLongParams
import com.cesarwillymc.technicaltest99minutes.extension.dataOrNull
import com.cesarwillymc.technicaltest99minutes.extension.isError
import com.cesarwillymc.technicaltest99minutes.extension.isSuccess
import com.cesarwillymc.technicaltest99minutes.ui.home.entities.CurrentLatLong
import com.cesarwillymc.technicaltest99minutes.ui.home.entities.HomeUiState
import com.cesarwillymc.technicaltest99minutes.ui.utils.launch
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.random.Random

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNearbyPlacesUseCase: GetNearbyPlacesUseCase
) : ViewModel() {
    val homeUiState get() = _homeUiState.asStateFlow()
    private val _homeUiState = MutableStateFlow(HomeUiState())

    val permissionUiState get() = _permissionUiState.asStateFlow()
    private val _permissionUiState = MutableStateFlow<Boolean?>(null)

    val currentLatLong get() = _currentLatLong.asStateFlow()
    private val _currentLatLong = MutableStateFlow(CurrentLatLong())

    val forceLocationSetting get() = _forceLocationSetting.receiveAsFlow()
    private val _forceLocationSetting: Channel<Boolean> = Channel()

    fun onPermissionGranted() {
        _permissionUiState.value = true
        launch { _forceLocationSetting.send(true) }
    }

    fun onPermissionDenied() {
        _permissionUiState.value = false
    }

    fun onLoadNearbyPlaces(latLng: LatLng?) {
        _currentLatLong.value = CurrentLatLong(latLng, Random.nextInt())
        latLng?.let {
            if (!homeUiState.value.isComplete)
                _homeUiState.update { it.copy(isLoading = true) }
            launch {
                getNearbyPlacesUseCase(LatLongParams(latLng.latitude, latLng.longitude)).let { result ->
                    _homeUiState.update {
                        HomeUiState(
                            isLoading = false,
                            isComplete = result.isSuccess,
                            isError = result.isError,
                            listData = result.dataOrNull().orEmpty()
                        )
                    }
                }
            }
        }
    }
}
