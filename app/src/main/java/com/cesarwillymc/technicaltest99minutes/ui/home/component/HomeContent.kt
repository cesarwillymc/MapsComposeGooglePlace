package com.cesarwillymc.technicaltest99minutes.ui.home.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.cesarwillymc.technicaltest99minutes.R
import com.cesarwillymc.technicaltest99minutes.extension.DELAY_500L
import com.cesarwillymc.technicaltest99minutes.extension.INTERVAL_DEFAULT
import com.cesarwillymc.technicaltest99minutes.extension.ZOOM_MAX
import com.cesarwillymc.technicaltest99minutes.extension.ZOOM_MIN
import com.cesarwillymc.technicaltest99minutes.extension.ZOOM_NORMAL
import com.cesarwillymc.technicaltest99minutes.ui.base.GreenFullScreenLoading
import com.cesarwillymc.technicaltest99minutes.ui.home.entities.HomeUiState
import com.cesarwillymc.technicaltest99minutes.ui.home.entities.PlacePresentation
import com.cesarwillymc.technicaltest99minutes.ui.theme.Normal100
import com.cesarwillymc.technicaltest99minutes.ui.theme.Small120
import com.cesarwillymc.technicaltest99minutes.ui.utils.bitmapDescriptorFromVector
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@Composable
@SuppressWarnings("LongMethod")
fun HomeContent(
    homeUiState: HomeUiState,
    navigateToDetail: (String) -> Unit,
    permissionUiState: Boolean?,
    currentLatLong: LatLng?,
    onClickButtonCurrentLocation: (LatLng?) -> Unit
) {
    val context = LocalContext.current
    val rememberCoroutine = rememberCoroutineScope()
    GreenFullScreenLoading(homeUiState.isLoading || permissionUiState != true)
    Scaffold(modifier = Modifier.fillMaxSize()) {
        var uiSettings by remember {
            mutableStateOf(
                MapUiSettings(
                    zoomControlsEnabled = false
                )
            )
        }
        var properties by remember {
            mutableStateOf(
                MapProperties(
                    mapType = MapType.NORMAL,
                    minZoomPreference = ZOOM_MIN,
                    maxZoomPreference = ZOOM_MAX,
                )
            )
        }
        var currentSelected by remember {
            mutableStateOf<PlacePresentation?>(null)
        }
        val cameraPositionState = rememberCameraPositionState {
            currentLatLong?.let {
                position = CameraPosition.fromLatLngZoom(it, ZOOM_NORMAL)
            }
        }
        LaunchedEffect(currentLatLong) {
            snapshotFlow { currentLatLong }.collectLatest {
                delay(DELAY_500L)
                uiSettings = uiSettings.copy(myLocationButtonEnabled = currentLatLong == null)
                properties = properties.copy(isMyLocationEnabled = currentLatLong == null)
                if (cameraPositionState.position.target != currentLatLong) {
                    currentLatLong?.let {
                        cameraPositionState.animate(
                            CameraUpdateFactory.newLatLngZoom(
                                it,
                                ZOOM_NORMAL
                            )
                        )
                    }
                }
            }
        }

        if (permissionUiState == true)
            Box(Modifier.fillMaxSize()) {
                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    properties = properties,
                    uiSettings = uiSettings,
                    onMapClick = {
                        currentSelected = null
                    },
                    onMyLocationButtonClick = {
                        rememberCoroutine.launch {
                            delay(INTERVAL_DEFAULT)
                            onClickButtonCurrentLocation.invoke(cameraPositionState.position.target)
                        }
                        false
                    },
                    cameraPositionState = cameraPositionState

                ) {
                    currentLatLong?.let {
                        Marker(
                            position = it,
                            icon = R.drawable.ic_current_ubication.bitmapDescriptorFromVector(
                                context = context
                            )
                        )
                    }
                    if (homeUiState.isComplete) {
                        homeUiState.listData.map {
                            Marker(
                                position = it.latLong,
                                icon = R.drawable.ic_pin.bitmapDescriptorFromVector(context = context),
                                onClick = { _ ->
                                    currentSelected = it
                                    true
                                }
                            )
                        }
                    }
                }
                if (currentLatLong != null)
                    ButtonIconCard(
                        modifier = Modifier.align(Alignment.TopEnd),
                        padding = Small120,
                        resource = R.drawable.ic_btn_current_location,
                        onClick = { onClickButtonCurrentLocation.invoke(null) }
                    )

                currentSelected?.let {
                    ProductItemCard(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .fillMaxWidth()
                            .padding(
                                Normal100
                            ),
                        it,
                        onClickItem = navigateToDetail
                    )
                }
            }
    }
}
