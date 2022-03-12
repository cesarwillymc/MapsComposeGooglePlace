package com.cesarwillymc.technicaltest99minutes.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.GetNearbyPlacesUseCase
import com.cesarwillymc.technicaltest99minutes.domain.usecase.googleplace.entities.LatLongParams
import com.cesarwillymc.technicaltest99minutes.ui.utils.launch
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

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
    fun onLoadNearbyPlaces(latLng:LatLng){
        launch {
            getNearbyPlacesUseCase(LatLongParams(latLng.latitude, latLng.longitude)).let {

            }
        }
    }
}