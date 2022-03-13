package com.cesarwillymc.technicaltest99minutes.ui.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.cesarwillymc.technicaltest99minutes.ui.theme.Small50
import com.cesarwillymc.technicaltest99minutes.ui.theme.ZeroDp
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

/**
 * A wrapper around [TopAppBar] which uses [Modifier.statusBarsPadding] to shift the app bar's
 * contents down, but still draws the background behind the status bar too.
 */
@SuppressWarnings("LongParameterList")
@Composable
fun GreenCrossTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.primarySurface,
    contentColor: Color = contentColorFor(backgroundColor),
    elevation: Dp = Small50,
    subHeader: @Composable () -> Unit = {}
) {
    Surface(
        color = backgroundColor,
        elevation = elevation,
        modifier = modifier
    ) {
        Column {
            TopAppBar(
                title = title,
                navigationIcon = navigationIcon,
                actions = actions,
                backgroundColor = Color.Transparent,
                contentColor = contentColor,
                elevation = ZeroDp,
                modifier = Modifier
                    .statusBarsPadding()
                    .navigationBarsPadding(bottom = false)
            )
            subHeader()
        }
    }
}
