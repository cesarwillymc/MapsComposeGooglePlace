package com.cesarwillymc.technicaltest99minutes.ui.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import com.cesarwillymc.technicaltest99minutes.extension.EMPTY_STRING
import com.cesarwillymc.technicaltest99minutes.extension.ONE
import com.cesarwillymc.technicaltest99minutes.ui.theme.OnyxBlack

@SuppressWarnings("LongParameterList")
@Composable
fun GreenCrossExtendScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    navigateUp: () -> Unit,
    toolbarTitle: String = EMPTY_STRING,
    actions: @Composable RowScope.() -> Unit = {},
    bottom: @Composable () -> Unit = {},
    topBarElevation: Dp = AppBarDefaults.TopAppBarElevation,
    content: @Composable () -> Unit
) {
    Scaffold(modifier = modifier, scaffoldState = scaffoldState, topBar = {
        GreenCrossTopAppBar(
            title = {
                Text(
                    text = toolbarTitle,
                    style = MaterialTheme.typography.h2,
                    color = OnyxBlack,
                    maxLines = ONE,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            navigationIcon = {
                GreenHomeUpButton(onNavigateUp = navigateUp)
            },
            actions = actions,
            elevation = topBarElevation
        )
    }, bottomBar = bottom, snackbarHost = snackbarHost) { innerPadding ->
    Box(modifier = Modifier.padding(innerPadding)) {
        content()
    }
}
}
