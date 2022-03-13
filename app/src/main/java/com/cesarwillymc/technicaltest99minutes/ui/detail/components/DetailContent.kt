package com.cesarwillymc.technicaltest99minutes.ui.detail.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.cesarwillymc.technicaltest99minutes.R
import com.cesarwillymc.technicaltest99minutes.ui.base.ErrorCard
import com.cesarwillymc.technicaltest99minutes.ui.base.GreenCrossExtendScaffold
import com.cesarwillymc.technicaltest99minutes.ui.detail.entities.DetailUiState
import com.cesarwillymc.technicaltest99minutes.ui.theme.Small100

/**
 * Created by cesarwillymamanicanaza on 13/03/22.
 * cesarwilly.mc@gmail.com
 *
 * Lima, Peru.
 */
@ExperimentalComposeUiApi
@Composable
fun DetailContent(
    navigateUp: () -> Unit,
    stateFavorite: Boolean,
    detailUiState: DetailUiState,
    onUnMarkFavorite: () -> Unit,
    onMarkFavorite: () -> Unit,
    onClickReload: () -> Unit
) {
    val localConfiguration = LocalConfiguration.current
    GreenCrossExtendScaffold(
        navigateUp = navigateUp,
        toolbarTitle = detailUiState.data?.name.orEmpty(),
        actions = {
            IconButton(onClick = {
                if (stateFavorite)
                    onUnMarkFavorite()
                else onMarkFavorite()
            }) {
                Icon(
                    painter = painterResource(
                        id = if (stateFavorite) R.drawable.ic_favorite_green
                        else R.drawable.ic_favorite_unselect
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colors.secondary
                )
            }
        },
    ) {
        LazyColumn(contentPadding = PaddingValues(Small100), modifier = Modifier.fillMaxSize()) {
            if (detailUiState.isError) {
                item {

                    ErrorCard(
                        Modifier
                            .fillMaxWidth()
                            .height(localConfiguration.screenHeightDp.dp),
                        error = stringResource(R.string.til_error_detail),
                        onClickRetry = onClickReload
                    )
                }
            }
            detailUiState.data?.let {
                item { InformationPlaceContent(it) }
            }
            detailUiState.data?.reviews?.let {
                item { ReviewContent(it) }
            }
        }
    }
}
