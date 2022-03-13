package com.cesarwillymc.technicaltest99minutes.ui.home

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.cesarwillymc.technicaltest99minutes.ui.home.component.HomeContent
import com.cesarwillymc.technicaltest99minutes.ui.home.component.HomePermissionHelper
import com.cesarwillymc.technicaltest99minutes.ui.home.viewmodel.HomeViewModel
import com.cesarwillymc.technicaltest99minutes.ui.utils.currentLocationByNetwork
import com.cesarwillymc.technicaltest99minutes.ui.utils.currentLocationGps

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */

@Composable
fun HomeScreen(
    navigateToDetail: (String) -> Unit,
    navigateToSetting: () -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val homeUiState by homeViewModel.homeUiState.collectAsState()
    val permissionUiState by homeViewModel.permissionUiState.collectAsState()
    val forceLocationSetting by homeViewModel.forceLocationSetting.collectAsState(false)
    val currentLatLong by homeViewModel.currentLatLong.collectAsState()
    val context = LocalContext.current
    HomePermissionHelper(
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
        onGranted = homeViewModel::onPermissionGranted,
        onDenied = homeViewModel::onPermissionDenied,
        showPermissionPopup = true
    )

    /* show location setting popup if user grant permission */
    LaunchedEffect(forceLocationSetting) {
        if (forceLocationSetting)
            context.currentLocationByNetwork(
                location = homeViewModel::onLoadNearbyPlaces,
                error = {
                    context.currentLocationGps(homeViewModel::onLoadNearbyPlaces)
                }
            )
    }
    HomeContent(
        homeUiState = homeUiState,
        navigateToDetail = navigateToDetail,
        permissionUiState = permissionUiState,
        currentLatLong = currentLatLong
    ) {
        if (it == null) {
            context.currentLocationByNetwork(
                location = homeViewModel::onLoadNearbyPlaces,
                error = {
                    context.currentLocationGps(homeViewModel::onLoadNearbyPlaces)
                }
            )
        } else {
            homeViewModel.onLoadNearbyPlaces(it)
        }
    }
}
