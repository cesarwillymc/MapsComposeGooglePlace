package com.cesarwillymc.technicaltest99minutes.ui.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import com.cesarwillymc.technicaltest99minutes.extension.orEmpty
import com.cesarwillymc.technicaltest99minutes.ui.base.GreenFullScreenLoading
import com.cesarwillymc.technicaltest99minutes.ui.detail.components.DetailContent
import com.cesarwillymc.technicaltest99minutes.ui.detail.viewmodel.DetailViewModel

/**
 * Created by cesarwillymamanicanaza on 12/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DetailScreen(navigateUp: () -> Unit, detailViewModel: DetailViewModel) {
    val detailUiState by detailViewModel.detailUiState.collectAsState()
    val stateFavorite = detailUiState.data?.isFavorite.orEmpty()

    GreenFullScreenLoading(detailUiState.isLoading)
    DetailContent(
        navigateUp = navigateUp,
        stateFavorite = stateFavorite,
        detailUiState = detailUiState,
        onUnMarkFavorite = detailViewModel::onUnMarkFavorite,
        onMarkFavorite = detailViewModel::onMarkFavorite,
        onClickReload = detailViewModel::onLoadPlaceId
    )
}
