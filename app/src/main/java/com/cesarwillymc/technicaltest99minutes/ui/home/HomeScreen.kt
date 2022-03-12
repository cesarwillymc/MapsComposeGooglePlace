package com.cesarwillymc.technicaltest99minutes.ui.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.cesarwillymc.technicaltest99minutes.ui.home.viewmodel.HomeViewModel

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
}